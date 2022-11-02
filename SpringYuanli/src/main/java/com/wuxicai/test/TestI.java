package com.wuxicai.test;

import org.junit.Test;

public class TestI {
    @Test
    public void test1(){
        Class<?>[] interfaces = Person.class.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface.getSimpleName());
        }
        for (Class<?> aClass : Student.class.getInterfaces()) {
            System.out.println(aClass.getSimpleName());
        }
    }}
