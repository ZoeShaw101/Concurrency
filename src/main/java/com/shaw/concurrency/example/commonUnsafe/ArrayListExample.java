package com.shaw.concurrency.example.commonUnsafe;

import com.shaw.concurrency.example.annotaion.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class ArrayListExample {
    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行数
    public static int threadTotal = 200;

    public static List<Integer> list = new ArrayList<>();

    public static Set<Integer> hashset = new HashSet<>();

    public static Map<Integer, Integer> map = new HashMap<>();

    private static void add(int i ) {
        map.put(i ,i);
    }

    public static void main(String[] agrs) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(count);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count {}", map.size());
    }
}
