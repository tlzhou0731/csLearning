package IOStreamExercise;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/5
 * Time:16:17
 * Describe:
 */

public class FileUtilsTest {
    public static void main(String[] args) {
        File srcFile = new File("JavaSE/src/resource/haha.txt");
        File destFile = new File("JavaSE/src/resource/hahaCopy.txt");
        try {
            FileUtils.copyFile(srcFile,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
