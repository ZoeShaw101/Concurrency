package com.shaw.concurrency.example.threadLocal;


/**
 * 线程封闭
 */
public class RequestHolder {
    private static final ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
