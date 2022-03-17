package multiThreading;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/2
 * Time:20:15
 * Describe:
 */

/**
 * 创建三个窗口卖票，总票数100张
 * 存在线程安全问题
 */
class Window extends Thread{
    private static int ticketNum = 100;
    @Override
    public void run() {
        while (true){
            if(ticketNum>0){
                System.out.println(getName()+":卖票，票号为"+ticketNum);
                ticketNum--;
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                break;
            }
        }
    }
}

public class WindowTest {
    public static void main(String[] args) {
        Window window1 = new Window();
        Window window2 = new Window();
        Window window3 = new Window();
        window1.setName("窗口1");
        window2.setName("窗口2");
        window3.setName("窗口3");
        window1.start();
        window2.start();
        window3.start();
    }
}
