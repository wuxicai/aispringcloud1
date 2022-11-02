package com.southwind.test;

import com.southwind.MyInvocationHandler;
import com.southwind.utils.Cal;
import com.southwind.utils.impl.CalImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop.xml");
        Cal proxy = (Cal) applicationContext.getBean("calImpl");
        proxy.add(9,2);
//        Cal cal = new CalImpl();
//        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
//        Cal bind = (Cal) myInvocationHandler.bind(cal);
//        bind.add(2,1);
//        bind.sub(2,1);
//        bind.mul(2,1);
//        bind.div(2,1);
//        cal.add(1,4);
//        cal.sub(1,4);
//        cal.mul(1,4);
//        cal.div(1,4);

    }
}
