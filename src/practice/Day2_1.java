package practice;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author yang
 * @date 2019/7/8 10:33
 */
public class Day2_1 {
    public static void main(String[] args) throws Exception {
        byte[] bytes = new byte[1024];
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 9876));
        Scanner scanner = new Scanner(System.in);
        while (true) {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(scanner.next().getBytes());
            outputStream.flush();
            InputStream inputStream = socket.getInputStream();
            while (inputStream.read(bytes)!=-1){
                String s = new String(bytes);
                System.out.println(s);
            }
            inputStream.close();
            outputStream.close();
        }
    }
}
