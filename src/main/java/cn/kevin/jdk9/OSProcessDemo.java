package cn.kevin.jdk9;

import java.nio.file.Paths;

/**
 * os process demo
 * created by yongkang.zhang
 * added at 2018/1/3
 */
public class OSProcessDemo {

    public static void main(String[] args) {
        ProcessBuilder dir = new ProcessBuilder()
                .command("dir")
                .directory(Paths.get("D:\\").toFile());

        ProcessBuilder findStr = new ProcessBuilder()
                .command("findstr", "pdf")
                .redirectOutput(ProcessBuilder.Redirect.INHERIT);
    }
}
