package demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: xzw
 * @Date: 2019/11/19
 */
public class Tips1 {
    public static void main(String[] args) {
        // 1.当需要循环使用contains方法的时候，使用hashSet的方法可以将原来的O(n)的时间复杂度变为O(1)
        List<Object> list = new ArrayList<>();
        list.contains("");
        Set<Object> set = new HashSet<>();
        set.contains("");

        String str = "aa.txt";
        str.split(".");
        // Java编译器中的特殊字符都要重新转义-


    }

    // 1在申明变量后进行初始化
    private static Map<String, Integer> map = new HashMap<String, Integer>(){
        {
            map.put("Leo",1);
            map.put("Family-loving",2);
            map.put("Cold on the out side passionate on the inside",3);
        }
    };

    // 2通过静态代码块初始化
    private static Map<String, Integer> map1 = new HashMap<>();
    static {
        map1.put("Leo",1);
        map1.put("Family-loving",2);
        map1.put("Cold on the out side passionate on the inside",3);
    }





    }
