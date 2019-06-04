package com.ymt.edu.book;

import java.math.BigInteger;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/3
 */
public class ExpensiveFunction implements Computable<String,BigInteger> {
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        return new BigInteger(arg);
    }
}
