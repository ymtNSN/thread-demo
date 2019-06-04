package com.ymt.edu.book;

import java.util.concurrent.Callable;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/4
 */
public class QuoteTask implements Callable<TravelQuote> {
    private final TravelCompany company;
    private final TravelInfo travelInfo;

    public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
        this.company = company;
        this.travelInfo = travelInfo;
    }

    @Override
    public TravelQuote call() throws Exception {
        return company.solicitQuote(travelInfo);
    }

    public TravelQuote getFailureQuote(Throwable cause) {
        return null;
    }
}
