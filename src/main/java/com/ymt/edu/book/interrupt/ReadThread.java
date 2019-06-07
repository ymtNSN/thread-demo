package com.ymt.edu.book.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @Description: 将非标准的取消操作封装在Thread中
 * @Author: yangmingtian2
 * @Date: 2019/6/7
 */
public class ReadThread extends Thread {
    private final Socket socket;
    private final InputStream in;

    public ReadThread(Socket socket, InputStream in) {
        this.socket = socket;
        this.in = in;
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[1024];
            while (true) {
                int count = in.read(buf);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    //
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // 允许线程退出
        }
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            super.interrupt();
        }
    }
}
