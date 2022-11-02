package com.southwind.dao;

import com.southwind.domain.Customer;
import com.southwind.domain.User;
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

public interface UserDao extends JpaRepository<User,Long>,JpaSpecificationExecutor<User> {

}
