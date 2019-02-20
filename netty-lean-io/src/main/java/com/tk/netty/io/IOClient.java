package com.tk.netty.io;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author mengxr
 * @since 1.0
 */
public class IOClient {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Socket socket = new Socket("127.0.0.1", 8000);
                    while (true) {
                        socket.getOutputStream().write((new Date() + Thread.currentThread().getName() + "Hello word").getBytes());
                        socket.getOutputStream().flush();
                        Thread.sleep(2000);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
