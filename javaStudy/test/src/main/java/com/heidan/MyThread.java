package com.heidan;

/**
 * Create by heidan on 2020/2/2 15:02
 */

public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0;i<20;i++){
            System.out.println("run:"+ i);
        }
    }
}
