package com.wuxicai.demo;


import com.wuxicai.annotations.GPController;
import com.wuxicai.annotations.GPRequestMapping;
import org.junit.Test;

@GPController
public class Test1 {
    @GPRequestMapping(value = "/")
    public String getName(){
        return "4324";
    }
    @Test
    public void a(){
        String b="11";
        if (b.equals("1")){
            System.out.println("3232");
        }

        Integer a=2;
        if (a.equals(1)){}
    }
}
