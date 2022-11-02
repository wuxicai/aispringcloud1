import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.Test;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

@Aspect
@Component
@Slf4j
@EnableAspectJAutoProxy
public class fanshe {


    @Test
    @table
    @field
    public  void test() throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> user = Class.forName("User");

        String name = user.getName();//类名  包名+类名
        String simpleName = user.getSimpleName();//简单名字  类名
        System.out.println(user);
        System.out.println(name+simpleName);

        System.out.println("------------------获取pubulic属性---------------------");
        Field[] fields = user.getFields();//获取pubulic属性
        for (Field one:fields
             ) {
            System.out.println(one);
        }
        System.out.println("------------------获取全部属性---------------------");
        Field[] fields1 = user.getDeclaredFields();//获取全部属性
        for (Field one:fields1
                ) {
            System.out.println(one);
        }
        System.out.println("------------------获取指定public属性name1---------------------");
        Field name1 = user.getField("name1");
        System.out.println(name1);
        System.out.println("------------------获取任一指定属性name---------------------");
        Field name2 = user.getDeclaredField("name");
        System.out.println(name2);
        String[] strings = {"3", "3", "2"};
        StringBuffer stringBuffer = new StringBuffer();
        for (String string : strings) {
            stringBuffer.append(string);
        }
        System.out.println(stringBuffer.toString());

        System.out.println("===============================获得本类及其父类的public方法==============================");
        //获取方法
        Method[] methods = user.getMethods();//获得本类及其父类的public方法
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("===============================获得本类的所有方法==============================");
        Method[] declaredMethods = user.getDeclaredMethods();//获得本类的所有方法
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
        System.out.println("===============================获取指定方法==============================");

        Method getName = user.getMethod("getName", null);
        Method setAge = user.getMethod("setAge", int.class);
        System.out.println(getName+setAge.toString());


        System.out.println("===============================获取构造器==============================");
        Constructor<?> constructor = user.getConstructor(String.class,int.class,long.class,String.class);
        Constructor<?>[] constructors = user.getConstructors();
        Constructor<?>[] declaredConstructors = user.getDeclaredConstructors();
        System.out.println("===============================获取指定构造器==============================");
        System.out.println(constructor);
        System.out.println("===============================获取构造器getConstructors==============================");
        for (Constructor<?> constructor1 : constructors) {
            System.out.println(constructor1);
        }
        System.out.println("===============================获取declaredConstructor构造器==============================");
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }
        System.out.println("===============================动态创建对象==============================");
        User user1 = (User)user.newInstance();
        user1.setAge(11);
        System.out.println(user1);

        System.out.println("===============================通过构造器创建对象==============================");

        Constructor<?> constructor1 = user.getConstructor(String.class,int.class,long.class,String.class);
        User o = (User)constructor.newInstance("fds", 11, 3334L, "f2f2");
        System.out.println(o);
        System.out.println(o.getName());

        System.out.println("===============================通过反射调用方法==============================");
        /*
        * invoke :激活方法  第一个参数是某个对象，第二个是方法参数
        * */
        Method setAge1 = user.getMethod("setAge", int.class);

        setAge1.invoke(o,13);
        System.out.println(o);

        System.out.println("===============================通过反射操作属性==============================");

        Field name4 = user.getDeclaredField("name");
        name4.setAccessible(true); //关闭安全监测 ，设置可达，使得私有属性可操作  ***可提到效率
        name4.set(o,"吴喜财");
        System.out.println(o);


        Field name3 = user.getField("name1");
        name3.set(o,"吴喜财");
        System.out.println(o);

        System.out.println("===============================性能比较==============================");
//        fanshe.putong();
//        fanshe.fanshe();
//        fanshe.guangbijiance();
        System.out.println("===============================反射操作泛型(参数)==============================");
        Method fangxing1 = fanshe.class.getMethod("fanxing1", Map.class, List.class);
        Type[] genericParameterTypes = fangxing1.getGenericParameterTypes();
        for (Type genericParameterType : genericParameterTypes) {
            System.out.println("参数泛型："+genericParameterType);
            if(genericParameterType instanceof ParameterizedType){
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println("真实类型："+actualTypeArgument);
                }
            }
        }

        System.out.println("===============================反射操作泛型（返回值）==============================");
        Method fangxing2 = fanshe.class.getMethod("fanxing2", List.class);
        Type genericReturnType = fangxing2.getGenericReturnType();

            System.out.println("参数泛型："+genericReturnType);
            if(genericReturnType instanceof ParameterizedType){
                Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println("真实类型："+actualTypeArgument);

                }
        System.out.println("===============================反射操作注解）==============================");
                Annotation[] annotations = user.getAnnotations();
                for (Annotation annotation : annotations) {
                    System.out.println(annotation);
                }
              //  System.out.println(Proxy.isProxyClass(Proxy.newProxyInstance(table.class.getClassLoader(),new Class[]{table.class},InvocationHandler handler)));
              //  InvocationHandler handler = Proxy.getInvocationHandler(user.getAnnotation(table.class));
                System.out.println("===============================反射操作注解(动态注入注解参数)==============================");
                InvocationHandler handler1 = Proxy.getInvocationHandler(this.getClass().getMethod("test").getAnnotation(field.class));
                Field memberValues1 = handler1.getClass().getDeclaredField("memberValues");
                memberValues1.setAccessible(true);
                Map memberValue = (Map) memberValues1.get(handler1);
                memberValue.put("type", "清华大学");
                System.out.println(this.getClass().getMethod("test").getAnnotation(field.class).type());


                InvocationHandler handler = Proxy.getInvocationHandler(this.getClass().getMethod("fanxing4").getAnnotation(table.class));
                Field hField = handler.getClass().getDeclaredField("memberValues");
                hField.setAccessible(true);
                Map memberValues = (Map) hField.get(handler);
                memberValues.put("value", "屈俊豪");
                //获取指定注解
                System.out.println(this.getClass().getMethod("fanxing4").getAnnotation(table.class).value());
                table annotation = (table) user.getAnnotation(table.class);
                String value = annotation.value();
                System.out.println(value);
                Field name11 = user.getField("name1");
                field annotation1 = name11.getAnnotation(field.class);
                System.out.println(annotation1.columnName()+" , "+annotation1.type()+" , "+annotation1.length());
            }

    }
    @table
    public void fanxing4(){}
    public void fanxing1(Map<String,User> map, List<User> list){
        System.out.println("泛型参数");
    }
    public Map<String,User> fanxing2(List<User> list){
        System.out.println("泛型返回值");
        return null;
    }
    //普通方法
    public static void putong(){
        User user = new User();
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            user.getName();
        }
        long endtime = System.currentTimeMillis();
        System.out.println("普通方法:"+(endtime-starttime)+"ms");
    }

    //反射方法
    public static void fanshe() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user1 = new User();
        Class<?> user = user1.getClass();
        Method getName = user.getMethod("getName", null);
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user1,null);
        }
        long endtime = System.currentTimeMillis();
        System.out.println("反射方法:"+(endtime-starttime)+"ms");
    }
    //反射关闭监测方法
    public static void guangbijiance() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user1 = new User();
        Class<?> user = user1.getClass();
        Method getName = user.getMethod("getName", null);
        getName.setAccessible(true);
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user1,null);
        }
        long endtime = System.currentTimeMillis();
        System.out.println("关闭监测方法:"+(endtime-starttime)+"ms");
    }
}
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface table{
    String value() default "";
}

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface field{
    String columnName() default "";
    String type() default "";
    int length() default 1;
}