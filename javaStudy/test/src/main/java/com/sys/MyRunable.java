package com.sys;

/**
 * Create by heidan on 2020/2/2 15:45
 */

public class MyRunable implements Runnable {

    private int number = 100;

    Object object = new Object();
    @Override
    public void run() {
        while (true){
            synchronized (object){
                if (number>0){
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"-->正在卖第"+number+"张票！");
                    number--;
                }
            }

        }
    }
}
