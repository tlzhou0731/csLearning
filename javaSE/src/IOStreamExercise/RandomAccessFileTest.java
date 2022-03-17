package IOStreamExercise;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/5
 * Time:15:52
 * Describe:
 */

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *  随机存取文件流
     * RandomAccessFile 声明在java.io包下，但直接继承于java.lang.Object类。并且它实现了DataInput、DataOutput这两个接口，也就意味着这个类既可以读也可以写。

     * RandomAccessFile 类支持“随机访问” 的方式，程序可以直接跳到文件的任意地方来读、写文件
        * 支持只访问文件的部分内容
         * 可以向已存在的文件后追加内容

     * RandomAccessFile 对象包含一个记录指针，用以标示当前读写处的位置。RandomAccessFile类对象可以自由移动记录指针：
        * long getFilePointer()：获取文件记录指针的当前位置
        * void seek(long pos)：将文件记录指针定位到pos位置

     * 构造器
        * public RandomAccessFile(Filefile, Stringmode)
        * public RandomAccessFile(Stringname, Stringmode)

     * 创建RandomAccessFile类实例需要指定一个mode 参数，该参数指定RandomAccessFile的访问模式：
        * r: 以只读方式打开
        * rw：打开以便读取和写入
        * rwd:打开以便读取和写入；同步文件内容的更新
        * rws:打开以便读取和写入；同步文件内容和元数据的更新

     * 如果模式为只读r。则不会创建文件，而是会去读取一个已经存在的文件，如果读取的文件不存在则会出现异常。如果模式为rw读写。如果文件不存在则会去创建文件，如果存在则不会创建。
 *
 */


/**
 * RandomAccessFile的使用
 * 1.RandomAccessFile直接继承于java.lang.Object类，实现了DataInput和DataOutput接口
 * 2.RandomAccessFile既可以作为一个输入流，又可以作为一个输出流
 * 3.如果RandomAccessFile作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建。
 *   如果写出到的文件存在，则会对原有文件内容进行覆盖。（默认情况下，从头覆盖）
 */
public class RandomAccessFileTest {

    @Test
    public void test(){

        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            raf1 = new RandomAccessFile(new File("src/resource/aaa.jpg"),"r");
            raf2 = new RandomAccessFile(new File("src/resource/ggg.jpg"),"rw");

            byte[] buffer = new byte[1024];
            int len;
            while((len = raf1.read(buffer)) != -1){
                raf2.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(raf1 != null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(raf2 != null){
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void test2() throws IOException {

        RandomAccessFile raf1 = new RandomAccessFile("src/resource/haha.txt","rw");

        raf1.write("xyz".getBytes());

        raf1.close();

    }


    /**
     * RandomAccessFile的使用
     * 1.RandomAccessFile直接继承于java.lang.Object类，实现了DataInput和DataOutput接口
     * 2.RandomAccessFile既可以作为一个输入流，又可以作为一个输出流
     * 3.如果RandomAccessFile作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建。
     *   如果写出到的文件存在，则会对原有文件内容进行覆盖。（默认情况下，从头覆盖）
     *
     * 4.可以通过相关的操作，实现RandomAccessFile“插入”数据的效果
     */
    /**
     * 使用RandomAccessFile实现数据的插入效果
     */
    @Test
    public void test3() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("src/resource/haha.txt","rw");

        raf1.seek(3);//将指针调到角标为3的位置
        //保存指针3后面的所有数据到StringBuilder中
        StringBuilder builder = new StringBuilder((int) new File("src/resource/haha.txt").length());
        byte[] buffer = new byte[20];
        int len;
        while((len = raf1.read(buffer)) != -1){
            builder.append(new String(buffer,0,len)) ;
        }
        //调回指针，写入“xyz”
        raf1.seek(3);
        raf1.write("xyz".getBytes());

        //将StringBuilder中的数据写入到文件中
        raf1.write(builder.toString().getBytes());

        raf1.close();

        //思考：将StringBuilder替换为ByteArrayOutputStream
    }

}
