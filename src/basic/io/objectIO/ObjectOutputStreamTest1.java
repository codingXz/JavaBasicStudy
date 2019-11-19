package io.objectIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: xzw
 * @Date: 2019/11/18
 */
public class ObjectOutputStreamTest1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        writeObject();

//        readObject();
        // 当需要循环使用contains方法的时候，使用hashSet的方法可以将原来的O(n)的时间复杂度变为O(1)
        List<Object> list = new ArrayList<>();
        list.contains("");
        Set<Object> set = new HashSet<>();
        set.contains("");
    }

    private static void readObject() throws IOException, ClassNotFoundException {
        ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("c.txt"));
        Student o = (Student)objectIn.readObject();
        System.out.println(o);
    }

    private static void writeObject() throws IOException {
        Student xzw = new Student(10, "xzw");
        ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("c.txt"));
        objectOut.writeObject(xzw);
        System.out.println("写入对象成功");
    }
}
