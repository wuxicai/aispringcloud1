package dongtaidaili;

public class ProxyTest {
    public static void main(String[] args) {
//        UserManager userManager=(UserManager) new CGLibProxy().createproxyObject(new UserManagerImpl());
//        System.out.println(userManager);
//        userManager.addUser("1","123");

        JDKProxy jdkProxy = new JDKProxy();
        UserManager userManagerJdk=(UserManager) jdkProxy.newProxy(new UserManagerImpl());
        userManagerJdk.delUser("1");

    }
}
