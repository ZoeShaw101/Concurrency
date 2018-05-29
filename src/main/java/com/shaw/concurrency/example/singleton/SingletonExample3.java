package com.shaw.concurrency.example.singleton;

import com.shaw.concurrency.example.annotaion.NotRecommend;
import com.shaw.concurrency.example.annotaion.ThreadSafe;

/**
 * 懒汉模式：
 * 单例的实例在第一次使用的时候进行创建
 * synchronized保证了线程安全，但是性能上开销很大
 */

@ThreadSafe
@NotRecommend
public class SingletonExample3 {
    //私有构造函数
    private SingletonExample3() {

    }
    //单例对象
    private static SingletonExample3 instance = null;
    //静态工厂方法
    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }

}
