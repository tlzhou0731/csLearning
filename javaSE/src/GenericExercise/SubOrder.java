package GenericExercise;


/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/4
 * Time:13:20
 * Describe:
 */

import java.util.ArrayList;
import java.util.List;

public class SubOrder extends OrderTest<Integer>{   //SubOrder:不是泛型类

    public static <E> List<E> copyFromArrayToList(E[] arr){//静态的泛型方法

        ArrayList<E> list = new ArrayList<>();

        for(E e : arr){
            list.add(e);
        }
        return list;

    }
}

