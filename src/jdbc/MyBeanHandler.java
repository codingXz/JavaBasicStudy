import org.apache.commons.dbutils.ResultSetHandler;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * 数据库到 bean转化
 *
 * 1.通过resultSetMetaData获取到对应的columnName 和 A.Class的字节码对象中的属性描述器PropertyDescriptor进行匹配
 * 重要方法 resultSet.getMetaData(); metaData.getColumnName(index)
 * 重要方法 clazz = > beanInfo : beanInfo = Introspector.getBeanInfo(type); beanInfo.getPropertyDescriptors()
 *
 * 2.通过数组存储下来对应的关系
 * 3.通过不同字段的类型在resultSet中进行取值（class.isPrimitive()区分是基本类型，Integer.Type(相当于int.class)、Integer.class）
 * 4.赋值操作
 *
 * @Author: xzw
 * @Date: 2020/4/1
 */
public class MyBeanHandler<T> implements ResultSetHandler<T> {

    private final Class<T> type;

    public MyBeanHandler(Class<T> clazz) {
        this.type = clazz;
    }

    @Override
    public T handle(ResultSet resultSet) throws SQLException {
        return resultSet.next() ? myConvertMethod(resultSet) : null;
    }

    private T myConvertMethod(ResultSet resultSet) throws SQLException {
        System.out.println(resultSet.getInt(1));
        // tip：对应关系（resultSet中的数据索引从1开始）
        ResultSetMetaData metaData = resultSet.getMetaData();
        PropertyDescriptor[] propertyDescriptors = getPropertyDescriptor(type);
        int[] mapIndexArr = mappingIndex(metaData, propertyDescriptors);
        return createBean(resultSet, mapIndexArr, propertyDescriptors);
    }

    private PropertyDescriptor[] getPropertyDescriptor(Class<T> type) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            return beanInfo.getPropertyDescriptors();
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int[] mappingIndex(ResultSetMetaData metaData, PropertyDescriptor[] propertyDescriptors) throws SQLException {
        // 开始对应（用-1排除未找到的情况）
        int columnCount = metaData.getColumnCount();
        int[] mapIndexArr = new int[columnCount + 1];
        Arrays.fill(mapIndexArr, -1);

        for (int i = 1; i < columnCount + 1; i++) {
            String colName = metaData.getColumnLabel(i);
            if (colName == null) {
                colName = metaData.getColumnName(i);
            }

            // 每一列的数据对应的字段的索引是第几个
            // 遍历查询出数据的列名 对应到propertyName
            assert propertyDescriptors != null;
            for (int j = 0; j < propertyDescriptors.length; j++) {
                if (colName.equals(propertyDescriptors[j].getName())) {
                    mapIndexArr[i] = j;
                    break;
                }
            }
        }
        return mapIndexArr;
    }

    private T createBean(ResultSet resultSet, int[] mapIndexArr, PropertyDescriptor[] propertyDescriptors) throws SQLException {
        T bean = null;
        try {
            bean = type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        for (int k = 1; k < mapIndexArr.length; k++) {
            Object value = null;
            if (mapIndexArr[k] != -1) {
                PropertyDescriptor prop = propertyDescriptors[mapIndexArr[k]];
                Class<?> propertyType = prop.getPropertyType();
                try {
                    value = getMetaDataValue(resultSet, k, propertyType);
                    if (value == null && propertyType.isPrimitive()) {
                        value = new Object();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                callSetter(bean, value, prop);
            }
        }
        return bean;
    }

    private Object getMetaDataValue(ResultSet resultSet, int k, Class<?> propertyType) throws SQLException {

        // 类型处理，通过类型来正确的获取resultSet中的结果
        if (!propertyType.isPrimitive() && resultSet.getObject(k) == null) {
            return null;
        } else {
            if (propertyType.equals(String.class)) {
                return resultSet.getString(k);
            }
            if (propertyType.equals(Integer.TYPE) || propertyType.equals(Integer.class)) {
                return resultSet.getInt(k);
            } else if (propertyType.equals(Short.TYPE) || propertyType.equals(Short.class)) {
                return resultSet.getShort(k);
            } else if (propertyType.equals(Long.TYPE) || propertyType.equals(Long.class)) {
                return resultSet.getLong(k);
            } else if (propertyType.equals(Byte.TYPE) || propertyType.equals(Byte.class)) {
                return resultSet.getByte(k);
            } else if (propertyType.equals(Double.TYPE) || propertyType.equals(Double.class)) {
                return resultSet.getDouble(k);
            } else if (propertyType.equals(Float.TYPE) || propertyType.equals(Float.class)) {
                return resultSet.getFloat(k);
            } else if (propertyType.equals(Character.TYPE) || propertyType.equals(Character.class)) {
                return ' ';
            } else if (propertyType.equals(Boolean.TYPE) || propertyType.equals(Boolean.class)) {
                return resultSet.getBoolean(k);
            } else if (propertyType.equals(Timestamp.class)) {
                return resultSet.getTimestamp(k);
            } else {
                throw new SQLException("未定义的数据类型");
            }
        }

    }


    private boolean isCompatibleType(Object value, Class<?> type) {
        if (value != null && !type.isInstance(value)) {
            if (type.equals(Integer.TYPE) && value instanceof Integer) {
                return true;
            } else if (type.equals(Long.TYPE) && value instanceof Long) {
                return true;
            } else if (type.equals(Double.TYPE) && value instanceof Double) {
                return true;
            } else if (type.equals(Float.TYPE) && value instanceof Float) {
                return true;
            } else if (type.equals(Short.TYPE) && value instanceof Short) {
                return true;
            } else if (type.equals(Byte.TYPE) && value instanceof Byte) {
                return true;
            } else if (type.equals(Character.TYPE) && value instanceof Character) {
                return true;
            } else {
                return type.equals(Boolean.TYPE) && value instanceof Boolean;
            }
        } else {
            return true;
        }
    }

    private void callSetter(Object bean, Object value, PropertyDescriptor prop) throws SQLException {
        if (isCompatibleType(value, prop.getPropertyType())) {
            Method writeMethod = prop.getWriteMethod();
            try {
                writeMethod.invoke(bean, value);
            } catch (IllegalAccessException e) {
                throw new SQLException("Cannot set " + prop.getName() + ": incompatible types, cannot convert " + value.getClass().getName() + " to " + prop.getPropertyType());
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
