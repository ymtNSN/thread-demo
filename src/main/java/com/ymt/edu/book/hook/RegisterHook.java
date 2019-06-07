package com.ymt.edu.book.hook;

import com.ymt.edu.book.logservice.LogService;

/**
 * @Description: 注册一个关闭钩子来停止日志服务
 * @Author: yangmingtian
 * @Date: 2019/6/8
 */
public class RegisterHook {

    public void start() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
//                    LogService.this.stop();
//                }catch (InterruptedException ignored){
//
//                }
            }
        }));
    }
}
