package test;

import com.southwind.dao.CustomerDao;
import com.southwind.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {
    @Autowired
    private CustomerDao dao;
    @Test
    public void testJpqlFind(){
        List<Customer> customers = dao.findjpql("wu%");
        for(Customer a:customers){
            System.out.println(a);
        }
    }
    @Test
    public void findjpqlnameandid(){
        List<Customer> customers = dao.findjpqlnameandid(4l,"wu%");
        for(Customer a:customers){
            System.out.println(a);
        }
    }

    /*
    * jpql更新操作必须添加事务
    * 默认rollback
    * 接口加 Modifying
    * */
    @Test
    @Transactional
    @Rollback(value = false)
    public void update(){
       dao.update("wu%",3l);

    }


    @Test
    public void findjpqlnameandidsql(){
        List<Customer> customers = dao.findjpqlnameandidsql(3l,"wu%");
        for(Customer a:customers){
            System.out.println(a);
        }
    }
    /*
    * 方法命名规则，
    * */
    @Test
    public void findBy(){
        Customer customer = dao.findByCustId(4l);
            System.out.println(customer);
    }
    @Test
    public void findByLike(){
        Customer[] customers = dao.findByCustNameLike("wu%");
        System.out.println(Arrays.toString(customers));
//        for(Customer[] a:customers){
//            System.out.println(a);
//        }
    }
    @Test
    public void findByAnd(){
        Customer[] customers = dao.findByCustNameLikeAndCustId("wu%",5);
        System.out.println(Arrays.toString(customers));
//        for(Customer[] a:customers){
//            System.out.println(a);
//        }
    }
}
