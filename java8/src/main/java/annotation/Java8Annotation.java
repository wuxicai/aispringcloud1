package annotation;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/*
* 重复注解
* 类型注解
*
* */
public class Java8Annotation {
    @Wuxc("study")
    @Wuxc
    @Wuxc("dsafkldsk")
    @Wuxc("fff")
    @Wuxc("fff")
    @Wuxc
    public void show(){

    }
    @Test
    public void test2() throws NoSuchMethodException {
        Class<Java8Annotation> clazz = Java8Annotation.class;
        Method show = clazz.getMethod("show");
        System.out.println("---------------------同类型的多个注解-----------------------");
        Wuxc[] annotations = show.getAnnotationsByType(Wuxc.class);
        for (Wuxc annotation : annotations) {
            System.out.println(annotation.value());
        }
//        for (Annotation[] annotations1 : show.getParameterAnnotations()) {
//            System.out.println(annotations1);
//        }

    }
}
