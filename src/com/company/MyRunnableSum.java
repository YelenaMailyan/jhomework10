package com.company;

import java.util.concurrent.atomic.AtomicInteger;

public class MyRunnableSum implements Runnable {
    int numberFrom;
    int numberTo;
    AtomicInteger sum;

    public MyRunnableSum(int numberFrom, int numberTo) {
        this.numberFrom = numberFrom;
        this.numberTo = numberTo;
        this.sum = new AtomicInteger();

    }

    public AtomicInteger getSum() {
        return this.sum;
    }


    public void run() {
        for (int i = this.numberFrom; i <= this.numberTo; i++) {
            int newValue = this.sum.addAndGet(i);
            try {
                this.sum.set(newValue);
            } catch (Exception ex) {
            }
        }

        System.out.println(Thread.currentThread().getId() + ". Numbers from " + this.numberFrom + " - " +
                this.numberTo + " amount is " + sum + " currentTime - " + System.currentTimeMillis());

    }

}
