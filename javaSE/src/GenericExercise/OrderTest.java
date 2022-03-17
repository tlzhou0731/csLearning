package GenericExercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/4
 * Time:13:17
 * Describe:
 */

public class OrderTest<T> {
    String orderName;
    int orderId;
    T orderT;

    public OrderTest() {
    }

    public OrderTest(String orderName, int orderId, T orderT) {
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    /**
     *    //泛型方法：在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系。
     *     //换句话说，泛型方法所属的类是不是泛型类都没有关系。
     *     //泛型方法，可以声明为静态的。原因：泛型参数是在调用方法时确定的。并非在实例化类时确定。
     */
    public static <E> List<E> copyFromArrayToList(E[] arr){
        ArrayList<E> list = new ArrayList<>();
        for(E e:arr){
            list.add(e);
        }
        return list;
    }
}
