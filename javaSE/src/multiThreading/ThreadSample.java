package multiThreading;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/2
 * Time:16:59
 * Describe:
 */

/**
 * 线程的创建和使用
 * 线程的生命周期
 * 线程的同步
 * 线程的通信
 * 新增线程的创建方式
 *
 * 线程方法：
 * yield(); 释放当前线程的CPU执行权
 * join(); 在线程A中调用B的阻塞状态，知道B完全执行完毕
 * sleep(); ms
 * isAlive(); 是否还存活
 * state(); 线程状态
 *
 * 线程的优先级 1——10
 * MAX_PRIORITY
 * MIN_PRIORITY
 * NORM
 * getPriority();
 * setPriority(int n); 获取设置优先级
 * 高优先级只是高概率，不是一定
 */
/**
 * 创建方式1： 创建一个thread类的子类&&创建一个thread类的匿名子类
 * 创建方式2： 声明一个class实现runAble，并实现run方法
 *  两种创建方式比较： 实现的方式天然能够共享数据，不需要指定静态变量
 *  开发中优先选择实现方式
 *  联系： Thread类也是实现了Run able方法
 *
 *  JDK5.0新增的线程创建方式
 *  1. 实现Callable接口
 *      与Runnable相比的优势
 *          可以有返回值
 *          方法可以抛出异常
 *          支持泛型的返回值
 *          需要借助FutureTask类，比如获取返回结果
 *  2. 线程池
 *      好处
 *          提高响应速度（减少了创建新线程的时间）
 *          降低资源消耗（重复利用线程池中线程，不需要每次创建）
 *          便于线程管理
 *              corePoolSize
 *              maximumPoolSize
 *              keepAliveTime
 *
 * 线程通信,定义在Object中
 *  wait();
 *  notify();
 *  notifyAll();
 *
 *  线程分类：守护线程，用户线程
 *  用户线程结束时，守护线程也会结束
 *  GC就是典型的守护线程
 *  thread.setDaemon(true)可以把一个用户线程变为一个守护线程
 *
 *  解决线程安全问题：锁临界资源
 *  即使线程A阻塞也没用
 *
 *  Java中的方法
 *
 *  方法一：同步代码块
 *  synchronized(同步监视器){
 *      //同步监视器：俗称：锁,任何一个类的对象都可以充当锁
 *      锁：要求：多个线程必须要公用同一把锁
 *      //需要同步的代码
 *      //即操作共享数据的方法
 *  }
 *  好处：安全了
 *  坏处：操作同步代码时，只有一个线程参与，效率低
 *  方法二：同步方法
 *  使用同步方法来处理线程安全问题
 *  private synchronized void show(){}
 *  如果是继承的线程类，锁方法要用static
 *
 *  方法三：Lock
 */
class ClassToRun implements Runnable{
    @Override
    public void run() {
        System.out.println("this is a runAble class Object");
    }
}

class ThreadTest extends Thread{
    @Override
    public void run(){
        for (int i = 0; i < 100; i++) {
            if((i&1)==0) {
                System.out.println("this is new thread class");
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
class ThreadTest2 extends Thread{
    @Override
    public void run(){
        for (int i = 0; i < 100; i++) {
            if((i&1)==1){
                System.out.println(Thread.currentThread().getName()+":"+i);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}

public class ThreadSample {

    public static void main(String[] args) {
        ThreadSample threadSample = new ThreadSample();
        ThreadTest threadTest = new ThreadTest();
        threadTest.start();

//        new Thread(){
//            @Override
//            public void run() {
//                for (int i = 0; i < 100; i++) {
//                    if((i&1)==1){
//                        System.out.println(Thread.currentThread().getName()+":"+i);
//                    }
//
//                }
//            }
//        }.start();
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if((i&1)==0){
                        try {
                            System.out.println(Thread.currentThread().getName()+":"+i);
                            sleep(10);
                            if(i==50)
                                threadTest.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }.start();
        new Thread(new ClassToRun()).start();

    }
}
