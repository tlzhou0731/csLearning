package ReflectionExercise;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/6
 * Time:17:26
 * Describe:
 */

import java.io.Serializable;

public abstract class Creature <T> implements Serializable {
    private char gender;
    public double weight;

    private void breath(){
        System.out.println("太阳系");
    }

    public void eat(){
        System.out.println("银河系");
    }
}
