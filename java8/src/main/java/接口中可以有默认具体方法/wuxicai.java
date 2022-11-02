package 接口中可以有默认具体方法;

public interface wuxicai {

    public default String getName(){
        return "wuxicai";
    }
    public static void jingtai(){
        System.out.println("接口中可以有静态方法");
    }
}
