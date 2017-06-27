package com.baidu.david.thread;

/**
 * Created by weiwei22 on 17/6/21.
 */

public class Consumer extends Thread {
    private int num;
    private Storage mStorage;

    public Consumer(String name, Storage storage) {
        super(name);
        mStorage = storage;
    }

    @Override
    public void run() {
        mStorage.consume(num);
    }

    public void setNum(int num) {
        this.num = num;
    }
}
