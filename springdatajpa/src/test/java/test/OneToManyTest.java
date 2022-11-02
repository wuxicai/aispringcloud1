package test;


import com.southwind.dao.CustomerDao;
import com.southwind.dao.LinkManDao;
import com.southwind.domain.Customer;
import com.southwind.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {
    @Autowired
    private CustomerDao daoc;
    @Autowired
    private LinkManDao daol;
    @Test
    @Transactional
    @Rollback(value = false)
    public void testSaveC(){
        Customer customer = new Customer();
        customer.setCustName("baidu");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("mayun");
        /*
        * 关联关系
        * */
        customer.getLinkMans().add(linkMan);
        linkMan.setCustomer(customer);
        daoc.save(customer);
        daol.save(linkMan);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testcascadeadd(){
        Customer customer = new Customer();
        customer.setCustName("baidu1");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("mayun1");
        LinkMan linkMan1 = new LinkMan();
        linkMan1.setLkmName("mayun2");
        LinkMan linkMan2 = new LinkMan();
        linkMan2.setLkmName("mayun3");
        /*
         * 关联关系
         * */
        customer.getLinkMans().add(linkMan);

        customer.getLinkMans().add(linkMan1);
        customer.getLinkMans().add(linkMan2);
        linkMan.setCustomer(customer);
        linkMan1.setCustomer(customer);
        linkMan2.setCustomer(customer);
        daoc.save(customer);
    }
    @Test
    @Transactional
    @Rollback(value = false)
    public void testcascadedel(){
        Customer one = daoc.findOne(19l);
        daoc.delete(one);
    }
}
