package com.shaw.concurrency.example.concurrent;

import com.shaw.concurrency.example.annotaion.NotThreadSafe;
import com.shaw.concurrency.example.annotaion.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.*;

/**
 * CopyOnWriteArrayList
 *
 */

@Slf4j
@ThreadSafe
public class CopyOnWriteArrayListExample {
    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行数
    public static int threadTotal = 200;

    public static List<Integer> list = new CopyOnWriteArrayList<>();

    private static void add(int i ) {
        list.add(i);
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
        log.info("count {}", list.size());
    }
}
