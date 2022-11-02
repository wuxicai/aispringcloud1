package test;


import com.southwind.dao.CustomerDao;
import com.southwind.dao.LinkManDao;
import com.southwind.dao.RoleDao;
import com.southwind.dao.UserDao;
import com.southwind.domain.Customer;
import com.southwind.domain.LinkMan;
import com.southwind.domain.Role;
import com.southwind.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {
    @Autowired
    private UserDao daoU;
    @Autowired
    private RoleDao daoR;
    @Test
    @Transactional
    @Rollback(value = false)
    public void testSaveC(){
        User user = new User();
        user.setUserName("wuxicai666");
        Role role = new Role();
        role.setRoleName("chushi666");
        user.getRoles().add(role);
        daoU.save(user);
        daoR.save(role);

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testcascade(){
        User user = new User();
        user.setUserName("wuxicai666");
        Role role = new Role();
        role.setRoleName("chushi666");
        user.getRoles().add(role);
        daoU.save(user);
    }
}
