package IOStreamExercise;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/4
 * Time:15:26
 * Describe:
 */

import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * File类的使用
 *
 * 1. File类的一个对象，代表一个文件或一个文件目录(俗称：文件夹)
 * 2. File类声明在java.io包下
 *
 */
public class FileTest {
    /**
     * 1.如何创建file类的实例
     *      File(String filePath):以filePath为路径创建File对象，可以是绝对路径或者相对路径
     *      File(String parentPath,String childPath):以parentPath为父路径，childPath为子路径创建File对象。
     *      File(File parentFile,String childPath):根据一个父File对象和子文件路径创建File对象
     * 2.
     *   相对路径：相较于某个路径下，指明的路径。
     *   绝对路径：包含盘符在内的文件或文件目录的路径
     *
     * 3.路径分隔符
     *      windows:\\
     *      unix:/
     * 4.Java程序支持跨平台运行，因此路径分隔符要慎用。
     *
     * 5.为了解决这个隐患，File类提供了一个常量：
     *   public  static final String separator。
     *   根据操作系统，动态的提供分隔符。
     *
     * File file1= new File("d:\\Work\\info.txt");
     * File file2= new File("d:"+ File.separator+ "Work"+ File.separator+ "info.txt");
     * File file3= new File("d:/Work");
     *
     */
    /**
     * File 的本质还是目录，到了文件才会读文件
     */
    @Test
    public void test(){
        //构造器1：
        File file1 = new File("hello.txt");//相对于当前module
        File file2 = new File("D:\\software\\workTools\\intelliJ\\ideaJava\\projects\\csLearning\\javaSE\\num.txt");

        System.out.println(file1);
        System.out.println(file1.getAbsoluteFile());
        System.out.println(file2);

        //构造器2：
        File file3 = new File("D:\\software\\workTools\\intelliJ\\ideaJava\\projects\\csLearning","JavaSE");
        System.out.println(file3);

        //构造器3：
        File file4 = new File(file3,"hello.txt");
        System.out.println(file4);
    }

    /**
     * public String getAbsolutePath()：获取绝对路径
     * public String getPath() ：获取路径
     * public String getName() ：获取名称
     * public String getParent()：获取上层文件目录路径。若无，返回null
     * public long length() ：获取文件长度（即：字节数）。不能获取目录的长度。
     * public long lastModified() ：获取最后一次的修改时间，毫秒值
     *
     * 如下的两个方法适用于文件目录：
     * public String[] list() ：获取指定目录下的所有文件或者文件目录的名称数组
     * public File[] listFiles() ：获取指定目录下的所有文件或者文件目录的File数组
     */
    @Test
    public void test2(){
        File file = new File("hello.txt");
        File file2 = new File("D:\\software\\workTools\\intelliJ\\ideaJava\\projects\\csLearning\\javaSE\\num.txt");

        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.length());
        System.out.println(new Date(file.lastModified()));

        System.out.println();

