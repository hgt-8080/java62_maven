package cn.java62.spring3;

public class UserService {
    public int add(User u){
        System.out.println("新用户"+u+"被加入数据库了。、。");
        return 1;
    }
    public void update(User u){
        System.out.println("更新用户"+u.getId()+"为："+u);
    }

    public java.util.List<User> search(String name,String tele){
        System.out.println("用name="+name+",电话："+tele+"搜素复合条件的用户信息");
        return null;
    }
}
