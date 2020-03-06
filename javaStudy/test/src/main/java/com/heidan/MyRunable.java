package com.heidan;

/**
 * Create by heidan on 2020/2/2 15:45
 */

public class MyRunable implements Runnable {

    @Override
    public void run() {
        for (int i =0;i<20;i++){
            System.out.println("run:"+ i);
        }
    }
}