        System.out.println(file2.getAbsolutePath());
        System.out.println(file2.getPath());
        System.out.println(file2.getName());
        System.out.println(file2.getParent());
        System.out.println(file2.length());
        System.out.println(new Date(file2.lastModified()));
    }

    @Test
    public void test3(){
        //文件需存在！！！
        File file = new File("D:\\software\\workTools\\intelliJ\\ideaJava\\projects\\csLearning","JavaSE");

        String[] list = file.list();
        for(String s : list){
            System.out.println(s);
        }

        System.out.println();

        File[] files = file.listFiles();
        for(File f : files){
            System.out.println(f);
        }
    }

    /**
     * File类的重命名功能
     *
     *  public boolean renameTo(File dest):把文件重命名为指定的文件路径
     *    比如：file1.renameTo(file2)为例：
     *         要想保证返回true,需要file1在硬盘中是存在的，且file2不能在硬盘中存在。
     */
    @Test
    public void test4(){
        File file1 = new File("hello.txt");
        File file2 = new File("D:\\software\\workTools\\intelliJ\\ideaJava\\projects\\csLearning\\javaSE\\numNew.txt");

        System.out.println(file1.getAbsoluteFile());

        boolean renameTo = file2.renameTo(file1);
        System.out.println(renameTo);
    }

    /**
     * /**
     *  * File类的使用
     *  *
     *  * 1. File类的一个对象，代表一个文件或一个文件目录(俗称：文件夹)
     *  * 2. File类声明在java.io包下
     *  * 3. File类中涉及到关于文件或文件目录的创建、删除、重命名、修改时间、文件大小等方法，
     *  *    并未涉及到写入或读取文件内容的操作。如果需要读取或写入文件内容，必须使用IO流来完成。
     *  * 4. 后续File类的对象常会作为参数传递到流的构造器中，指明读取或写入的"终点".
     *
     */
    /**
     * public boolean isDirectory()：判断是否是文件目录
     * public boolean isFile() ：判断是否是文件
     * public boolean exists() ：判断是否存在
     * public boolean canRead() ：判断是否可读
     * public boolean canWrite() ：判断是否可写
     * public boolean isHidden() ：判断是否隐藏
     */
    @Test
    public void test5(){
        File file1 = new File("hello.txt");
        file1 = new File("hello.txt");

        System.out.println(file1.isDirectory());
        System.out.println(file1.isFile());
        System.out.println(file1.exists());
        System.out.println(file1.canRead());
        System.out.println(file1.canWrite());
        System.out.println(file1.isHidden());

        System.out.println();

        File file2 = new File("D:\\book");
        file2 = new File("D:\\software");
        System.out.println(file2.isDirectory());
        System.out.println(file2.isFile());
        System.out.println(file2.exists());
        System.out.println(file2.canRead());
        System.out.println(file2.canWrite());
        System.out.println(file2.isHidden());
    }

    /**
     * 创建硬盘中对应的文件或文件目录
     * public boolean createNewFile() ：创建文件。若文件存在，则不创建，返回false
     * public boolean mkdir() ：创建文件目录。如果此文件目录存在，就不创建了。如果此文件目录的上层目录不存在，也不创建。
     * public boolean mkdirs() ：创建文件目录。如果此文件目录存在，就不创建了。如果上层文件目录不存在，一并创建
     *
     *     删除磁盘中的文件或文件目录
     * public boolean delete()：删除文件或者文件夹
     *     删除注意事项：Java中的删除不走回收站。
     */
    @Test
    public void test6() throws IOException {
        File file1 = new File("hi.txt");
        if(!file1.exists()){
            //文件的创建
            file1.createNewFile();
            System.out.println("创建成功");
        }else{//文件存在
            file1.delete();
            System.out.println("删除成功");
        }
    }

    @Test
    public void test7(){
        //文件目录的创建
        File file1 = new File("D:\\software\\workTools\\intelliJ\\ideaJava\\projects\\csLearning\\javaSE\\FileTemp\\fi3");

        boolean mkdir = file1.mkdir();
        if(mkdir){
            System.out.println("创建成功1");
        }

        File file2 = new File("D:\\software\\workTools\\intelliJ\\ideaJava\\projects\\csLearning\\javaSE\\FileTemp\\fi3");

        boolean mkdir1 = file2.mkdirs();
        if(mkdir1){
            System.out.println("创建成功2");
        }
        //要想删除成功，io4文件目录下不能有子目录或文件
        File file3 = new File("D:\\software\\workTools\\intelliJ\\ideaJava\\projects\\csLearning\\javaSE\\FileTemp\\fi3");
        file3 = new File("D:\\software\\workTools\\intelliJ\\ideaJava\\projects\\csLearning\\javaSE\\FileTemp\\fi3");
        File file4 = new File("D:\\software\\workTools\\intelliJ\\ideaJava\\projects\\csLearning\\javaSE\\FileTemp");
        System.out.println(file3.delete());
        System.out.println(file4.delete());
    }

    @Test
    public void test8() throws IOException {
        File file = new File("D:\\software\\workTools\\intelliJ\\ideaJava\\projects\\csLearning\\javaSE\\hello.txt");
        //创建一个与file同目录下的另外一个文件，文件名为：haha.txt
        File destFile = new File(file.getParent(),"haha.txt");
        boolean newFile = destFile.createNewFile();
        if(newFile){
            System.out.println("创建成功！");
        }
    }












}
