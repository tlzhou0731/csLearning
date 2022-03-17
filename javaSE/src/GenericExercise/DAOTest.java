package GenericExercise;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/4
 * Time:13:35
 * Describe:
 */

import org.junit.Test;

import java.util.List;

public class DAOTest {

    @Test
    public void test(){
        CustomerDAO dao1 = new CustomerDAO();

        dao1.add(new Customer());
        List<Customer> list = dao1.getForList(10);


        StudentDAO dao2 = new StudentDAO();
        Student student = dao2.getIndex(1);
    }

}
