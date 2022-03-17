package AnnotationExercise;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/3
 * Time:16:16
 * Describe:
 */

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Date;

/**
 * 注解的使用
 *
 * 1. 理解Annotation:
 * ① jdk 5.0 新增的功能
 *
 * ② Annotation 其实就是代码里的特殊标记, 这些标记可以在编译, 类加载, 运行时被读取, 并执行相应的处理。通过使用 Annotation,
 *    程序员可以在不改变原有逻辑的情况下, 在源文件中嵌入一些补充信息。
 *
 * ③在JavaSE中，注解的使用目的比较简单，例如标记过时的功能，忽略警告等。在JavaEE/Android
 *  中注解占据了更重要的角色，例如用来配置应用程序的任何切面，代替JavaEE旧版中所遗留的繁冗
 *  代码和XML配置等。
 *
 * 2. Annocation的使用示例
 *  示例一：生成文档相关的注解
 *  示例二：在编译时进行格式检查(JDK内置的三个基本注解)
 *      @Override: 限定重写父类方法, 该注解只能用于方法
 *      @Deprecated: 用于表示所修饰的元素(类, 方法等)已过时。通常是因为所修饰的结构危险或存在更好的选择
 *      @SuppressWarnings: 抑制编译器警告
 *
 *  示例三：跟踪代码依赖性，实现替代配置文件功能
 */

/**
 * 使用Annotation 时要在其前面增加@ 符号, 并把该Annotation 当成一个修饰符使用。用于修饰它支持的程序元素
 * 示例一：生成文档相关的注解
 * @author标明开发该类模块的作者，多个作者之间使用,分割
 * @version标明该类模块的版本
 * @see参考转向，也就是相关主题
 * @since从哪个版本开始增加的
 * @param对方法中某参数的说明，如果没有参数就不能写
 * @return对方法返回值的说明，如果方法的返回值类型是void就不能写
 * @exception对方法可能抛出的异常进行说明，如果方法没有用throws显式抛出的异常就不能写其中
 * @param@return和@exception这三个标记都是只用于方法的。
 * @param的格式要求：@param形参名形参类型形参说明
 * @return的格式要求：@return返回值类型返回值说明
 * @exception的格式要求：@exception异常类型异常说明
 * @param和@exception可以并列多个
 * 示例二：在编译时进行格式检查(JDK内置的三个基本注解)
 * @Override: 限定重写父类方法, 该注解只能用于方法
 * @Deprecated: 用于表示所修饰的元素(类, 方法等)已过时。通常是因为所修饰的结构危险或存在更好的选择
 * @SuppressWarnings: 抑制编译器警告
 * 示例三：跟踪代码依赖性，实现替代配置文件功能
 * Servlet3.0提供了注解(annotation),使得不再需要在web.xml文件中进行Servlet的部署。
 * spring框架中关于“事务”的管理
 *
 */

/**
 * 注解的使用
 *
 *   4.jdk 提供的4种元注解
 *     元注解：对现有的注解进行解释说明的注解
 *     **********使用注解时一般都会用 Retention, Target ********************
 *     Retention:指定所修饰的 Annotation 的生命周期：SOURCE\CLASS（默认行为）\RUNTIME
 *               只有声明为RUNTIME生命周期的注解，才能通过反射获取。
 *     Target:用于指定被修饰的 Annotation 能用于修饰哪些程序元素
 *     *******出现的频率较低*******
 *     Documented:表示所修饰的注解在被javadoc解析时，保留下来。
 *     Inherited:被它修饰的 Annotation 将具有继承性。
 *
 * 5.通过反射获取注解信息 ---到反射内容时系统讲解
 *
 * JDK 5.0 在java.lang.reflect包下新增了AnnotatedElement接口, 该接口代表程序中可以接受注解的程序元素
 * 当一个Annotation 类型被定义为运行时Annotation 后, 该注解才是运行时可见, 当class文件被载入时保存在class 文件中的Annotation 才会被虚拟机读取
 * 程序可以调用AnnotatedElement对象的如下方法来访问Annotation 信息
 */

/**
 * 注解的使用
 *
 * 6.jdk 8 中注解的新特性：可重复注解、类型注解
 *
 *   6.1可重复注解：① 在MyAnnotation上声明@Repeatable，成员值为MyAnnotations.class
 *                ② MyAnnotation的Target和Retention等元注解与MyAnnotations相同。
 *
 *  *   6.2类型注解：
 *  *      ElementType.TYPE_PARAMETER 表示该注解能写在类型变量的声明语句中（如：泛型声明）。
 *  *      ElementType.TYPE_USE 表示该注解能写在使用类型的任何语句中。
 *
 * @author subei
 * @create 2020-05-11 11:19
 */



public class AnnotationTest {
    public static void main(String[] args) {

    }

    @Test
    public void testGetAnnotation(){
        Class clazz = Student.class;
        Annotation[] annotations = clazz.getAnnotations();
        for(int i = 0;i < annotations.length;i++){
            System.out.println(annotations[i]);
        }
    }
}
@MyAnnotation(value = "hello")
@MyAnnotation(value = "yep")
class Person{
    private String name;
    private int age;

    public Person() {
        super();
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void walk(){
        System.out.println("学习中……");
    }
    public void eat(){
        System.out.println("摸鱼中……");
    }
}

interface Info{
    void show();
}

class Student extends Person implements Info{

    @Override
    public void walk() {
        System.out.println("喷子走开");
    }

    @Override
    public void show() {

    }
}

class Generic<@MyAnnotation T>{

    public void show() throws @MyAnnotation RuntimeException{

        ArrayList<@MyAnnotation String> list = new ArrayList<>();

        int num = (@MyAnnotation int) 10L;
    }

}
















