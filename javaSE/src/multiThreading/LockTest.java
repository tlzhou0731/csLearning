package multiThreading;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/3
 * Time:9:41
 * Describe:
 */

class WindowLock implements Runnable{

    private int ticket = 100;
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            try {
                lock.lock();
                if (ticket > 0) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName()+":售票，票号为"+ticket);
                    ticket--;
                }else {
                    break;
                }
            }finally {
                lock.unlock();
            }
        }
    }
}

public class LockTest {
    public static void main(String[] args) {
        WindowLock windowLock =  new WindowLock();
        Thread t1 = new Thread(windowLock);
        Thread t2 = new Thread(windowLock);
        Thread t3 = new Thread(windowLock);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();

    }
}
