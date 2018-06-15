package jvm;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M
 */
public class DirectMemoryOOM {

    public static void main(String[] args) throws IllegalAccessException {
        List<ByteBuffer> list = new ArrayList<ByteBuffer>();
        while (true) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024);
            list.add(buffer);
        }
    }
}
