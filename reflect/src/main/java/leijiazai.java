public class leijiazai {
    static {
        System.out.println("main类被加载");
    }
    public static void main(String[] args) throws ClassNotFoundException {
//        A a = new A();
//        System.out.println(a.a);
        //主动引用
       // Son son = new Son();
        //反射主动引用
        //Class.forName("Son");

        //不会初始化
        //System.out.println(Son.a);

        //Son[] sons = new Son[4];
       // System.out.println(Son.m);

        //M是常量  不会触发类的初始化
        System.out.println(Son.M);

    }
}
/*
 * 1.类加载：把class字节码文件加载到内存中，并将这些静态数据转换成方法区的运行时数据结构
 * 然后生成一个代表这个类的java lang.Class对象
 * 2.链接：将java类中的二进制代码合并到jvm的运行状态中的过程
 * 3.初始化：执行构造器clinit()方法的过程，类构造器 clinit（）方法是由编译期自动收集类中所有变量的赋值动作
 * 和静态代码块中的语句合并产生的。（类构造器是构造类信息的，不是指构造该类对象的构造器）
 * 先触发父类的初始化
 * 该方法在多线程环境有同步锁
 *
 *
 *
 * 什么时候会初始化类
 * 1主动引用： main方法， new一个对象，调用静态成员或者静态方法，反射调用，初始化父类
 *
 * 2被动引用不会初始化
 *访问一个静态域时只有真正声明这个域的类才会被初始化。如 通过子类引用父类的静态变量，不会导致子类初始化
 *
 * 通过数组定义类引用，不会触发此类的初始化
 *
 * 引用常量不会触发此类的初始化
 *
 *
 *
 * 类是有缓存的，class会被垃圾回收   GC
 * */

class Father {
    static int a = 1;
    static String ll="";
    static {
        System.out.println("父类被加载");
    }

}

class Son extends Father {
    static {
        System.out.println("子类被加载");
        m=33;
    }
    static int m=21;
    static final int M=123;
}

class A {

    static int a = 1;

    static {
        System.out.println("静态代码块初始化");
        a = 3;
    }


    public A() {
        System.out.println("无参构造初始化");
    }
}