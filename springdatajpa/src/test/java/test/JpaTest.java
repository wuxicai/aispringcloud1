package test;

import com.southwind.dao.CustomerDao;
import com.southwind.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpaTest {
    @Autowired
    private CustomerDao dao;

    @Test
    public void testFindOne(){
        Customer one = dao.findOne(1l);
        System.out.println(one);
    }
    @Test
    public void testSave(){
        Customer customer = new Customer();
//        customer.setCustName("111222");
        customer.setCustId(1l);
        customer.setCustName("5555222");
        dao.save(customer);

    }
    @Test
    public void testDelete(){
        dao.delete(1l);
    }
    @Test
    public void testFinaAll(){
        List<Customer> all = dao.findAll();
        for(Customer a:all){
            System.out.println(a);
        }

    }
    /*
    * 统计查询
    *
    * */
    @Test
    public void testCount(){
        long count = dao.count();
            System.out.println(count);

    }
    /*
     * 判断某数据是否存在
     *
     * */
    @Test
    public void testExists(){
        boolean exists = dao.exists(1l);
        System.out.println(exists);

    }
    /*
     * 依据ID查询
     * Transactional:保证getone正常运行
     * getReference 懒加载模式 返回的是实体类的动态代理对象，什么时候用什么时候查询
     * */
    @Test
    @Transactional
    public void testGetone(){
        Customer one = dao.getOne(3l);
        System.out.println(one);

    }
}
