package com.shaw.concurrency.publish;

import com.shaw.concurrency.annotaion.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 发布对象；这样任何一个线程都可以更改state变量，造成state数据域的不确定性
 */

@NotThreadSafe
@Slf4j
public class UnsafePublish {
    private String[] state = {"a", "b", "c"};

   public String[] getState() {return  state;}

   public static void main(String[] args) {
       UnsafePublish unsafePublish = new UnsafePublish();
       log.info("state: " + Arrays.toString(unsafePublish.getState()));
       unsafePublish.getState()[0] = "d";
       log.info("state: " + Arrays.toString(unsafePublish.getState()));
   }
}
