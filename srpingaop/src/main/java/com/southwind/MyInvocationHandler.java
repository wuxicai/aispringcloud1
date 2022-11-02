package com.southwind;

import lombok.Data;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
@Data
public class MyInvocationHandler implements InvocationHandler {
    /*
    * 该类用来创建代理类
    * */
    private Object object=null;
    public Object bind(Object object){
        this.object=object;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName()+"方法的参数是："+ Arrays.toString(args));
        Object result = method.invoke(this.object, args);
        System.out.println(method.getName()+"的结果是："+result);
        return result;
    }
}
