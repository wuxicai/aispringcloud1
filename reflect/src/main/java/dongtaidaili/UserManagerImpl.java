package dongtaidaili;

public class UserManagerImpl implements UserManager{

    String name;
    @Override
    public void addUser(String id, String password) {
        System.out.println("添加成功!id : "+id+"密码 ： "+password);
    }

    @Override
    public void delUser(String id) {
        System.out.println("删除成功!id : "+id);
    }
}
