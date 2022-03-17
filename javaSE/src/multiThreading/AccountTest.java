package multiThreading;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/3
 * Time:9:51
 * Describe:
 */

class Account{
    private double balance;
    private ReentrantLock lock = new ReentrantLock();
    public Account(double balance){
        this.balance = balance;
    }
    public void deposit(double amt){
        if (amt>0){
            try {
                sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
            lock.lock();
            balance+=amt;
            System.out.println(Thread.currentThread().getName()+"存钱成功：账户余额："+balance);
            lock.unlock();
        }
    }

}

class Customer implements Runnable{
    private Account account;
    public Customer(Account account){
        this.account = account;
    }

    @Override
    public void run() {
        for(int i=0;i<3;i++){
            account.deposit(1000);
        }
    }
}

public class AccountTest {
    public static void main(String[] args) {
        Account acct = new Account(0);
        Customer c1 = new Customer(acct);
        Customer c2 = new Customer(acct);
        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);

        t1.setName("甲");
        t2.setName("乙");

        t1.start();
        t2.start();
    }
}
