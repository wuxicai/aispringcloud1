package com.southwind.dao;

import com.southwind.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/*
* 继承 JpaRepository  JpaSpecificationExecutor
* JpaRepository 增删改查
* JpaSpecificationExecutor 复杂查询
* 用动态代理功能实现具体实现类
* */

public interface CustomerDao extends JpaRepository<Customer,Long>,JpaSpecificationExecutor<Customer> {

    /*
    * 使用jpql:
    * 1.接口中自定义方法
    * 2.使用query注解
    * */
    @Query("from Customer where custName like ?")
    public List<Customer> findjpql(String name);


    /*
    * 方法参数默认与占位符顺序一致，可以指定:占位符后加索引（索引从1开始）
    *
    *
    * */
    @Query("from Customer where custName like ?2 and custId = ?1")
    public List<Customer> findjpqlnameandid(Long id,String name);


    @Query(value = "select * from cst_customer where cust_name like ?2 and cust_id = ?1",nativeQuery = true)
    public List<Customer> findjpqlnameandidsql(Long id,String name);
    /*
    * Modifying:代表更新方法
    *
    * */
    @Query("update Customer set custName = ? where cust_id = ?")
    @Modifying
    public void update(String name,long id);


    /*
    * 默认查找方式是==
    *
    *
    */
    public Customer findByCustId(Long id);

    public Customer[] findByCustNameLike(String name);

    public Customer[] findByCustNameLikeAndCustId(String name,long id);
}
