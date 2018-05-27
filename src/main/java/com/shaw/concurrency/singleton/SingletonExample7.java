package com.shaw.concurrency.singleton;


import com.shaw.concurrency.annotaion.ThreadSafe;

/**
 * 使用枚举创建单例
 */

@ThreadSafe
public class SingletonExample7 {

    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton{
        INSTANCE;
        private SingletonExample7 singleton;


        //JVM保证这个只创建一次
        Singleton() {
            singleton = new SingletonExample7();
        }
        public SingletonExample7 getSingleton() {return singleton;}
    }
}
