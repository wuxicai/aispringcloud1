package com.southwind.test;

import com.southwind.pojo.Customer;
import com.southwind.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class JpqlTest {
    @Test
    public void FindAll(){
        EntityManager em = JpaUtils.getEntityManagerFactory();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        String sql="from Customer";
        Query query = em.createQuery(sql);
        List resultList = query.getResultList();
        for (Object a:resultList
             ) {
            System.out.println(a);
        }
        transaction.commit();
        em.close();
    }
    @Test
    /*
    * 倒叙查询
    * */
    public void FindAllOrder(){
        EntityManager em = JpaUtils.getEntityManagerFactory();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        String sql="from Customer order by custId desc ";
        Query query = em.createQuery(sql);
        List resultList = query.getResultList();
        for (Object a:resultList
                ) {
            System.out.println(a);
        }
        transaction.commit();
        em.close();
    }
    @Test
    /*
     * count
     * */
    public void count(){
        EntityManager em = JpaUtils.getEntityManagerFactory();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        String sql="select count(id) from Customer";
        Query query = em.createQuery(sql);
        Object singleResult = query.getSingleResult();
        System.out.println(singleResult);
        transaction.commit();
        em.close();
    }

    @Test
    /*
     * 条件查询
     * */
    public void condition(){
        EntityManager em = JpaUtils.getEntityManagerFactory();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        String sql=" from Customer where custName like ?";
        Query query = em.createQuery(sql);
        query.setParameter(1,"w%");
        List resultList = query.getResultList();
        for (Object a:resultList
                ) {
            System.out.println(a);
        }
        transaction.commit();
        em.close();
    }
}
