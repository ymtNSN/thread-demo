package com.ymt.edu.book;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/4
 */
public class CompletionServiceDemo {
    private final ExecutorService executorService;

    public CompletionServiceDemo(ExecutorService executorService) {
        this.executorService = executorService;
    }

    void renderPage(Object source) {
        List<Integer> info = new ArrayList<>();

        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

        for (Integer i : info) {
            completionService.submit(() -> i);
        }

        renderText(source);
        try {
            for (int i = 0; i < info.size(); i++) {
                Future<Integer> f = completionService.take();
                Integer rs = f.get();
                renderImage(rs);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void renderImage(Integer rs) {
    }

    private void renderText(Object source) {
    }

}
