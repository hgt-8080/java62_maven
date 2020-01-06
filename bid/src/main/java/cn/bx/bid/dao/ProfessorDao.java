package cn.bx.bid.dao;

import cn.bx.bid.entity.Professor;
import cn.bx.bid.entity.Project;
import cn.bx.bid.entity.WorkHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface ProfessorDao {
    public List<Professor> search(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize, @Param("name") String name, @Param("tele") String tele, @Param("idcard") String idcard);
    public int searchTotal(@Param("name") String name, @Param("tele") String tele, @Param("idcard") String idcard);

    Professor get(long id);
    //删除指定专家的工作记录，不单独设置historyDao

    /**
     *
     * @param id 专家号
     * @param hisid 历史记录号
     * @return
     */
    int delHis(@Param("pid") long id, @Param("hisid") long hisid);

    int addHistory(WorkHistory w);
    //根据专家号获得项目信息
    public List<Project> findProjs(long id);
}
