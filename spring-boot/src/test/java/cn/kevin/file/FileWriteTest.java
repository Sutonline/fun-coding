package cn.kevin.file;

import au.com.bytecode.opencsv.CSVWriter;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.stream.IntStream;

/**
 * @author yongkang.zhang
 * created at 2018-12-22
 */
public class FileWriteTest {

	@Test
	public void writeTest() throws IOException {
		File file = new File("a.txt");
		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fileOutputStream = new FileOutputStream(file);
		CSVWriter writer = new CSVWriter(new OutputStreamWriter(fileOutputStream, Charset.forName("utf-8")), CSVWriter.DEFAULT_SEPARATOR);
		IntStream.range(1, 10000).forEach(
			i -> writer.writeNext("我是第", i + "", "几行")
		);

		writer.flush();
		writer.close();
		fileOutputStream.close();
	}
}
