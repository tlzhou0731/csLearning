package GenericExercise;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/4
 * Time:13:22
 * Describe:
 */

import org.junit.Test;

import java.util.List;

/**
 * 如何自定义泛型结构：泛型类、泛型接口；泛型方法。
 *
 * 1.关于自定义泛型类、泛型接口：
 */

/**
 * 泛型类可能有多个参数，此时应将多个参数一起放在尖括号内。比如：<E1,E2,E3>
 *
 * 泛型类的构造器如下：public GenericClass(){}。而下面是错误的：public GenericClass(){}
 *
 * 实例化后，操作原来泛型位置的结构必须与指定的泛型类型一致。
 *
 * 泛型不同的引用不能相互赋值。尽管在编译时ArrayList和ArrayList是两种类型，但是，在运行时只有一个ArrayList被加载到JVM中。
 *
 * 泛型如果不指定，将被擦除，泛型对应的类型均按照Object处理，但不等价于Object。
 * 经验：泛型要使用一路都用。要不用，一路都不要用。
 *
 * 如果泛型结构是一个接口或抽象类，则不可创建泛型类的对象。
 *
 * jdk1.7，泛型的简化操作：ArrayList flist = new ArrayList<>();
 *
 * 泛型的指定中不能使用基本数据类型，可以使用包装类替换。
 *
 * 在类/接口上声明的泛型，在本类或本接口中即代表某种类型，可以作为非静态属性的类型、非静态方法的参数类型、非静态方法的返回值类型。但在静态方法中不能使用类的泛型。
 *
 * 异常类不能是泛型的
 */

public class GenericTest1 {
    /**
     * 如果定义了泛型类，实例化没有指明类的泛型，则认为此泛型类型为Object类型
     * 要求：如果大家定义了类是带泛型的，建议在实例化时要指明类的泛型。
     */
    @Test
    public void test(){
        OrderTest orderTest = new OrderTest();
        orderTest.setOrderT(123);
        orderTest.setOrderT("ABC");

        //建议：实例化时指明类的泛型
        OrderTest<String> order1 = new OrderTest<String>("orderAA",1001,"order:AA");

        order1.setOrderT("AA:hello");

    }

    @Test
    public void test2(){
        SubOrder sub1 = new SubOrder();
        //由于子类在继承带泛型的父类时，指明了泛型类型。则实例化子类对象时，不再需要指明泛型。
        sub1.setOrderT(1122);

        SubOrder1<String> sub2 = new SubOrder1<>();
        sub2.setOrderT("order2...");
    }

    //测试泛型方法
    @Test
    public void test4(){
        OrderTest<String> order = new OrderTest<>();
        Integer[] arr = new Integer[]{1,2,3,4};
        //泛型方法在调用时，指明泛型参数的类型。
        List<Integer> list = order.copyFromArrayToList(arr);

        System.out.println(list);
    }









}

/**
 * 继承中使用泛型
 *  父类有泛型，子类可以选择保留泛型也可以选择指定泛型类型：
 *      子类不保留父类的泛型：按需实现
 *          没有类型擦除
 *          具体类型
 *      子类保留父类的泛型：泛型子类
 *          全部保留
 *          部分保留
 * @param <T1>
 * @param <T2>
 */
class Father<T1, T2> {}
// 子类不保留父类的泛型
// 1)没有类型擦除
class Son1 extends Father {// 等价于class Son extends Father<Object,Object>{}
}
// 2)具体类型
class Son2 extends Father<Integer, String> {}
// 子类保留父类的泛型
// 1)全部保留
class Son3<T1, T2> extends Father<T1, T2> {}
// 2)部分保留
class Son4<T2> extends Father<Integer, T2> {}

/**
 * 方法，也可以被泛型化，不管此时定义在其中的类是不是泛型类。
 * 在泛型方法中可以定义泛型参数，此时，参数的类型就是传入数据的类型。
 */

class TEST{
    public static <E> List<E> copyFromArrayToList(E[] arr) throws Exception{
        return null;
    }

}










