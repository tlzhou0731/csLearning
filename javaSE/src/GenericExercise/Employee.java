package GenericExercise;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/4
 * Time:13:09
 * Describe:
 */

public class Employee implements Comparable<Employee>{
    private String name;
    private int age;
    private MyDate birthday;

    public Employee(String name, int age, MyDate birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public int compareTo(Employee e) {
        return this.name.compareTo(e.name);
//        if(this.name!=e.name){
//            return this.name.compareTo(e.name);
//        }else if(this.age!=e.age){
//            return this.age-e.age;
//        }else{
//            return this.birthday.compareTo(e.birthday);
//        }
    }
}
