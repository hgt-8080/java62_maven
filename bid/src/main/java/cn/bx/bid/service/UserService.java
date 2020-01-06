package cn.bx.bid.service;

import cn.bx.bid.dao.UserDao;
import cn.bx.bid.entity.Page;
import cn.bx.bid.entity.User;
import cn.bx.bid.util.MyBatisUtil;
import java.util.*;

import org.apache.ibatis.session.SqlSession;

//M V C
public class UserService {
	public static final int PAGE_SIZE = Page.PAGE_SIZE;

	public int add(User u) {
		SqlSession s = MyBatisUtil.get();
		try {
			UserDao userDao = s.getMapper(UserDao.class);
			return userDao.add(u);
		} finally {
			s.commit();
			MyBatisUtil.close(s);
		}
	}
	public int update(User u) {
		SqlSession s = MyBatisUtil.get();
		try {
			UserDao userDao = s.getMapper(UserDao.class);
			return userDao.update(u);
		} finally {
			s.commit();
			MyBatisUtil.close(s);
		}
	}
	public int batchDel(long[] ids) {
		SqlSession s = MyBatisUtil.get();
		try {
			UserDao userDao = s.getMapper(UserDao.class);
			return userDao.batchDel(ids);
		} finally {
			s.commit();
			MyBatisUtil.close(s);
		}
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

		SqlSession s = MyBatisUtil.get();
		try {
			UserDao userDao = s.getMapper(UserDao.class);
			List<User> list = userDao.search(pageNo, limit, name, start, end);
			int total = userDao.searchTotal(name, start, end);
			Page<User> p = new Page<User>(total, limit, list);
			return p;
		} finally {
			MyBatisUtil.close(s);
		}

	}

	public User get(long id) {
		SqlSession s = MyBatisUtil.get();
		try {
			UserDao userDao = s.getMapper(UserDao.class);
			return userDao.get(id);

		} finally {
			MyBatisUtil.close(s);
		}
	}

    public boolean checkUserNameRepeat(Long id, String name) {
		SqlSession s = MyBatisUtil.get();
		try {
			UserDao userDao = s.getMapper(UserDao.class);
			return userDao.checkUserNameRepeat(id,name)>0;

		} finally {
			MyBatisUtil.close(s);
		}
    }

    public User login(String username, String password) {
		SqlSession s = MyBatisUtil.get();
		try {
			UserDao userDao = s.getMapper(UserDao.class);
			return userDao.login(username,password);

		} finally {
			MyBatisUtil.close(s);
		}
    }
}
