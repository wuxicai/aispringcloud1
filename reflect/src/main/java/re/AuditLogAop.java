package re;

import com.wu.annotation.Field;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@EnableAspectJAutoProxy
public class AuditLogAop {

    @Pointcut(value = "@annotation(com.wu.annotation.Field)")
    public void show(){
        System.out.println("++++++++++++++++++");
    }

    @AfterReturning("@annotation(field)")
    protected void insertAuditLog(Field field){
        System.out.println(field.type()+"************************************************************************************************");
    }

    @Field(columnName = "ff",type = "fsf",length = 1)
    public void insertAuditLog(){
        System.out.println("fsfs");
    }

    public static void main(String[] args) {
        new AuditLogAop().insertAuditLog();
    }
}
