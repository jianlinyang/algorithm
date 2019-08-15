package practice;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yang
 * @date 2019/7/8 10:26
 */
public class Day2 {
    public static void main(String[] args) throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        List<SocketChannel> list = new ArrayList<>();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9091));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel == null) {
                Thread.sleep(1000);
                System.out.println("没人连接");
                for (SocketChannel channel : list) {
                    int read = channel.read(byteBuffer);
                    if (read != 0) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array()));
                    }
                }
            } else {
                socketChannel.configureBlocking(false);
                list.add(socketChannel);
                for (SocketChannel channel : list) {
                    int read = channel.read(byteBuffer);
                    if (read != 0) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array()));
                    }
                }
            }
        }
    }
}
