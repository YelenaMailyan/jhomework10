package com.company;

public class MyDeadLock {
    final String obj1 = "Object1";
    final Integer obj2 = 1;

    Thread firstThread = new Thread(() -> {
        System.out.println("Starting " + Thread.currentThread().getName());

        synchronized (obj1) {
            System.out.println("Locked obj1 by " + Thread.currentThread().getName());

            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
            }

            synchronized (obj2) {
                System.out.println(Thread.currentThread().getName() + " try to lock obj2  ");
            }
        }
    });

    Thread secondThread = new Thread(() -> {
        System.out.println("Starting " + Thread.currentThread().getName());
        synchronized (obj2) {
            System.out.println("Lock obj2 by " + Thread.currentThread().getName());

            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
            }
            synchronized (obj1) {
                System.out.println("locked obj1 by " + Thread.currentThread().getName());
            }
        }
    });
}