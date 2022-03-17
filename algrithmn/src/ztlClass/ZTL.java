package ztlClass;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/1/22
 * Time:22:53
 * Describe:
 */

public class ZTL {
    public static int[] distinct(int nums[]){
        Set<Integer> set = new HashSet<>();
        for(int num:nums)
            set.add(num);
        int n = set.size(), i=0;
        int res[] = new int[n];
        Iterator<Integer> iterator = set.iterator();
        while(iterator.hasNext())
            res[i++] = iterator.next();
        return res;
    }
    public static void swap(int ai, int bi) {
        Integer a = new Integer(ai);
        Integer b = new Integer(bi);
        if (a == null || b == null) {
            return;
        }
        //获得a的class对象
        Class<Integer> integerClass = (Class<Integer>) a.getClass();
        try {
            //获得value属性
            Field value=integerClass.getDeclaredField("value");
            //设置权限为可访问
            value.setAccessible(true);
            //交换
            int temp=a;
            value.setInt(a,b);
            value.setInt(b,temp);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int a=1, b=2;
        swap(a,b);
        System.out.println(a+" "+b);
    }
}
