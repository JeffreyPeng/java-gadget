package io.nio;

import java.io.*;
import java.nio.channels.FileChannel;

public class CopyFile {

    // normal
    public static void copyFileByStream(File input, File output) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(input);
             FileOutputStream outputStream = new FileOutputStream(output)) {
            byte[] buff = new byte[1024];
            int len;
            while ((len = inputStream.read(buff)) > 0) {
                System.out.println(len);
                outputStream.write(buff, 0, len);
            }
        }
    }

    // zero copy
    public static void copyFileByChannel(File input, File output) throws IOException {
        try (FileChannel inputChannel = new FileInputStream(input).getChannel();
          FileChannel outputChannel = new FileOutputStream(output).getChannel();) {
            System.out.println(inputChannel.size());
            long size = inputChannel.size();
            while (size > 0) {
                long transfers = inputChannel.transferTo(inputChannel.position(), size, outputChannel);
                System.out.println(transfers);
                size -= transfers;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File input = new File("src/main/java/io/nio/CopyFile.java");
        File output = new File("target/CopyFile.java");
        if (output.exists()) {
            output.delete();
        }
        copyFileByStream(input, output);
        //copyFileByChannel(input, output);
        System.out.println("input length: " + input.length());
        System.out.println("output length: " + output.length());
    }
}
