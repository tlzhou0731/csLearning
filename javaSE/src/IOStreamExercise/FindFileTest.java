package IOStreamExercise;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/4
 * Time:20:18
 * Describe:
 */

public class FindFileTest {

    @Test
    public void test(){
        File srcFile = new File("D:\\software\\workTools\\intelliJ\\ideaJava\\projects\\csLearning\\javaSE");

        String[] fileNames = srcFile.list();
        for(String fileName : fileNames){
            if(fileName.endsWith(".jpg")){
                System.out.println(fileName);
            }
        }
    }

    @Test
    public void test2(){
        File srcFile = new File("D:\\software\\workTools\\intelliJ\\ideaJava\\projects\\csLearning\\javaSE");

        File[] listFiles = srcFile.listFiles();
        for(File file : listFiles){
            if(file.getName().endsWith(".jpg")){
                System.out.println(file.getAbsolutePath());
            }
        }
    }

    /**
     * File类提供了两个文件过滤器方法
     * public String[] list(FilenameFilter filter)
     * public File[] listFiles(FileFilter filter)
     */
    @Test
    public void test3(){
        File srcFile = new File("D:\\software\\workTools\\intelliJ\\ideaJava\\projects\\csLearning\\javaSE");

        File[] subFiles = srcFile.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jpg");
            }
        });

        for(File file : subFiles){
            System.out.println(file.getAbsolutePath());
        }
    }

}
