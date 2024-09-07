package org.example.multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance = 100;

    private final Lock lock = new ReentrantLock();

    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " is trying to withdraw" + amount);

        try {
            if(lock.tryLock(2, TimeUnit.SECONDS)) {
                if (balance >= amount) {
                    try {
                    System.out.println(Thread.currentThread().getName() + " is proceeding to withdraw");
                    Thread.sleep(3000); // To simulate the time taken to withdraw the amount
                    balance -= amount;
                } catch (Exception e){
                        e.printStackTrace();
                        System.out.println("Error in withdrawing the amount");
                    } finally {
                        lock.unlock();
                }
            } else {
                System.out.println("Insufficient balance");
            }
        } else {
                System.out.println("Could not acquire lock");
            }
    } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        // Storing the state of the thread
        if(Thread.currentThread().isInterrupted()) {
            System.out.println("Thread interrupted");
        }
    }
}
