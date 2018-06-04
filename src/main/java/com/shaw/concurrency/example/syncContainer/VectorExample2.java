package com.shaw.concurrency.example.syncContainer;

import com.shaw.concurrency.example.annotaion.NotThreadSafe;
import com.shaw.concurrency.example.annotaion.ThreadSafe;

import java.util.List;
import java.util.Vector;

/**
 * 同步容器并不是在所有情况下都线程安全
 */

@NotThreadSafe
public class VectorExample2 {
    public static List<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) vector.add(i);

            Thread thread1 = new Thread() {
                public void run() {
                    for (int i = 0; i < 10; i++)
                        vector.remove(i);
                }
            };

            Thread thread2 = new Thread() {
                public void run() {
                    for (int i = 0; i < 10; i++)
                        vector.get(i);
                }
            };
            thread1.start();
            thread2.start();
        }
    }
}
