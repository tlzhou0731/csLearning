package ReflectionExercise;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/5
 * Time:19:23
 * Describe:
 */

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {

    //反射之前，对于Person的操作
    @Test
    public void test(){

        //1.创建类的对象
        Person p1 = new Person("jay",21);

        //2.调用对象,调用其内部的属性和方法
        p1.age = 15;
        System.out.println(p1.toString());

        p1.show();

        //在Person类的外部，不可以通过Person类的对象调用其内部私有的结构。
        //比如：name、showNation以及私有的构造器。

    }

    //反射之后 ，堆与Person的操作
    @Test
    public void test2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class clazz = Person.class;
        //1.通过反射，创建Person类的对象
        Constructor cons = clazz.getConstructor(String.class,int.class);
        Object obj = cons.newInstance("Jon",18);
        Person p = (Person) obj;
        System.out.println(p.toString());
        //2.通过反射，调用对象指定的属性和方法
        //调用属性
        Field age = clazz.getDeclaredField("age");
        age.set(p,10);
        System.out.println(p.toString());

        //调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);

    }
    //反射之后 ，堆与Person的操作
    @Test
    public void test3() throws Exception{
        Class clazz = Person.class;
        //1.通过反射，创建Person类的对象
        Constructor cons = clazz.getConstructor(String.class,int.class);
        Object obj = cons.newInstance("Jon",18);
        Person p = (Person) obj;
        System.out.println(p.toString());
        //2.通过反射，调用对象指定的属性和方法
        //调用属性
        Field age = clazz.getDeclaredField("age");
        age.set(p,10);
        System.out.println(p.toString());

        //调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);

        System.out.println("+++++++++++++++++++++++++");

        //通过反射，是可以调用Person类的私有结构的。比如：私有的构造器、方法、属性
        //调用私有的构造器
        Constructor cons2 = clazz.getDeclaredConstructor(String.class);
        cons2.setAccessible(true);
        Person p1 = (Person) cons2.newInstance("kalo");
        System.out.println(p1);

        //调用私有的属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1,"Taoyao");
        System.out.println(p1);

        //调用私有的方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(p1,"中国");
        //相当于String nation = p1.showNation("FaceBook")
        System.out.println(nation);
    }

    /**
     * 疑问1：通过直接new的方式或反射的方式都可以调用公共的结构，开发中到底用那个？
     * 建议：直接new的方式。
     * 什么时候会使用：反射的方式。 反射的特征：动态性
     * 疑问2：反射机制与面向对象中的封装性是不是矛盾的？如何看待两个技术？
     * 不矛盾。
     */

    /**
     * 关于java.lang.Class类的理解
     * 1.类的加载过程：
     * 程序经过Javac.exe命令后，会生成一个或多个字节码文件(.class结尾)。
     * 接着我们使用java.exe命令对某个字节码文件进行解释运行。相当于将某个字节码文件
     * 加载到内存中。此过程就称为类的加载。加载到内存中的类，我们就称为运行时类，此
     * 运行时类，就作为Class的一个实例。
     *
     * 2.换句话说，Class的实例就对应着一个运行时类。
     */
    /**
     * 2.换句话说，Class的实例就对应着一个运行时类。
     * 3.加载到内存中的运行时类，会缓存一定的时间。在此时间之内，我们可以通过不同的方式
     * 来获取此运行时类。
     */


    @Test
    public void test4() throws ClassNotFoundException {
        //方式一：
        Class c1 = Person.class;
        System.out.println(c1);

        //方式二：通过运行时类的对象,调用getClass()
        Person p1 = new Person();
        Class c2 = p1.getClass();
        System.out.println(c2);

        //方式三：调用Class的静态方法：forName(String classPath)
        Class c3 = Class.forName("ReflectionExercise.Person");
//        c3 = Class.forName("www.123.com");
        System.out.println(c3);

        System.out.println(c1 == c2);
        System.out.println(c1 == c3);

        //方式四：使用类的加载器：ClassLoader  (了解)
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class c4 = classLoader.loadClass("ReflectionExercise.Person");
        System.out.println(c4);

        System.out.println(c1 == c4);
    }

    /**
     * 1、哪些类型可以有Class对象？
     *
     * （1）class：外部类，成员(成员内部类，静态内部类)，局部内部类，匿名内部类
     * （2）interface：接口
     * （3）[]：数组
     * （4）enum：枚举
     * （5）annotation：注解@interface
     * （6）primitivetype：基本数据类型
     * （7）void
     *
     */

    //万事万物皆对象？对象.xxx,File,URL,反射,前端、数据库操作

    /**
     * Class实例可以是哪些结构的说明：
     */
    @Test
    public void test5() {
        Class s1 = Object.class;
        Class s2 = Comparable.class;
        Class s3 = String[].class;
        Class s4 = int[][].class;
        Class s5 = ElementType.class;
        Class s6 = Override.class;
        Class s7 = int.class;
        Class s8 = void.class;
        Class s9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class s10 = a.getClass();
        Class s11 = b.getClass();
        // 只要数组的元素类型与维度一样，就是同一个Class
        System.out.println(s10 == s11);
    }


    /**
     * 调用运行时类中指定的结构：属性、方法、构造器
     */
    /**
     * 不需要掌握
     */
    @Test
    public void testField() throws Exception {
        Class clazz = PersonLast.class;

        //创建运行时类的对象
        PersonLast p = (PersonLast) clazz.newInstance();

        //获取指定的属性：要求运行时类中属性声明为public
        //通常不采用此方法
        Field id = clazz.getField("id");

        //设置当前属性的值
        //set():参数1：指明设置哪个对象的属性   参数2：将此属性值设置为多少
        id.set(p,1001);

        //获取当前属性的值
        //get():参数1：获取哪个对象的当前属性值
        int pId = (int) id.get(p);
        System.out.println(pId);
    }

    /**
     * 如何操作运行时类中的指定的属性 -- 需要掌握
     */
    @Test
    public void testField1() throws Exception {
        Class clazz = PersonLast.class;

        //创建运行时类的对象
        PersonLast p = (PersonLast) clazz.newInstance();

        //1. getDeclaredField(String fieldName):获取运行时类中指定变量名的属性
        Field name = clazz.getDeclaredField("name");

        //2.保证当前属性是可访问的
        name.setAccessible(true);

        //3.获取、设置指定对象的此属性值
        name.set(p,"Jam");
        System.out.println(name.get(p));
    }

    /**
     * 如何操作运行时类中的指定的方法 -- 需要掌握
     */
    @Test
    public void testMethod() throws Exception {
        Class clazz = PersonLast.class;
        //创建运行时类的对象
        PersonLast p = (PersonLast) clazz.newInstance();

        //1.获取指定的某个方法
        //getDeclaredMethod():参数1 ：指明获取的方法的名称  参数2：指明获取的方法的形参列表
        Method show = clazz.getDeclaredMethod("show", String.class);

        //2.保证当前方法是可访问的
        show.setAccessible(true);

        //3.调用方法的invoke():参数1：方法的调用者  参数2：给方法形参赋值的实参
        //invoke()的返回值即为对应类中调用的方法的返回值。
        Object returnValue = show.invoke(p,"CCA"); //String nation = p.show("CCA");
        System.out.println(returnValue);

        System.out.println("+++++++++如何调用静态方法+++++++++++");

//    private static void showDesc()

        Method showDesc = clazz.getDeclaredMethod("showDown");
        showDesc.setAccessible(true);
        //如果调用的运行时类中的方法没有返回值，则此invoke()返回null
//    Object returnVal = showDesc.invoke(null);
        Object returnVal = showDesc.invoke(Person.class);
        System.out.println(returnVal);//null
    }

    /**
     * 如何调用运行时类中的指定的构造器
     */
    @Test
    public void testConstructor() throws Exception {
        Class clazz = PersonLast.class;

        //private Person(String name)
        //1.获取指定的构造器
        //getDeclaredConstructor():参数：指明构造器的参数列表
        Constructor constructor = clazz.getDeclaredConstructor(String.class);

        //2.保证此构造器是可访问的
        constructor.setAccessible(true);

        //3.调用此构造器创建运行时类的对象
        Person per = (Person) constructor.newInstance("Tom");
        System.out.println(per);
    }















}
