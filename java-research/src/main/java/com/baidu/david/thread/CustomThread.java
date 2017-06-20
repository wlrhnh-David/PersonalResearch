package com.baidu.david.thread;

import java.util.List;

/**
 * Created by weiwei22 on 17/6/21.
 */

public class CustomThread extends Thread {
    private List<String> mGoodsList;

    public CustomThread(String name, List<String> goodsList) {
        super(name);
        mGoodsList = goodsList;
    }

    @Override
    public void run() {
        synchronized (mGoodsList) {
            while (true) {
                System.out.println("我是消费者 " + Thread.currentThread().getName() + "~~~~~~~~~~~~~~~~~~");

                if (mGoodsList.isEmpty()) {
                    System.out.println("我是消费者 " + Thread.currentThread().getName() + "，生产者赶紧生产吧~~~~");
                    System.out.println();
                    System.out.println();

                    mGoodsList.notifyAll();

                    try {
                        mGoodsList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    String good = mGoodsList.remove(0);
                    System.out.println("我是消费者 " + Thread.currentThread().getName() + "，当前good = " + good + "， 还剩：" + mGoodsList.size());
                }
            }
        }
    }
}
