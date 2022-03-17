package IOStreamExercise;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/5
 * Time:11:29
 * Describe:
 */

import org.junit.Test;

import java.io.*;

/**
 * 其他流的使用
 * 1.标准的输入、输出流
 * 2.打印流
 * 3.数据流
 */

/**
 * System.in和System.out分别代表了系统标准的输入和输出设备
 * 默认输入设备是：键盘，输出设备是：显示器
 * System.in的类型是InputStream
 * System.out的类型是PrintStream，其是OutputStream的子类FilterOutputStream的子类
 * 重定向：通过System类的setIn，setOut方法对默认设备进行改变。
 * public static void setIn(InputStreamin)
 * public static void setOut(PrintStreamout)
 */
public class OtherStreamTest {

    /**
     * 1.标准的输入、输出流
     *   1.1
     *     System.in:标准的输入流，默认从键盘输入
     *     System.out:标准的输出流，默认从控制台输出
     *   1.2
     *     System类的setIn(InputStream is) / setOut(PrintStream ps)方式重新指定输入和输出的流。
     *
     *   1.3练习：
     *     从键盘输入字符串，要求将读取到的整行字符串转成大写输出。然后继续进行输入操作，
     *     直至当输入“e”或者“exit”时，退出程序。
     *
     *   方法一：使用Scanner实现，调用next()返回一个字符串
     *   方法二：使用System.in实现。System.in  --->  转换流 ---> BufferedReader的readLine()
     */
    @Test
    public void test(){
        BufferedReader br = null;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);

