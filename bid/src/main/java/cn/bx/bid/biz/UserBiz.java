package cn.bx.bid.biz;

import cn.bx.bid.dao.UserDao;
import cn.bx.bid.entity.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserBiz {
//    @Resource(name="dt")
//    private SqlSessionTemplate  template;
//
//    public User login(String u, String p){
//        UserDao userDao=template.getMapper(UserDao.class);
//        return userDao.login(u,p);
//    }

    @Resource
    private UserDao userDao;

    public User login(String u, String p){
        return userDao.login(u,p);
    }
}
