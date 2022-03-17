package ReflectionExercise;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/6
 * Time:17:23
 * Describe:
 */

@MyAnnotation(value="java")
public class PersonNew extends Creature<String> implements Comparable<String>,MyInterface{

    private String name;
    int age;
    public int id;

    public PersonNew() {
    }

    @MyAnnotation(value="C++")
    PersonNew(String name){
        this.name = name;
    }

    private PersonNew(String name,int age){
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

    public String display(String play){
        return play;
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }
}

