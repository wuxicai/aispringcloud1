package dongtaidaili;


import org.aspectj.weaver.ast.Var;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibProxy implements MethodInterceptor {

    private Object targetObject;

    public Object createproxyObject(Object obj){

        this.targetObject=obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        Object proxyObj = enhancer.create();
        return proxyObj;
    }
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        if("addUser".equals(method.getName())){
            System.out.println("检查权限");

        }

        return method.invoke(targetObject,args);
    }
}
