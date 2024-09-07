package org.example.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
    private final Lock lock = new ReentrantLock();

    public void outerMethod() {
        lock.lock();
        try {
            System.out.println("Outer method");
            innerMethod();
        } finally {
            lock.unlock();
        }
    }

    public void innerMethod() {
        // Since the lock is reentrant, the same thread can acquire the lock again [this is advantage of Reentrant Lock]
        lock.lock();
        try{
            System.out.println("Inner method");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantExample reentrantExample = new ReentrantExample();
        reentrantExample.outerMethod();
    }
}

// different methods of ReentrantLock
// 1. lock() - Acquires the lock if it is not held by another thread and returns immediately, setting the lock hold count to 1.
// 2. lockInterruptibly() - Acquires the lock unless the current thread is interrupted.
// 3. tryLock() - Acquires the lock only if it is not held by another thread at the time of invocation.
// 4. tryLock(long timeout, TimeUnit unit) - Acquires the lock if it is not held by another thread within the given waiting time and the current thread has not been interrupted.
//5. unlock() - Releases the lock. And decrease the lock hold count. If the hold count reaches zero then the lock is released.
// 6. getHoldCount() - Returns the number of holds on this lock by the current thread.
///7. isHeldByCurrentThread() - Queries if this lock is held by the current thread.
//8. isLocked() - Queries if this lock is held by any thread.
// 9. hasQueuedThreads() - Queries if there are any threads waiting to acquire this lock.
//10. getQueueLength() - Returns an estimate of the number of threads waiting to acquire this lock.
