package userfulClasses;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/3
 * Time:15:09
 * Describe:
 */

public class Goods implements Comparable{
    private String name;
    private double price;
    public Goods(){

    }
    public Goods(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString(){
        return "Goods{"+"name='"+name+'\''+", price="+price+'}';
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Goods){
            //方式1
            Goods goods = (Goods) o;
            if(this.price> goods.price) return 1;
            else if(this.price< goods.price) return -1;
            else return this.name.compareTo(goods.name);
            //方式二
            //return Double.compare(this.price, goods.price);
        }
        throw new RuntimeException("传入数据类型不一致");
    }
}
