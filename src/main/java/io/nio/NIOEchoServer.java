package io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOEchoServer {

    static final String POISON_PILL = "POISON_PILL";

    private Selector selector;
    private ServerSocketChannel serverChannel;
    private ByteBuffer byteBuffer;

    public NIOEchoServer() {
    }

    public void start() throws Exception {
        try {
            byteBuffer = ByteBuffer.allocate(256);
            selector = Selector.open();
            serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress("127.0.0.1", 5454));
            serverChannel.configureBlocking(false);
            // accept is for server side, connect is for client side, see berkeley sockets wiki
            SelectionKey registerKey = serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            // register.attach(xx)
            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey selectionKey = iter.next();
                    if (selectionKey.isAcceptable()) {
                        // do Accept
                        // 跟上面 chennel 同一个对象
                        // System.out.println(selectionKey.channel() == serverChannel);
                        // 跟上面 registerKey 同一个对象
                        //System.out.println(registerKey == selectionKey);
                        SocketChannel socketChannel = serverChannel.accept();
                        socketChannel.configureBlocking(false);
                        SelectionKey keyR = socketChannel.register(selector, SelectionKey.OP_READ);
                        //可以同时注册多个事件，该例子中 write 没有必要注册，因为 tcp 双工，连接后一直都可以 write，echo 是收到才返回
                        //socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    }
                    if (selectionKey.isReadable()) {
                        // 此时跟上面 keyR 是同一对象
                        // 如果没有注册 write，即 interest set 里没有，则 ready set 里也不会有，但实际是可以写
                        //boolean w = selectionKey.isWritable();
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        // 注意客户端close后，server 端会一直 readable，需要服务端主动关闭，判断 read 结果是 -1
                        int read = socketChannel.read(byteBuffer);
                        System.out.println(socketChannel.isConnected());
                        // 切换到 byteBuffer 读模式
                        byteBuffer.flip();
                        // 全部内容，包括越界部分
                        String content = new String(byteBuffer.array()).trim();
                        System.out.println(content);
                        // 有效内容，只复制容器，byte[] 共享
                        ByteBuffer duplicateContainer = byteBuffer.duplicate();
                        while (duplicateContainer.hasRemaining())
                            System.out.print((char)duplicateContainer.get()); // 只接受 ascii，1字节
                        System.out.println();
                        if (POISON_PILL.equals(content)) {
                            socketChannel.close();
                        } else {
                            socketChannel.write(byteBuffer);
                        }
                        // 即使关闭也必须 clear，该 buffer 共享
                        byteBuffer.clear();
                    }
                    iter.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverChannel != null) {
                serverChannel.close();
            }
            if (selector != null) {
                selector.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new NIOEchoServer().start();
    }
}
