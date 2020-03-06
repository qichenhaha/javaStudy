package com.sys;

/**
 * Create by heidan on 2020/2/2 16:25
 */

public class Demo {
    public static void main(String[] args) {
        MyRunable myRunable = new MyRunable();

        Thread thread = new Thread(myRunable);
        Thread thread1 = new Thread(myRunable);
        Thread thread2 = new Thread(myRunable);
        thread.start();
        thread1.start();
        thread2.start();
    }


}
