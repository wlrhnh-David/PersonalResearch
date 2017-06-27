package com.baidu.david.thread;

import java.util.LinkedList;

/**
 * Created by weiwei22 on 17/6/21.
 */

public class Storage {
    private static final int MAX_SIZE = 100;
    private LinkedList<String> mList = new LinkedList<>();

    public void produce(int num) {
        synchronized (mList) {
            while (mList.size() + num > MAX_SIZE) {
                System.out.println("【" + Thread.currentThread().getName() + " 要生产的产品数量】:" + num + "\t【库存量】:" + mList.size() + "\t不执行生产任务!");
                try {
                    mList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < num; i++) {
                mList.add("");
            }
            System.out.println("【" + Thread.currentThread().getName() + " 已生产产品数量】:" + num + "\t【现仓储量为】:" + mList.size() + "\t消费者们赶紧来消费吧");

            mList.notifyAll();
        }
    }

    public void consume(int num) {
        synchronized (mList) {
            while (mList.size() < num) {
                System.out.println("【" + Thread.currentThread().getName() + " 要消费的产品数量】:" + num + "\t【库存量】:" + mList.size() + "\t不执行消费任务!");
                try {
                    mList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < num; i++) {
                mList.remove();
            }
            System.out.println("【" + Thread.currentThread().getName() + " 已消费产品数量】:" + num + "\t【现仓储量为】:" + mList.size());

            mList.notifyAll();
        }
    }
}
