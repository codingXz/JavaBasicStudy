package io.objectIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * objectOutputStream和objectInputStream进行对象的读入和写出
 *
 * 当readObject时里面的内容为空时会抛出一下异常
 * @Author: xzw
 * @Date: 2019/11/18
 */
public class ObjectOutputStreamTest1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try(ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("c.txt"));
            ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("c.txt"))){

            writeObject(objectOut);
            readObject(objectIn);
        }

    }

    private static void readObject(ObjectInputStream objectIn) throws IOException, ClassNotFoundException {
        Student o = (Student)objectIn.readObject();
        System.out.println(o);
    }

    private static void writeObject(ObjectOutputStream objectOut) throws IOException {
        Student xzw = new Student(10, "xzw");
        objectOut.writeObject(xzw);
        System.out.println("写入对象成功");
    }
}
