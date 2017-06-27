package com.baidu.david.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadMain {
    private static volatile int counter;
    private static AtomicInteger atomicInteger;
    private static Bean bean = new Bean();

    private static List<String> mGoodsList = new ArrayList<>();

    public static void main(String[] args) {
        /*atomicInteger = new AtomicInteger(0);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        counter++;
                        bean.increase();
                        atomicInteger.incrementAndGet();
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("counter = " + counter);
        System.out.println("bean.mDown = " + bean.mDown);
        System.out.println("atomicInteger = " + atomicInteger);*/

        ProductThread productThread1 = new ProductThread("P1", mGoodsList);
        productThread1.start();
        ProductThread productThread2 = new ProductThread("P2", mGoodsList);
        productThread2.start();

        Object mLocker = new Object();
        CustomThread customThread1 = new CustomThread("C1", mGoodsList);
        customThread1.setPriority(Thread.MAX_PRIORITY);
        CustomThread customThread2 = new CustomThread("C2", mGoodsList);
        customThread1.setPriority(Thread.MIN_PRIORITY);
        customThread1.start();
        customThread2.start();
    }

    private static class Bean {
        private int mDown;

        public synchronized void increase() {
            mDown++;
        }
    }
}
