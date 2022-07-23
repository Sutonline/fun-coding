package cn.kevin.io;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

/**
 * @author yongkang.zhang
 * date 2022/7/9
 */
public class ReaderDemo {

    @SneakyThrows
    public static void main(String[] args) {
        File file = new File("");
        FileReader reader = new FileReader(file);
        int i;
        while ((i = reader.read()) != -1) {
            System.out.println((char) i);
        }
        reader.close();

        FileInputStream inputStream = new FileInputStream(file);
        while ((i = inputStream.read()) != -1) {
            System.out.println((char) i);
        }
        inputStream.close();
    }
}
