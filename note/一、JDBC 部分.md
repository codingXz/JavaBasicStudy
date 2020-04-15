## 一、JDBC 部分

> 思考1：为什么会出现JDBC ？是为了解决什么样的问题？
>
> 思考2：使用JDBC来操作数据库时，一般的操作顺序，最容易遇到的一些问题。
>
> 思考3：JDBC是如何来操作事务的，事务的基本特性
>
> 思考4：什么是连接池，连接池的作用是什么？
>
> 思考5：JDBC返回的结果集 怎么快速的mapping的到想要对象上？



```java
答题五（参数传入对应的resultSet、Class实现转化）：
		// 1.通过resultSetMetaData获取到对应的columnName 和 A.Class的字节码对象中的属性描述器PropertyDescriptor进行匹配
        // 重要方法 resultSet.getMetaData(); metaData.getColumnName(index)
        // 重要方法 clazz = > beanInfo : beanInfo = 				  Introspector.getBeanInfo(type); beanInfo.getPropertyDescriptors()

        // 2.通过数组存储下来对应的关系

        // 3.通过不同字段的类型在resultSet中进行取值（class.isPrimitive()区分是基本类型，Integer.Type(相当于int.class)、Integer.class）	
        
        // 4.赋值操作
    
<同类型思考>（如何快速将map对应的值填充到bean当中）
    例子apache.Common.BeanUtils.populate(Object bean,Map<> map)：
    // 1.获取传入对象的字节码对象
	// 2.获取map集合中所有的键和值
	// 3.调用Class中的getDeclaredField()方法将每一个键传入, 得到Field对象
	// 4.通过Field对象中的set方法赋值
```

