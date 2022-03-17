package CollectionExercises;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/4
 * Time:10:55
 * Describe:
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {
    //Properties:常用来处理配置文件。key和value都是String类型
    public static void main(String[] args){
        FileInputStream fileInputStream = null;
        try{
            Properties properties = new Properties();
            fileInputStream = new FileInputStream("D:\\software\\workTools\\intelliJ\\ideaJava\\projects\\csLearning\\javaSE\\jdbc.properties");
//            fileInputStream = new FileInputStream("jdbc.properties");
            properties.load(fileInputStream);

            String name = properties.getProperty("name");
            String password = properties.getProperty("password");
            System.out.println(name+":"+password);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                }catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
    }
}

