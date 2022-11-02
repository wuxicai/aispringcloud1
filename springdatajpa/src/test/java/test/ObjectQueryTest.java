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

import java.util.Set;

/*
*
*
* 对象导航查询，通过查询一个对象，并查询此对象查询的所有关联对象
*
*  默认是使用延迟加载形式进行的查询，再使用关联对象的时候才会进行子查询
*   如果不使用延迟加载修改： fetch，配置到多表 映射关系的注解上
*
*   EAGER：立即加载，join查询，
*   LAZY：延迟加载，子查询（默认模式）
*
* */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectQueryTest {
    @Autowired
    private CustomerDao daoc;
    @Autowired
    private LinkManDao daol;

    @Test
    @Transactional
//    @Rollback(value = false)
    public void testQuery1() {
        Customer one = daoc.getOne(11l);
        Set<LinkMan> linkMans = one.getLinkMans();
        for(LinkMan a:linkMans){
            System.out.println(a);
        }

    }
    @Test
    @Transactional
//    @Rollback(value = false)
    public void testQuery2() {
//        LinkMan one1 = daol.findOne(22l);
//        Customer customer = one1.getCustomer();
//        System.out.println(customer);
        Customer one = daoc.getOne(11l);
        Set<LinkMan> linkMans = one.getLinkMans();
        for(LinkMan a:linkMans){
            System.out.println(a);
        }

    }
}
