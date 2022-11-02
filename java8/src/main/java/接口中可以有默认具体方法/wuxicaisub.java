package 接口中可以有默认具体方法;

import org.junit.Test;

public class wuxicaisub  implements wuxicai,wuxicai1 {
    @Test
    public void test1(){
        wuxicaisub wuxicaisub = new wuxicaisub();
        System.out.println(wuxicaisub.getName());//类优先原则
        wuxicai.jingtai();//接口中静态方法的调用方式
    }

    @Override
    public String getName() {
        return wuxicai1.super.getName();//实现多接口冲突
    }
}
