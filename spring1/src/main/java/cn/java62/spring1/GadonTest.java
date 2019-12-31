package cn.java62.spring1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GadonTest {
    public static void main(String[] args) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
//        Gardon g=(Gardon)ac.getBean("g");
//        g.intro();
        Gardon g=(Gardon)ac.getBean("g2");
        g.intro();
    }
}
