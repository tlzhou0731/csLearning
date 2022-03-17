package multiThreading;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/2
 * Time:21:51
 * Describe:使用同步机制将单例模式中的懒汉式改写成线程安全的
 */

class Bank{
    private static Bank instance = null;
    private Bank(){
        super();
    }

    //直接synchronized效率差
    public static synchronized Bank getInstance(){
        if(instance == null){
            //这样效率高
            synchronized (Bank.class){
                if(instance == null){
                    instance = new Bank();
                }
            }
        }
        return instance;
    }

}

class BankRun implements Runnable{

    @Override
    public void run() {

    }
}

public class BankTest {
    public static void main(String[] args) {

    }
}
