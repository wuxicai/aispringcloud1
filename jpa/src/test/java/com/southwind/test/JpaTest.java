package com.southwind.test;

import com.southwind.pojo.Customer;
import com.southwind.utils.JpaUtils;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

    /*
    * 1.加载配置文件创建工厂对象
    * 2.通过工厂对象获取实体管理器
    * 3.获取事务对象，开启事务
    * 4.完成增删操作
    * 5.提交事务（或者回滚）
    * 6.释放资源
    */
    @Test
    public void save(){
        EntityManager em = JpaUtils.getEntityManagerFactory();
        EntityTransaction tx = em.getTransaction();//事务对象
        tx.begin();
        Customer customer = new Customer();
        customer.setCustName("周洪峰");
        customer.setCustAddress("北京丰台区");
        em.persist(customer);
        tx.commit();
        em.close();
    }
    @Test
    /*
    * 查出来 的结果就是实体类对象
    * 执行sql立即查询数据库  立即加载
    *
    * */
    public void testFind(){
        EntityManager em = JpaUtils.getEntityManagerFactory();

        System.out.println(em.find(Customer.class,1l));
        em.close();
    }
    @Test
    /*
    * 获取的对象是动态代理的对象
    * 执行sql 不立即查询，调用的时候才查询，延迟加载  懒加载
    * */
    public void testReference(){
        EntityManager em = JpaUtils.getEntityManagerFactory();

        System.out.println(em.getReference(Customer.class,2l));
        em.close();
    }
    @Test
    public void testRemove(){
        EntityManager em = JpaUtils.getEntityManagerFactory();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Customer customer = em.getReference(Customer.class, 2l);
        em.remove(customer);
        transaction.commit();
        em.close();
    }
    @Test
    public void testUpdate(){
        EntityManager em = JpaUtils.getEntityManagerFactory();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Customer customer = em.getReference(Customer.class, 1l);
        customer.setCustName("555");
        em.merge(customer);
        transaction.commit();
        em.close();
    }
}
