package com.shaw.concurrency.example.concurrent;

import com.shaw.concurrency.example.annotaion.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
@NotThreadSafe
public class ConcurrentHashMapExample {
    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行数
    public static int threadTotal = 200;

    public static Map<Integer, Integer> map = new ConcurrentHashMap<>();

    public static Map<Integer, Integer> map2 = Collections.synchronizedMap(new HashMap<>());

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
