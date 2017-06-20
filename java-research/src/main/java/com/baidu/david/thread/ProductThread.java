package com.baidu.david.thread;

import java.util.List;

/**
 * Created by weiwei22 on 17/6/21.
 */

public class ProductThread extends Thread {
    private List<String> mGoodsList;
    private int index;

    public ProductThread(List<String> goodsList) {
        mGoodsList = goodsList;
    }

    @Override
    public void run() {
        synchronized (mGoodsList) {
            while (true) {
                System.out.println("我是生产者=================");

                if (mGoodsList.size() >= 8) {
                    System.out.println("我是生产者，消费者呢，妈的赶紧吃 ）-（ ");
                    System.out.println();
                    System.out.println();

                    mGoodsList.notifyAll();

                    index = 0;

                    try {
                        mGoodsList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    index++;
                    String good = "土豆-" + index;
                    mGoodsList.add(good);
                    System.out.println("我是生产者，生产good = " + good + "， 总共：" + mGoodsList.size());
                }
            }
        }
    }
}
