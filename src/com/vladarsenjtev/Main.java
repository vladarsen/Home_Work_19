package com.vladarsenjtev;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        ThreadSafeList threadSafeList = new ThreadSafeList();
        ExecutorService executorService = Executors.newCachedThreadPool();
        threadSafeList.add(200);
        threadSafeList.add(200);
        threadSafeList.add(300);


        List<Future<Integer>> futures = executorService.invokeAll(threadSafeList.getCallables());
        System.out.println(futures);
        executorService.shutdown();
        System.out.println("На АЗС осталось топлива: " + threadSafeList.getPetrolStation() + " л.");
        System.out.println("Затраченное время на выполнение процесса: " + (System.currentTimeMillis() - start));
    }
}

