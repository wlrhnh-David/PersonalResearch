package com.baidu.david.thread;

/**
 * Created by weiwei22 on 17/6/21.
 */

public class ThredMain2 {
    public static void main(String[] args) {
        Storage storage = new Storage();

        Producer producer1 = new Producer("P1", storage);
        Producer producer2 = new Producer("P2", storage);
        Producer producer3 = new Producer("P3", storage);
        producer1.setNum(30);
        producer2.setNum(10);
        producer3.setNum(100);

        Consumer consumer1 = new Consumer("C1", storage);
        Consumer consumer2 = new Consumer("C2", storage);
        Consumer consumer3 = new Consumer("C3", storage);
        Consumer consumer4 = new Consumer("C4", storage);
        consumer1.setNum(40);
        consumer2.setNum(20);
        consumer3.setNum(40);
        consumer4.setNum(90);

        producer1.start();
        producer2.start();
        producer3.start();

        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
    }
}
