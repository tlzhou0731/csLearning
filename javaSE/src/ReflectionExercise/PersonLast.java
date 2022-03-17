package ReflectionExercise;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/6
 * Time:18:07
 * Describe:
 */


@MyAnnotation(value="java")
public class PersonLast extends Creature<String> implements Comparable<String>,MyInterface{

    private String name;
    int age;
    public int id;

    public PersonLast() {
    }

    @MyAnnotation(value="C++")
    PersonLast(String name){
        this.name = name;
    }

    private PersonLast(String name,int age){
        this.name = name;
        this.age = age;
    }

    @MyAnnotation
    private String show(String nation){
        System.out.println("我来自" + nation + "星系");
        return nation;
    }

    @Override
    public void info() {
        System.out.println("火星喷子");
    }

    public String display(String interests,int age) throws Exception{
        return interests + age;
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    @Override
    public String toString() {
        return "PersonLast{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}

