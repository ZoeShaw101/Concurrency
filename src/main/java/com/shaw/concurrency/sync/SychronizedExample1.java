package com.shaw.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SychronizedExample1 {
    //修饰一个代码块
    public void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 {} - {}", j , i);
            }
        }
    }
    //修饰一个方法
    public synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 {} - {}", j , i);
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        SychronizedExample1 example1 = new SychronizedExample1();
        SychronizedExample1 example2 = new SychronizedExample1();
        service.execute(() -> {
            example1.test2(1);
        });
        service.execute(() -> {
            example2.test2(2);
        });
        service.shutdown();
    }
}
