package com.baidu.david.thread;

/**
 * Created by weiwei22 on 17/6/21.
 */

public class Producer extends Thread {
    private int num;
    private Storage mStorage;

    public Producer(String name, Storage storage) {
        super(name);
        mStorage = storage;
    }

    @Override
    public void run() {
        mStorage.produce(num);
    }

    public void setNum(int num) {
        this.num = num;
    }
}
