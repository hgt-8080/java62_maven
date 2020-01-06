package cn.bx.bid.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.bx.bid.entity.User;

//映射user.xml中的操作
public interface UserDao {

	// 分页
	// #{0}，#{1},#{param0},#{param1}?args0
	// 用注解
	//public List<User> paging(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

	//public List<User> page2(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize, @Param("name") String name,
	//		@Param("tel") String tel, @Param("enterdate") Date enterDate);

	//public List<User> page3(Map<String, Object> m);

	// 根据条件查询总数
	//public int findTotal2(@Param("name") String name, @Param("tel") String tel, @Param("enterdate") Date enterDate);

	// 求总条数
	//public int findTotal();

	// 查询全部
	//public List<User> findALL();

	User get(long id);

	int add(User u);

	int update(User u);

	int del(long id);

	/* 批量删除 */
	int batchDel(@Param("ids") long[] ids);

	//public List<User> find(@Param("sex") int sex);

	public List<User> search(@Param("pageNo") int pageNo, @Param("pageSize") int limit, @Param("name") String name,
                             @Param("start") Date start, @Param("end") Date end);

	public int searchTotal(@Param("name") String name, @Param("start") Date start, @Param("end") Date end);

    int checkUserNameRepeat(@Param("id") Long id, @Param("userName") String userName);

    User login(@Param("userName") String username, @Param("pass") String password);
}
