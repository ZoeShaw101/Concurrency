package com.shaw.concurrency.example.atomic;

import com.shaw.concurrency.example.annotaion.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;


/**
 *
 */
@ThreadSafe
@Slf4j
public class AtomicExample5 {
    @Getter
    private volatile int count = 100;

    private static AtomicExample5 atomicExample5 = new AtomicExample5();

    public static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    public static void main(String[] args) {
        if (updater.compareAndSet(atomicExample5, 100, 120)) {
            log.info("update success {}" , atomicExample5.getCount());
        } else {
            log.info("update failed");
        }
    }
}