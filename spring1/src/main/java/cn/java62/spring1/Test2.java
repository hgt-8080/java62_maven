package cn.java62.spring1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {
    //spring,交出对象的构建权 给spring
    public static void main(String[] args) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
        Person p=(Person)ac.getBean("p");//set 原则。。。无参的构造
        p.say();
    }
}
