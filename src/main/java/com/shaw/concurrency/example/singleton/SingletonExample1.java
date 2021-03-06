package com.shaw.concurrency.example.singleton;

import com.shaw.concurrency.example.annotaion.NotThreadSafe;

/**
 * 懒汉模式：
 * 单例的实例在第一次使用的时候进行创建
 */

@NotThreadSafe
public class SingletonExample1 {
    //私有构造函数
    private SingletonExample1() {

    }
    //单例对象
    private static SingletonExample1 instance = null;
    //静态工厂方法
    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }

}