            while (true) {
                System.out.println("请输入字符串：");
//                String data = br.readLine();
                String data = br.readLine();
//                String data = "SSSSSSSSSSSSS";
                if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)) {
                    System.out.println("程序结束");
                    break;
                }

                String upperCase = data.toUpperCase();
                System.out.println(upperCase);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * 实现将基本数据类型的数据格式转化为字符串输出
     * 打印流：PrintStream和PrintWriter
     * 提供了一系列重载的print()和println()方法，用于多种数据类型的输出
     * PrintStream和PrintWriter的输出不会抛出IOException异常
     * PrintStream和PrintWriter有自动flush功能
     * PrintStream 打印的所有字符都使用平台的默认字符编码转换为字节。在需要写入字符而不是写入字节的情况下，应该使用PrintWriter 类。
     * System.out返回的是PrintStream的实例
     *
     */

    /**
     * 2. 打印流：PrintStream 和PrintWriter
     *  2.1 提供了一系列重载的print() 和 println()
     *  2.2 练习：
     */
    @Test
    public void test2(){
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream(new File( "D:\\software\\workTools\\intelliJ\\ideaJava\\projects\\csLearning\\javaSE\\num.txt"));
            // 创建打印输出流,设置为自动刷新模式(写入换行符或字节 '\n' 时都会刷新输出缓冲区)
            ps = new PrintStream(fos, true);
            if (ps != null) {// 把标准输出流(控制台输出)改成文件
                System.setOut(ps);
            }

            for (int i = 0; i <= 255; i++) { // 输出ASCII字符
                System.out.print((char) i);
                if (i % 50 == 0) { // 每50个数据一行
                    System.out.println(); // 换行
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    /**
     * 数据流
     *      为了方便地操作Java语言的基本数据类型和String的数据，可以使用数据流。
     *
     *      数据流有两个类：(用于读取和写出基本数据类型、String类的数据）
     *
     *          DataInputStream和DataOutputStream
     *          分别“套接”在InputStream和OutputStream子类的流上
     */

    /**
     * 3.数据流
     *   3.1 DataInputStream 和 DataOutputStream
     *   3.2 作用：用于读取或写出基本数据类型的变量或字符串
     *
     *   练习：将内存中的字符串、基本数据类型的变量写出到文件中。
     *
     *   注意：处理异常的话，仍然应该使用try-catch-finally.
     */
    @Test
    public void test3() throws IOException {
        //1.
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("dataStreamTest.txt"));
        //2.
        dos.writeUTF("刘刚");
        dos.flush();//刷新操作，将内存中的数据写入文件
        dos.writeInt(23);
        dos.flush();
        dos.writeInt(34);
        dos.flush();
        dos.writeBoolean(true);
        dos.flush();
        dos.writeInt(45);
        dos.flush();
        //3.
        dos.close();
    }

    /**
     * 将文件中存储的基本数据类型变量和字符串读取到内存中，保存在变量中。
     *
     * 注意点：读取不同类型的数据的顺序要与当初写入文件时，保存的数据的顺序一致！
     */
    @Test
    public void test4() throws IOException {
        //1.
        DataInputStream dis = new DataInputStream(new FileInputStream("dataStreamTest.txt"));
        //2.
        String name = dis.readUTF();
        int age = dis.readInt();
        int age2 = dis.readInt();
        boolean isMale = dis.readBoolean();
        int age3 = dis.readInt();

        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("age = " + age2);
        System.out.println("isMale = " + isMale);
        System.out.println("age = " + age3);
        //3.
        dis.close();

    }

    /**
     * 对象流
     */
    /**
     * ObjectInputStream和ObjectOutputSteam
     *
     * 用于存储和读取基本数据类型数据或对象的处理流。它的强大之处就是可以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。
     *
     * 序列化：用ObjectOutputStream类保存基本类型数据或对象的机制
     *
     * 反序列化：用ObjectInputStream类读取基本类型数据或对象的机制
     *
     * ObjectOutputStream和ObjectInputStream不能序列化static和transient修饰的成员变量
     *
     * 对象序列化机制允许把内存中的Java对象转换成平台无关的二进制流，从而允许把这种二进制流持久地保存在磁盘上，或通过网络将这种二进制流传输到另一个网络节点。//当其它程序获取了这种二进制流，就可以恢复成原来的Java对象
     *
     * 序列化的好处在于可将任何实现了Serializable接口的对象转化为字节数据，使其在保存和传输时可被还原
     *
     * 序列化是RMI（Remote Method Invoke –远程方法调用）过程的参数和返回值都必须实现的机制，而RMI 是JavaEE的基础。因此序列化机制是JavaEE平台的基础
     *
     * 如果需要让某个对象支持序列化机制，则必须让对象所属的类及其属性是可序列化的，为了让某个类是可序列化的，该类必须实现如下两个接口之一。否则，会抛出NotSerializableException异常
     *      Serializable
     *      Externalizable
     */

    /**
     * 序列化过程：将内存中的java对象保存到磁盘中或通过网络传输出去
     * 使用ObjectOutputStream实现
     */
    @Test
    public void objectTest1(){
        ObjectOutputStream oos = null;
        try {
            //创造流
            oos = new ObjectOutputStream(new FileOutputStream("src/resource/objectTest.dat"));

            //制造对象
            oos.writeObject(new String("秦始皇陵欢迎你"));

            //刷新操作
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(oos != null){
                //3.关闭流
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 反序列化：将磁盘文件中的对象还原为内存中的一个java对象
     * 使用ObjectInputStream来实现
     */
    @Test
    public void objectTest2(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("src/resource/objectTest.dat"));

            Object obj = ois.readObject();
            String str = (String) obj;

            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 序列化过程：将内存中的java对象保存到磁盘中或通过网络传输出去
     * 使用ObjectOutputStream实现
     */
    @Test
    public void objectTest3(){
        ObjectOutputStream oos = null;
        try {
            //创造流
            oos = new ObjectOutputStream(new FileOutputStream("src/resource/objectTest2.dat"));
            //制造对象
            oos.writeObject(new String("秦始皇陵欢迎你"));
            //刷新操作
            oos.flush();

            oos.writeObject(new Person("李时珍",65,0));
            oos.flush();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(oos != null){
                //3.关闭流
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 反序列化：将磁盘文件中的对象还原为内存中的一个java对象
     * 使用ObjectInputStream来实现
     */
    @Test
    public void objectTest4(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("src/resource/objectTest2.dat"));

            Object obj = ois.readObject();
            String str = (String) obj;

            Person p = (Person) ois.readObject();

            System.out.println(str);
            System.out.println(p);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
















}
