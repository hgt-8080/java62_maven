package cn.bx.bid.test;
import cn.bx.bid.biz.UserBiz;
import cn.bx.bid.dao.UserDao;
import cn.bx.bid.service.ProjectService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class TestMyBatis {
    public static void main(String[] args) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-config.xml");
//        UserBiz userBiz=ac.getBean(UserBiz.class);
//        System.out.println(userBiz.login("admin","123456"));
//
//        UserDao userdao=ac.getBean(UserDao.class);
//        System.out.println(userdao.getClass().getName()+"???????????????????");
//        Class []cr=userdao.getClass().getInterfaces();
//        System.out.println(Arrays.toString(cr));
        ProjectService projectService=ac.getBean(ProjectService.class);
        projectService.profnotPresentProject(2L,365,"被狗咬了");

    }
}
