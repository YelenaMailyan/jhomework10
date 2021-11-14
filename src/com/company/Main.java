package com.company;

import java.util.ArrayList;

public class Main {

    public static void waitFor(ArrayList<Thread> threads)
            throws InterruptedException {
        for (Thread t : threads) {
            t.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 1.Write Java Program That counts sum of the numbers from 1 to 10000000
        //	Single Thread, MultiThread
        int numberOfThreads = 3;
        int start = 5;
        int end = 10000;
        int step = ( int ) Math.floor(( double ) end / numberOfThreads);
        ArrayList<Thread> threads = new ArrayList<>();
        ArrayList<MyRunnableSum> myRunnableSums = new ArrayList<MyRunnableSum>();

        int totalAmount = 0;
        for (int i = 1; i <= numberOfThreads; i++) {
            int numberFrom = i == 1 ? (i - 1) * step + start : (i - 1) * step + 1;
            int numberTo = i == numberOfThreads ? end : step * i;
            MyRunnableSum myRunnableSum = new MyRunnableSum(numberFrom, numberTo);
            Thread temp = new Thread(myRunnableSum);
            threads.add(temp);
            temp.start();
            myRunnableSums.add(myRunnableSum);
        }
        waitFor(threads);
        for (MyRunnableSum t : myRunnableSums
        ) {
            totalAmount = totalAmount + t.getSum().intValue();
        }
        System.out.println("Total sum amount of threads is " + totalAmount);


        // 3. Make a DeadLock
        //If thread 1 locks A, and tries to lock B, and thread 2 has already locked B,
        // and tries to lock A, a deadlock arises. Thread 1 can never get B, and thread 2 can never get A.
        // In addition, neither of them will ever know.
        // They will remain blocked on each their object, A and B, forever. This situation is a deadlock

        MyDeadLock dLock = new MyDeadLock();
        dLock.firstThread.start();
        dLock.secondThread.start();


    }
}

