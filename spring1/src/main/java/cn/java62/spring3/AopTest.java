package cn.java62.spring3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
    public static void main(String[] args) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("aop.xml");
        UserService us= (UserService) ac.getBean("userService");
        System.out.println("%%%%%%%%%%%%%%%%%:"+us.getClass().getName());
        User user=new User(1000,"wang","123","133939933");
        int r=us.add(user);
        System.out.println("执行的结果是："+r);
        us.update(user);
        User user2=new User(1001,"w","22333","4444");
        int r2=us.add(user2);
        System.out.println("返回的结果是======================="+r2);
    }
}
