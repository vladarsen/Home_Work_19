package com.vladarsenjtev;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PetrolStation {

    private double amount = 2000;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private Semaphore semaphore = new Semaphore(3);

    public synchronized void doRefuel(int value) {
        try {
            semaphore.acquire();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                lock.writeLock().lock();
                this.amount = amount - value;
            } finally {
                lock.writeLock().unlock();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public synchronized double getAmount() {
        try {
            lock.readLock().lock();
            return amount;
        } finally {
            lock.readLock().unlock();
        }
    }
}
