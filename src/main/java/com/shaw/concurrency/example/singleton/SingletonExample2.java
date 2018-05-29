package com.shaw.concurrency.example.singleton;

import com.shaw.concurrency.example.annotaion.ThreadSafe;

/**
 * 饿汉模式：
 * 单例的实例在类装载的时候进行创建
 */

@ThreadSafe
public class SingletonExample2 {
    //私有构造函数
    private SingletonExample2() {

    }

    //单例对象
    private static SingletonExample2 instance = null;

    static {
        instance = new SingletonExample2();
    }

    //静态工厂方法
    public static SingletonExample2 getInstance() {
        return instance;
    }

    public static void main(String[] agrs) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }

}
