package com.ymt.edu.book;

import java.lang.annotation.Target;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/4
 */
public class QuoteTest {

    private ExecutorService executorService;

    public List<TravelQuote> getRankedTravelQuote(TravelInfo travelInfo, Set<TravelCompany> companies,
                                                  Comparator<TravelQuote> ranking, long time, TimeUnit unit) throws InterruptedException {
        List<QuoteTask> quoteTasks = new ArrayList<>();
        for (TravelCompany company : companies) {
            quoteTasks.add(new QuoteTask(company, travelInfo));
        }
        List<Future<TravelQuote>> futures = executorService.invokeAll(quoteTasks, time, unit);

        List<TravelQuote> quotes = new ArrayList<>(quoteTasks.size());

        Iterator<QuoteTask> iterator = quoteTasks.iterator();
        for(Future<TravelQuote> f:futures){
            QuoteTask task = iterator.next();
            try {
                quotes.add(f.get());
            } catch (ExecutionException e) {
                quotes.add(task.getFailureQuote(e.getCause()));
            }
        }
        Collections.sort(quotes,ranking);
        return quotes;
    }
}
