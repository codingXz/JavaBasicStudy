package demo1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    }
}
