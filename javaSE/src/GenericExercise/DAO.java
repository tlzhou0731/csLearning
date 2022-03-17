package GenericExercise;

import java.util.List;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/4
 * Time:13:34
 * Describe:
 */

public class DAO <T>{
    public void add(T t){

    }
    //删除一条记录
    public boolean remove(int index){

        return false;
    }

    //修改一条记录
    public void update(int index,T t){

    }

    //查询一条记录
    public T getIndex(int index){

        return null;
    }

    //查询多条记录
    public List<T> getForList(int index){

        return null;
    }

    //泛型方法
    //举例：获取表中一共有多少条记录？获取最大的员工入职时间？
    public <E> E getValue(){

        return null;
    }
}
