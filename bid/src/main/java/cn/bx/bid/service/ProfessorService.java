package cn.bx.bid.service;

import cn.bx.bid.dao.ProfessorDao;
import cn.bx.bid.dao.UserDao;
import cn.bx.bid.entity.*;
import cn.bx.bid.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

//M V C
public class ProfessorService {
	public static final int PAGE_SIZE = Page.PAGE_SIZE;

//	public int add(User u) {
//		SqlSession s = MyBatisUtil.get();
//		try {
//			UserDao userDao = s.getMapper(UserDao.class);
//			return userDao.add(u);
//		} finally {
//			s.commit();
//			MyBatisUtil.close(s);
//		}
//	}
//	public int update(User u) {
//		SqlSession s = MyBatisUtil.get();
//		try {
//			UserDao userDao = s.getMapper(UserDao.class);
//			return userDao.update(u);
//		} finally {
//			s.commit();
//			MyBatisUtil.close(s);
//		}
//	}
//	public int batchDel(long[] ids) {
//		SqlSession s = MyBatisUtil.get();
//		try {
//			UserDao userDao = s.getMapper(UserDao.class);
//			return userDao.batchDel(ids);
//		} finally {
//			s.commit();
//			MyBatisUtil.close(s);
//		}
//	}


	public Page<Professor> paging(int pageNo, int limit, String name, String tele,String id) {

		SqlSession s = MyBatisUtil.get();
		try {
			ProfessorDao userDao = s.getMapper(ProfessorDao.class);
			List<Professor> list = userDao.search(pageNo, limit, name, tele, id);
			int total = userDao.searchTotal(name, tele, id);
			Page<Professor> p = new Page<Professor>(total, limit, list);
			return p;
		} finally {
			MyBatisUtil.close(s);
		}

	}

	public Professor get(long id) {
		SqlSession s = MyBatisUtil.get();
		try {
			ProfessorDao userDao = s.getMapper(ProfessorDao.class);

			 Professor  p = userDao.get(id);
			return p;
		} finally {
			MyBatisUtil.close(s);
		}
	}

    public int delHis(long id, long hisid) {

			SqlSession s = MyBatisUtil.get();
			try {
				ProfessorDao userDao = s.getMapper(ProfessorDao.class);

				int p = userDao.delHis(id,hisid);
				return p;
			} finally {
				MyBatisUtil.close(s);
			}

    }

	public int addHistory(WorkHistory w) {
		SqlSession s = MyBatisUtil.get();
		try {
			ProfessorDao userDao = s.getMapper(ProfessorDao.class);

			int p = userDao.addHistory(w);
			return p;
		} finally {
			MyBatisUtil.close(s);
		}
	}
	public List<Project>  findProjs(long  profid) {
		SqlSession s = MyBatisUtil.get();
		try {
			ProfessorDao userDao = s.getMapper(ProfessorDao.class);

			List<Project> p = userDao.findProjs(profid);
			return p;
		} finally {
			MyBatisUtil.close(s);
		}
	}
//	public User get(long id) {
//		SqlSession s = MyBatisUtil.get();
//		try {
//			UserDao userDao = s.getMapper(UserDao.class);
//			return userDao.get(id);
//
//		} finally {
//			MyBatisUtil.close(s);
//		}
//	}
}
