public class leijiazaiqi {
    public static void main(String[] args) throws ClassNotFoundException {
        //获取系统类的加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        //获取扩展类加载器  系统类加载器的父类加载器
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);
        //获取扩展类加载器的父类加载器  根加载器
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);
        //自定义类是由APP加载器加载的
        ClassLoader leijiazaiqi = Class.forName("leijiazaiqi").getClassLoader();
        System.out.println(leijiazaiqi);
        //java类是有根加载器加载的
        leijiazaiqi = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(leijiazaiqi);
        /*

        双亲委派机制：java.lang.String --->
        * D:\JAVA\jre\lib\charsets.jar;D:\JAVA\jre\lib\deploy.jar;
        * D:\JAVA\jre\lib\ext\access-bridge-64.jar;D:\JAVA\jre\lib\ext\cldrdata.jar;
        * D:\JAVA\jre\lib\ext\dnsns.jar;D:\JAVA\jre\lib\ext\jaccess.jar;
        * D:\JAVA\jre\lib\ext\jfxrt.jar;D:\JAVA\jre\lib\ext\localedata.jar;
        * D:\JAVA\jre\lib\ext\nashorn.jar;D:\JAVA\jre\lib\ext\sunec.jar;
        * D:\JAVA\jre\lib\ext\sunjce_provider.jar;D:\JAVA\jre\lib\ext\sunmscapi.jar;
        * D:\JAVA\jre\lib\ext\sunpkcs11.jar;D:\JAVA\jre\lib\ext\zipfs.jar;
        * D:\JAVA\jre\lib\javaws.jar;D:\JAVA\jre\lib\jce.jar;
        * D:\JAVA\jre\lib\jfr.jar;D:\JAVA\jre\lib\jfxswt.jar;
        * D:\JAVA\jre\lib\jsse.jar;D:\JAVA\jre\lib\management-agent.jar;
        * D:\JAVA\jre\lib\plugin.jar;D:\JAVA\jre\lib\resources.jar;
        * D:\JAVA\jre\lib\rt.jar;D:\workspace\aispringcloud\reflect\target\classes;
        * D:\maven\repository\org\springframework\boot\spring-boot-starter-web\2.0.7.RELEASE\spring-boot-starter-web-2.0.7.RELEASE.jar;
        * D:\maven\repository\org\springframework\boot\spring-boot-starter\2.0.7.RELEASE\spring-boot-starter-2.0.7.RELEASE.jar;
        * D:\maven\repository\org\springframework\boot\spring-boot\2.0.7.RELEASE\spring-boot-2.0.7.RELEASE.jar;
        * D:\maven\repository\org\springframework\boot\spring-boot-autoconfigure\2.0.7.RELEASE\spring-boot-autoconfigure-2.0.7.RELEASE.jar;
        * D:\maven\repository\org\springframework\boot\spring-boot-starter-logging\2.0.7.RELEASE\spring-boot-starter-logging-2.0.7.RELEASE.jar;
        * D:\maven\repository\ch\qos\logback\logback-classic\1.2.3\logback-classic-1.2.3.jar;D:\maven\repository\ch\qos\logback\logback-core\1.2.3\logback-core-1.2.3.jar;
        * D:\maven\repository\org\slf4j\slf4j-api\1.7.25\slf4j-api-1.7.25.jar;D:\maven\repository\org\apache\logging\log4j\log4j-to-slf4j\2.10.0\log4j-to-slf4j-2.10.0.jar;
        * D:\maven\repository\org\apache\logging\log4j\log4j-api\2.10.0\log4j-api-2.10.0.jar;D:\maven\repository\org\slf4j\jul-to-slf4j\1.7.25\jul-to-slf4j-1.7.25.jar;
        * D:\maven\repository\javax\annotation\javax.annotation-api\1.3.2\javax.annotation-api-1.3.2.jar;
        * D:\maven\repository\org\springframework\spring-core\5.0.11.RELEASE\spring-core-5.0.11.RELEASE.jar;
        * D:\maven\repository\org\springframework\spring-jcl\5.0.11.RELEASE\spring-jcl-5.0.11.RELEASE.jar;
        * D:\maven\repository\org\yaml\snakeyaml\1.19\snakeyaml-1.19.jar;
        * D:\maven\repository\org\springframework\boot\spring-boot-starter-json\2.0.7.RELEASE\spring-boot-starter-json-2.0.7.RELEASE.jar;
        * D:\maven\repository\com\fasterxml\jackson\core\jackson-databind\2.9.7\jackson-databind-2.9.7.jar;
        * D:\maven\repository\com\fasterxml\jackson\core\jackson-annotations\2.9.0\jackson-annotations-2.9.0.jar;
        * D:\maven\repository\com\fasterxml\jackson\core\jackson-core\2.9.7\jackson-core-2.9.7.jar;
        * D:\maven\repository\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.9.7\jackson-datatype-jdk8-2.9.7.jar;
        * D:\maven\repository\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.9.7\jackson-datatype-jsr310-2.9.7.jar;
        * D:\maven\repository\com\fasterxml\jackson\module\jackson-module-parameter-names\2.9.7\jackson-module-parameter-names-2.9.7.jar;
        * D:\maven\repository\org\springframework\boot\spring-boot-starter-tomcat\2.0.7.RELEASE\spring-boot-starter-tomcat-2.0.7.RELEASE.jar;
        * D:\maven\repository\org\apache\tomcat\embed\tomcat-embed-core\8.5.35\tomcat-embed-core-8.5.35.jar;
        * D:\maven\repository\org\apache\tomcat\embed\tomcat-embed-el\8.5.35\tomcat-embed-el-8.5.35.jar;
        * D:\maven\repository\org\apache\tomcat\embed\tomcat-embed-websocket\8.5.35\tomcat-embed-websocket-8.5.35.jar;
        * D:\maven\repository\org\hibernate\validator\hibernate-validator\6.0.13.Final\hibernate-validator-6.0.13.Final.jar;
        * D:\maven\repository\javax\validation\validation-api\2.0.1.Final\validation-api-2.0.1.Final.jar;
        * D:\maven\repository\org\jboss\logging\jboss-logging\3.3.2.Final\jboss-logging-3.3.2.Final.jar;
        * D:\maven\repository\com\fasterxml\classmate\1.3.4\classmate-1.3.4.jar;
        * D:\maven\repository\org\springframework\spring-web\5.0.11.RELEASE\spring-web-5.0.11.RELEASE.jar;
        * D:\maven\repository\org\springframework\spring-beans\5.0.11.RELEASE\spring-beans-5.0.11.RELEASE.jar;
        * D:\maven\repository\org\springframework\spring-webmvc\5.0.11.RELEASE\spring-webmvc-5.0.11.RELEASE.jar;
        * D:\maven\repository\org\springframework\spring-aop\5.0.11.RELEASE\spring-aop-5.0.11.RELEASE.jar;
        * D:\maven\repository\org\springframework\spring-context\5.0.11.RELEASE\spring-context-5.0.11.RELEASE.jar;
        * D:\maven\repository\org\springframework\spring-expression\5.0.11.RELEASE\spring-expression-5.0.11.RELEASE.jar;
        * D:\maven\repository\javax\xml\bind\jaxb-api\2.3.0\jaxb-api-2.3.0.jar;D:\maven\repository\com\sun\xml\bind\jaxb-impl\2.3.0\jaxb-impl-2.3.0.jar;
        * D:\maven\repository\com\sun\xml\bind\jaxb-core\2.3.0\jaxb-core-2.3.0.jar;D:\maven\repository\javax\activation\activation\1.1.1\activation-1.1.1.jar;
        * D:\IDEA\IntelliJ IDEA 2017.3.5\lib\idea_rt.jar

         * */
        System.out.println(System.getProperty("java.class.path"));
    }
}

