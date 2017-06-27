package com.baidu.david.thread;

import com.baidu.david.util.SystemUtil;

/**
 * Created by weiwei22 on 17/6/26.
 */

public class ThreadMain3 {
    private static Object mLocker = new Object();

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }

    private static class Thread1 extends Thread {
        private int index;
        @Override
        public void run() {
            synchronized (mLocker) {
                while(index <= 100) {
                    index++;
                    SystemUtil.p("线程1: 执行 index = " + index);

                    if (index % 20 == 0) {
                        try {
                            mLocker.notifyAll();

                            SystemUtil.p("线程1: ***等待*** index = " + index);
                            mLocker.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    SystemUtil.p("线程1: 结束 index = " + index);
                }
            }
        }
    }

    private static class Thread2 extends Thread {
        int index;
        @Override
        public void run() {
            synchronized (mLocker) {
                while(index <= 100) {
                    index++;
                    SystemUtil.p("-------线程2: 执行 index = " + index);
                    if (index % 10 == 0) {
                        mLocker.notify();

                        try {
                            mLocker.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        /*try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
//                        Thread.yield();
                    }
                    SystemUtil.p("-------线程2: 结束 index = " + index);
                }
            }
        }
    }
}
