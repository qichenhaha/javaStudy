package com.heidan;

/**
 * Create by heidan on 2020/2/2 15:03
 */

public class DemoThread {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        for (int i = 0;i<20;i++){
            System.out.println("main:"+ i);
        }
    }
}
