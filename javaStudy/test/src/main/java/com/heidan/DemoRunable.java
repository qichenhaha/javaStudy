package com.heidan;

/**
 * Create by heidan on 2020/2/2 15:46
 */

public class DemoRunable {

    public static void main(String[] args) {
        MyRunable myRunable = new MyRunable();
        Thread thread = new Thread(myRunable);
        thread.start();

        for (int i =0;i<20;i++){
            System.out.println("main:"+ i);
        }

    }
}
