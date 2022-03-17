package networkExercise;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/5
 * Time:17:28
 * Describe:
 */

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * UDPd协议的网络编程
 */
public class UDPTest {

    /**
     * 类DatagramSocket和DatagramPacket实现了基于UDP 协议网络程序。
     * UDP数据报通过数据报套接字DatagramSocket发送和接收，系统不保证UDP数据报一定能够安全送到目的地，也不能确定什么时候可以抵达。
     * DatagramPacket 对象封装了UDP数据报，在数据报中包含了发送端的IP地址和端口号以及接收端的IP地址和端口号。
     * UDP协议中每个数据报都给出了完整的地址信息，因此无须建立发送方和接收方的连接。如同发快递包裹一样。
     * 流程：
     * DatagramSocket与DatagramPacket
     * 建立发送端，接收端
     * 建立数据包
     * 调用Socket的发送、接收方法
     * 关闭Socket
     * @throws IOException
     */
    //发送端
    @Test
    public void sender() throws IOException {
        DatagramSocket socket = new DatagramSocket(9988);

        String str = "我是UDP发送端";
        byte[] data = str.getBytes();
        InetAddress inet = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(data,0,data.length,inet,9099);
        socket.send(packet);

        byte[] buffer = new byte[100];
        DatagramPacket receive = new DatagramPacket(buffer,0,buffer.length);
        socket.receive(receive);
        System.out.println(new String(receive.getData(),0,receive.getLength()));

        socket.close();
    }

    //接收端
    @Test
    public void receiver() throws IOException {
        DatagramSocket socket = new DatagramSocket(9099);

        byte[] buffer = new byte[100];
        DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);
        socket.receive(packet);
        System.out.println(new String(packet.getData(),0,packet.getLength()));

        String receive = "已经接受";
        byte[] toSend = receive.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(toSend,0,toSend.length,InetAddress.getLocalHost(),9988);
        socket.send(sendPacket);
        System.out.println(new String(packet.getData(),0,packet.getLength()));

        socket.close();
    }
}
