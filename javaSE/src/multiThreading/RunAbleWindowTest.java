package multiThreading;

import sun.java2d.loops.GraphicsPrimitive;

import static java.lang.Thread.sleep;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/2
 * Time:20:33
 * Describe:
 * 仍然存在线程安全问题
 */

class RunAbleWindow implements Runnable{
    private static int ticketNum = 100;
    @Override
    public void run() {
        while (true)
            show();
    }
    private synchronized void show(){
        if (ticketNum > 0) {
            System.out.println(Thread.currentThread().getName() + ":卖票:票号" + ticketNum);
            ticketNum--;
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class RunAbleWindowTest {
    public static void main(String[] args) {
        RunAbleWindow runAbleWindow = new RunAbleWindow();
        Thread thread1 = new Thread(runAbleWindow);
        Thread thread2 = new Thread(runAbleWindow);
        Thread thread3 = new Thread(runAbleWindow);
        thread1.setName("窗口1");
        thread2.setName("窗口2");
        thread3.setName("窗口3");
        thread1.start();
        thread2.start();
        thread3.start();


    }
}
