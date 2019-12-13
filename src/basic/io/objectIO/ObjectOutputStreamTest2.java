package io.objectIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 使用new FileOutputStream("c.txt",true)
 * 向同一个文件中追加 序列化对象，每次都会向文件中序列化一个header。
 * 在反序列化的时候每个 ObjectInputStream 对象只会读取一个header，那么当遇到第二个的时候就会报错
 * 异常java.io.StreamCorruptedException: invalid type code: AC
 * 解决方法：方法（1）.用数组将已经存储数据读取出来，将这次的数据添加进去，再整体写入
 *          方法（2）.重写objectInputStream的writeStreamHeader()
 */
public class ObjectOutputStreamTest2 {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("c.txt",true));
        ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("c.txt"));
    }
}
