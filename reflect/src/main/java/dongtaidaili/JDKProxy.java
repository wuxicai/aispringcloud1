package dongtaidaili;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {
    private Object targerObject;

    public Object newProxy(Object targerObject){
        this.targerObject=targerObject;
        Object obj= Proxy.newProxyInstance(targerObject.getClass().getClassLoader(), targerObject.getClass().getInterfaces(),this);
        return obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk invoke");

        return method.invoke(targerObject,args);

    }
}
