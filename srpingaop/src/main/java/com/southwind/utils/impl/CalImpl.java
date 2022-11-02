package com.southwind.utils.impl;

import com.southwind.utils.Cal;
import org.springframework.stereotype.Component;

@Component//类名首字母小写作为ID注册BEAN
public class CalImpl implements Cal {
    @Override
    public int add(int num1, int num2) {
        int result=num1+num2;
        return result;
    }

    @Override
    public int sub(int num1, int num2) {
        int result=num1-num2;
        return result;
    }

    @Override
    public int mul(int num1, int num2) {
        int result=num1*num2;
        return result;
    }

    @Override
    public int div(int num1, int num2) {
        int result=num1/num2;
        return result;
    }
}
