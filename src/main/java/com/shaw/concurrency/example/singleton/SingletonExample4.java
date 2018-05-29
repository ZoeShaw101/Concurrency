package com.shaw.concurrency.example.singleton;

import com.shaw.concurrency.example.annotaion.NotThreadSafe;

/**
 * 懒汉模式：=> 双重同步锁机制
 * 单例的实例在第一次使用的时候进行创建
 */

@NotThreadSafe
public class SingletonExample4 {
    //私有构造函数
    private SingletonExample4() {

    }
    //单例对象
    private static SingletonExample4 instance = null;
    //静态工厂方法
    public static SingletonExample4 getInstance() {
        if (instance == null) {  //双重检测机制
            synchronized (SingletonExample4.class) {
                if (instance == null)
                    instance = new SingletonExample4();
            }
        }
        return instance;
    }

}
