package cn.bx.bid.service;

import cn.bx.bid.dao.UserDao;
import cn.bx.bid.entity.Page;
import cn.bx.bid.entity.User;
import cn.bx.bid.util.MyBatisUtil;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

//M V C
@Service
@Transactional
public class UserService {
    public static final int PAGE_SIZE = Page.PAGE_SIZE;
    @Resource
    //@Autowired
    private UserDao userDao;

    public int add(User u) {

        return userDao.add(u);

    }

    public int update(User u) {

        return userDao.update(u);

    }

    public int batchDel(long[] ids) {

        return userDao.batchDel(ids);

    }
//	// Map
//	public Page<User> paging(int pageNo) {
//		SqlSession s = MyBatisUtil.get();
//		try {
//			UserDao userDao = s.getMapper(UserDao.class);
//			List<User> list = userDao.paging(pageNo, PAGE_SIZE);
//			int total = userDao.findTotal();
//			Page<User> p = new Page<User>(0, total, list);
//			return p;
//		} finally {
//			MyBatisUtil.close(s);
//		}
//	}

    public Page<User> paging(int pageNo, int limit, String name, Date start, Date end) {


        List<User> list = userDao.search(pageNo, limit, name, start, end);
        int total = userDao.searchTotal(name, start, end);
        Page<User> p = new Page<User>(total, limit, list);
        return p;


    }

    public User get(long id) {

        return userDao.get(id);

    }

    public boolean checkUserNameRepeat(Long id, String name) {

        return userDao.checkUserNameRepeat(id, name) > 0;


    }

    public User login(String username, String password) {

        return userDao.login(username, password);


    }
}
