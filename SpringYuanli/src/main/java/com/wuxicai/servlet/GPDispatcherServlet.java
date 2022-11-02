package com.wuxicai.servlet;

import com.wuxicai.annotations.GPAutowired;
import com.wuxicai.annotations.GPController;
import com.wuxicai.annotations.GPRequestMapping;
import com.wuxicai.annotations.GPService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class GPDispatcherServlet extends HttpServlet {


    private Properties contextConfit = new Properties();
    private List<String> classNames = new ArrayList<>();
    private Map<String, Object> ioc = new HashMap<>();
    private Map<String,Method> handlerMapping=new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{

        try {
            doDispatcher(req,resp);
        } catch (Exception e) {
            e.printStackTrace();

            resp.getWriter().write("500:Exception Detial:"+Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String url = req.getRequestURI();
        System.out.println("绝对路径"+url);
        String path = req.getContextPath();
        System.out.println("服务器路径："+path);
        url = url.replaceAll(path, "").replaceAll("/+", "/");
        if(!handlerMapping.containsKey(url)){
            resp.getWriter().write("404啊，找不到服务");
        }
        Map<String,String[]> params = req.getParameterMap();
        Method method = this.handlerMapping.get(url);

        //根据方法找到类名，拿到类名得到beanname去ioc容器里面找到对象，自己new 的对象调用不了方法
        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());

        method.invoke(ioc.get(beanName),new Object[]{req,resp,params.get("name")[0]});
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //1  加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        //2  扫描相关的类
        doScanner(contextConfit.getProperty("scanPackage"));
        //3  实例化相关的类
        doInstance();
        //4  完成依赖注入
        doAutowired();
        //5  初始化HandlerMapping
        doInitHandlerMapping();
        //6  调用

        System.out.println(" GP Spring 初始化完成");
    }

    private void doInitHandlerMapping() {
        if(ioc.isEmpty()){return;}
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {

            Class<?> clazz = entry.getValue().getClass();
            if(!clazz.isAnnotationPresent(GPController.class)) continue;
            String baseUrl="";
            if(clazz.isAnnotationPresent(GPRequestMapping.class)){
                baseUrl=clazz.getAnnotation(GPRequestMapping.class).value();
            }


            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if(!method.isAnnotationPresent(GPRequestMapping.class))continue;
                //正则
                String url = ("/"+baseUrl+"/"+method.getAnnotation(GPRequestMapping.class).value()).replace("/+","/");
                handlerMapping.put(url,method);
                System.out.println("请求地址："+url+"请求的后台方法："+method);
            }
        }
    }

    private void doAutowired() {
        if (ioc.isEmpty()) return;
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(GPAutowired.class)) continue;
                GPAutowired autowired = field.getAnnotation(GPAutowired.class);
                String beanName = autowired.value().trim();
                if("".equals(beanName)){
                    beanName=field.getType().getName();
                }
                //强制暴力访问
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(),ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void doInstance() {
        if (classNames.isEmpty()) {
            return;
        }
        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(GPController.class)){
                String beanName = toLowerFirstCase(clazz.getSimpleName());
                ioc.put(beanName,clazz.newInstance());
                }else if(clazz.isAnnotationPresent(GPService.class)){
                   //默认的类名首字母小写
                    String beanName = toLowerFirstCase(clazz.getSimpleName());

                   //自定义命名
                    GPService annotation = clazz.getAnnotation(GPService.class);
                    if (!"".equals(annotation.value())){
                        beanName=annotation.value();
                    }
                    Object obj = clazz.newInstance();
                    ioc.put(beanName,obj);
                   //接口不能实例化，实例化他的实现类
                    for (Class<?> i : clazz.getInterfaces()) {
                        if(ioc.containsKey(i.getName())) throw new Exception("the beanName is exist!!!");
                        //把接口也放入到容器中
                        ioc.put(i.getName(),obj);
                    }
                }else continue;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0]+=32;
        return String.valueOf(chars);
    }

    private void doScanner(String scanPackage) {

        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        System.out.println(url.getFile());
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                System.out.println(file.getName());
                String className = scanPackage + "." + file.getName().replace(".class", "");
                System.out.println(className);
                classNames.add(className);
            }
        }
    }

    private void doLoadConfig(String contextConfigLocation) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfit.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
