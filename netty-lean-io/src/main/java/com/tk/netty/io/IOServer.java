package com.tk.netty.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * https://www.jianshu.com/p/a4e03835921a
 * 我们简化下场景：客户端每隔两秒发送一个带有时间戳的"hello world"给服务端，服务端收到之后打印。
 * IO server
 *
 * @author mengxr
 * @since 1.0
 */
public class IOServer {
    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(8000);
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        // (1) 阻塞方法获取新的连接
                        final Socket socket = serverSocket.accept();
                        // (2) 每一个新的连接都创建一个线程，负责读取数据
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    byte[] data = new byte[1024];
                                    InputStream inputStream = socket.getInputStream();
                                    while (true) {
                                        int len;
                                        // (3) 按字节流方式读取数据
                                        while ((len = inputStream.read(data)) != -1) {
                                            System.out.println(Thread.currentThread().getName() + new String(data, 0, len));
                                        }
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
