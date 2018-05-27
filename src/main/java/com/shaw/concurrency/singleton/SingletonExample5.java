package com.shaw.concurrency.singleton;

import com.shaw.concurrency.annotaion.ThreadSafe;

/**
 * 懒汉模式：=> 双重同步锁机制
 * 单例的实例在第一次使用的时候进行创建
 */

@ThreadSafe
public class SingletonExample5 {
    //私有构造函数
    private SingletonExample5() {

    }
    //单例对象 volatile + 双重检测机制 -> 禁止指令重排
    private volatile static SingletonExample5 instance = null;
    //静态工厂方法
    public static SingletonExample5 getInstance() {
        if (instance == null) {  //双重检测机制
            synchronized (SingletonExample5.class) {
                if (instance == null)
                    instance = new SingletonExample5();
            }
        }
        return instance;
    }

}
