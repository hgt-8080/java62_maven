package cn.bx.bid.service;

import cn.bx.bid.dao.RoleDao;
import cn.bx.bid.dao.UserDao;
import cn.bx.bid.entity.Page;
import cn.bx.bid.entity.User;
import cn.bx.bid.entity.Userrole;
import cn.bx.bid.util.MyBatisUtil;
import java.util.*;

import org.apache.ibatis.session.SqlSession;

//M V C
public class RoleService {
	public static final int PAGE_SIZE = Page.PAGE_SIZE;

	public List<Userrole> findALL() {
		SqlSession s = MyBatisUtil.get();
		try {
			RoleDao dao = s.getMapper(RoleDao.class);
			return dao.findALL();
		} finally {
			MyBatisUtil.close(s);
		}
	}
}
