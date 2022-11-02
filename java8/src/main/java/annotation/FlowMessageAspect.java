package annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Aspect
@Component
@Slf4j
public class FlowMessageAspect {
private static final ThreadLocal<Map<String, Object>> threadInfo = new ThreadLocal<>();

    private static final String regex = "\\$\\{(\\d*)\\}";
    private static final String KEY_START = "start";
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String VALUE_STATUS_INIT = "初始";
    private static final String VALUE_STATUS_SUCCC = "成功";
    private static final String VALUE_STATUS_FAIL = "失败";
    private final Pattern pattern = Pattern.compile(regex);

    public FlowMessageAspect() {
        Map<String, Object> threadInfoMap = new HashMap<>();
        threadInfo.set(threadInfoMap);
    }

    /**
     * 设置流程专属线程属性
     */
    public static void setThreadInfo(String key, Object value) {
        threadInfo.get().put(key, value);
    }

    /**
     * 获取流程专属线程属性
     */
    public static Object getThreadInfo(String key) {
        return threadInfo.get().get(key);
    }

    /**
     * 设置流程状态为成功
     */
    public static void setFlowSucc() {
        setThreadInfo(KEY_STATUS, VALUE_STATUS_SUCCC);
    }

    /**
     * 设置流程状态为失败
     */
    public static void setFlowFail() {
        setThreadInfo(KEY_STATUS, VALUE_STATUS_FAIL);
    }
    /**
     * 切点
     */
    @Pointcut("execution(* com.demo.orgdz.job..*.*(..))")
    public void sendMessage() {
    }

    @Before(value = "sendMessage() && @annotation(flowMessageLogger) ")
    public void doBefore(JoinPoint joinPoint, FlowMessageLogger flowMessageLogger) {
        // 置入开始时间
        long start = System.currentTimeMillis();
        setThreadInfo(KEY_START, start);
        // 置入注解消息
        String message = annotationResolver(joinPoint, flowMessageLogger);
        setThreadInfo(KEY_MESSAGE, message);
        // 置入状态
        setThreadInfo(KEY_STATUS, VALUE_STATUS_INIT);
    }


    /**
     * 成功返回
     */
    @AfterReturning(value = "sendMessage() && @annotation(flowMessageLogger))")
    public void doAfterReturning(FlowMessageLogger flowMessageLogger) {
        afterHandler(null);
    }

    /**
     * 异常返回
     */
    @AfterThrowing(value = "sendMessage() && @annotation(flowMessageLogger))", throwing = "throwable")
    public void doAfterThrowing(Throwable throwable, FlowMessageLogger flowMessageLogger) {
        setFlowFail();
        afterHandler(throwable);
    }

    /**
     * 通用后置处理
     */
    private void afterHandler(Throwable throwable) {
        long end = System.currentTimeMillis();
        long start = (long) getThreadInfo(KEY_START);
        long duaring = (end - start) / 1000;

        String message = (String) getThreadInfo(KEY_MESSAGE);

        String status = (String) getThreadInfo(KEY_STATUS);

        String exception = null;
        if (null != throwable) {
            exception = throwable.getLocalizedMessage();
        }
        threadInfo.remove();
        
        send(message, status, duaring, exception);
    }

    /**
     * 发送消息
     *
     * @param message 步骤内容
     * @param status 状态
     * @param duaring 耗时（秒）
     */
    private void send(String message, String status, long duaring, String exception) {
        // TODO: 发送邮件
        StringBuffer sb = new StringBuffer();
        sb.append(message).append("\n\r")
            .append("状态：").append(status).append("\n\r")
            .append("耗时：").append(duaring).append("秒");

        if (!StringUtils.isEmpty(exception)) {
            sb.append("\n\r").append("异常：").append(exception);
        }

        log.info("发送邮件：{}", sb);
    }

    /**
     * FlowMessageLogger 注解动态参数解析器
     */
    private String annotationResolver(JoinPoint joinPoint, FlowMessageLogger flowMessageLogger) {

        String message = flowMessageLogger.value();
        Object[] args = joinPoint.getArgs();

        if (null == args || args.length < 1 || StringUtils.isEmpty(message)) {
            return message;
        }

        // 如果name匹配上了${},则把内容当作变量
        Matcher matcher = pattern.matcher(message);

        int i;
        while (matcher.find()) {
            i = Integer.parseInt(matcher.group(0).replace("${", "").replace("}", ""));
            message = message.replace(matcher.group(0), String.valueOf(args[i]));
        }
        return message;
    }
}
