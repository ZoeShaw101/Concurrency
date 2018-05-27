package com.shaw.concurrency.publish;

import com.shaw.concurrency.annotaion.NotRecommend;
import com.shaw.concurrency.annotaion.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;


/**
 * 对象逸出：当一个对象还没构造完成时，就被其他线程所看见
 * this引用逸出:新线程在对象构造完成之前就看见他了
 */

@NotRecommend
@NotThreadSafe
@Slf4j
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
         new Escape();
    }

}
