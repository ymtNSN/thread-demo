package com.ymt.edu.condition;

import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/1
 */
@Immutable
public class ThreadLocalDemo {

    static ThreadLocal<Integer> num =    new ThreadLocal(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) {
        System.out.println(num.get());
    }
}
