package org.example.multithreading;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InterruptibleLockExample {
    private final Lock lock = new ReentrantLock();

    public void performTask() {
        try {
            // Blocked waiting for the lock indeifinitely
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + " acquired the lock");
                // Simulate some work with the lock held
                Thread.sleep(2000);
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " released the lock");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted");
        }
    }

    public static void main(String[] args) {
        InterruptibleLockExample example = new InterruptibleLockExample();

        Runnable task = example::performTask;
//        // Same line as above
//        Runnable task = new Runnable() {
//            @Override
//            public void run() {
//                example.performTask();
//            }  
//        };

//        Equivalent of above using lambda
//        Runnable task = () -> example.performTask();

        Thread t1 = new Thread(task, "Thread1");
        Thread t2 = new Thread(task, "Thread2");

        t1.start();
        t2.start();

        // Interrupt t2 to demonstrate lockInterruptibly behavior
        t2.interrupt();
    }
}
// Using Streams API
//public static void main(String[] args) {
//    InterruptibleLockExample example = new InterruptibleLockExample();
//
//    List<Thread> threads = Stream.of("Thread1", "Thread2")
//            .map(name -> new Thread(() -> example.performTask(), name))
//            .toList();
//
//    threads.forEach(Thread::start);
//
//    // Interrupt the second thread to demonstrate lockInterruptibly behavior
//    threads.get(1).interrupt();
//}
