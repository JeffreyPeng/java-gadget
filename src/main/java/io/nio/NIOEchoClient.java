package io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOEchoClient {

    public NIOEchoClient() {
    }

    public String send(String msg) throws Exception {
        SocketChannel clientChannel = null;
        try {
            //byteBuffer = ByteBuffer.allocate(256);
            clientChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 5454));
            ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
            clientChannel.write(byteBuffer);
            byteBuffer.clear();
            clientChannel.read(byteBuffer);
            return new String(byteBuffer.array());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (clientChannel != null) {
                //注意如果仅仅 close，server 端会一直 readable，需要服务端主动关闭，判断 read 结果是 -1，异常关闭才会导致服务端收到异常
                clientChannel.write(ByteBuffer.wrap(NIOEchoServer.POISON_PILL.getBytes()));
                clientChannel.close();
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new NIOEchoClient().send("hello"));
    }
}
