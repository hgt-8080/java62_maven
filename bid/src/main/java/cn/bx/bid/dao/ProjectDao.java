package cn.bx.bid.dao;

import cn.bx.bid.entity.Professor;
import cn.bx.bid.entity.ProfessorEnroll;
import cn.bx.bid.entity.Project;
import cn.bx.bid.entity.RandomName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectDao {
    public List<Project> search(@Param("pageNo") int pageNo, @Param("pageSize") int limit, @Param("name") String name, @Param("category") String category);
    public int searchTotal(@Param("name") String name, @Param("category") String category);
    //查找项目参与的专家
    public List<ProfessorEnroll> searchProfessorInProjs(Long pid);

    //项目信息
    public Project get(Long id);

    //根据项目编号，获得能来的以及没有确定不来的专家数量
    public int getPrentProfessors(long id);
    //根据项目编号，获得能来的专家数量
    public int getComingProfessors(long id);
    //根据类型和查找专家，距离不能在这里做限制,同时排除掉已经选择的专家
    public List<Professor> getProfessorsForChoose(@Param("prjid") long prjid, @Param("category") String category);

    public int saveChoose(RandomName r);

    //确定能来的专家，修改状态
    int profRoleProject(@Param("pfid") long pfid, @Param("proid") long proid);

    int profnotPresentProject(@Param("pfid") long pfid, @Param("projectid") long projectid, @Param("reason") String reason);

    //修改项目状态，为state
    void updateProjectState(@Param("id") long projectid, @Param("state") String state);

    int increaseNotPresentNum(long pfid);

    int settleProject(@Param("projid") long projid, @Param("dealCompany") String dealCompany, @Param("settlePrice") double settlePrice);
}
