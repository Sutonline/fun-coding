package cn.kevin.codec;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.*;

/**
 * @author yongkang.zhang
 * created at 2019-05-03
 */
public class SerializableTest {

	@Test
	public void serialize() throws IOException {
		A a = new A();
		a.name = "张三";
		a.age = "5";

		BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("./a.txt"));
		ObjectOutputStream writeStream = new ObjectOutputStream(outputStream);
		writeStream.writeObject(a);
		writeStream.flush();
	}


	/*
	 * 会抛出异常
	 * java.lang.ClassNotFoundException: cn.kevin.codec.SerializableTest$A
	 */
	@Test
	public void deleteClass() throws IOException, ClassNotFoundException {
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("./a.txt"));
		Object o = objectInputStream.readObject();
		System.out.println(JSON.toJSONString(o));
	}

	/*
	 * UID不相同的话抛出java.io.InvalidClassException: cn.kevin.codec.SerializableTest$A; local class incompatible: stream classdesc serialVersionUID = -2177016800543946109, local class serialVersionUID = 11
	 *
	 * 两个class都存在，并且serialVersinUID相等，反序列化成功，但是cast失败java.lang.ClassCastException: cn.kevin.codec.SerializableTest$A cannot be cast to cn.kevin.codec.A
	 *
	 */
	@Test
	public void moveClass() throws IOException, ClassNotFoundException {
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("./a.txt"));
		cn.kevin.codec.A a = (cn.kevin.codec.A) objectInputStream.readObject();
		System.out.println(a);
	}

	public static class A implements Serializable {

		private static final long serialVersionUID = 11L;

		private String name;
		private String age;
	}
}
