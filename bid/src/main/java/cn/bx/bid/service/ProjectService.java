package cn.bx.bid.service;

import cn.bx.bid.dao.ProfessorDao;
import cn.bx.bid.dao.ProjectDao;
import cn.bx.bid.entity.*;
import cn.bx.bid.util.Client;
//import cn.bx.bid.util.MyBatisUtil;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

//M V C
@Service
@Transactional(readOnly = true)
//@Scope("prototype")
public class ProjectService {
    public static final int PAGE_SIZE = Page.PAGE_SIZE;

    public ProjectService(){
        System.out.println("project servierce创建成功");
    }
    @Resource
    private ProjectDao projectDao;

    public Page<Project> paging(int pageNo, int limit, String name, String category) {

//        SqlSession s = MyBatisUtil.get();
//        try {
           // ProjectDao userDao = s.getMapper(ProjectDao.class);
            List<Project> list = projectDao.search(pageNo, limit, name, category);
            int total = projectDao.searchTotal(name, category);
            Page<Project> p = new Page<>(total, limit, list,pageNo);
            return p;
//        } finally {
//            MyBatisUtil.close(s);
//        }

    }

    public Page<ProfessorEnroll> searchProfessorInProjs(Long pid) {
//        SqlSession s = MyBatisUtil.get();
//        try {
          //  ProjectDao userDao = s.getMapper(ProjectDao.class);
            List<ProfessorEnroll> list = projectDao.searchProfessorInProjs(pid);

            return new Page<ProfessorEnroll>(list.size(), list);
//        } finally {
//            MyBatisUtil.close(s);
//        }
    }

    public Project get(Long id) {
//        SqlSession s = MyBatisUtil.get();
//        try {
           // ProjectDao userDao = s.getMapper(ProjectDao.class);

            return projectDao.get(id);
//        } finally {
//            MyBatisUtil.close(s);
//        }
    }

    public int getPrentProfessors(long id) {
//        SqlSession s = MyBatisUtil.get();
//        try {
           // ProjectDao userDao = s.getMapper(ProjectDao.class);

            return projectDao.getPrentProfessors(id);
//        } finally {
//            MyBatisUtil.close(s);
//        }
    }

    //根据类型和查找专家，距离不能在这里做限制,同时排除掉已经选择的专家
    public List<Professor> getProfessorsForChoose(long prjid, String category) {
//        SqlSession s = MyBatisUtil.get();
//        try {
           // ProjectDao userDao = s.getMapper(ProjectDao.class);
            return projectDao.getProfessorsForChoose(prjid, category);
//        } finally {
//            MyBatisUtil.close(s);
//        }
    }

    //保存选择记录，并发短信
    public int saveChooseResult(Project p, long[] ids) {
//        SqlSession s = MyBatisUtil.get();
//        try {
            //ProjectDao userDao = s.getMapper(ProjectDao.class);
            for (long id : ids) {
                RandomName r = new RandomName();
                r.setProjectid(p.getId());
                r.setProfessorid(id);
                r.setBePresent("不确定");

                r.setCreatedate(new Date());
                r.setIsNotice("是");
                r.setMsg("邀请您参加于" + p.getOpenDate() + " ，在" + p.getAddress() + "举行的" + p.getProjectName() + " 招标评标会议，请准时参加，不能参加请及时告知，联系人："+p.getLinkMan()+",电话"+p.getMobliePhone());
                r.setStatus("否");
                projectDao.saveChoose(r);
                //Client.sendMsm("p.getMobilePhone",r.getMsg());
            }

            return ids.length;
//        } finally {
//            MyBatisUtil.close(s);
//        }
    }
    //都加上判断是否要修改状态的判断
    /**
     * 确定能来
     * @param profid    专家号
     * @param projectid 项目号
     * @return
     */
    @Transactional(readOnly = false)
    public int profRoleProject(long profid, long projectid) {

//        SqlSession s = MyBatisUtil.get();
//        try {
           // ProjectDao userDao = s.getMapper(ProjectDao.class);

            int r = projectDao.profRoleProject(profid, projectid);
            Project proj = projectDao.get(projectid);
            //找到能来的人数
            int cancome = projectDao.getComingProfessors(projectid);
            if (cancome >= proj.getProfessorNum()) {
                //人到齐了，修改项目状态为4，抽签完成
                projectDao.updateProjectState(projectid, "4");
            } else {//补抽中
                projectDao.updateProjectState(projectid, "3");
            }
            return r;
//        } finally {
//            MyBatisUtil.close(s);
//        }

    }

    /**
     * 缺席操作
     *
     * @param pfid
     * @param projectid
     * @param reason
     * @return
     */
    @Transactional(readOnly = true)
    public int profnotPresentProject(long pfid, long projectid, String reason) {

//        SqlSession s = MyBatisUtil.get();
//        try {
           // ProjectDao userDao = s.getMapper(ProjectDao.class);
            int r = projectDao.profnotPresentProject(pfid, projectid, reason);
            //记录专家未到次数
            projectDao.increaseNotPresentNum(pfid);

            Project proj = projectDao.get(projectid);
            //找到能来的人数
            int cancome = projectDao.getComingProfessors(projectid);
            if (cancome >= proj.getProfessorNum()) {
                //人到齐了，修改项目状态为4，抽签完成
                projectDao.updateProjectState(projectid, "4");
            } else {//补抽中
                projectDao.updateProjectState(projectid, "3");
            }

            //
            return r;
//        } finally {
//            MyBatisUtil.close(s);
//        }
    }
    //设置中标企业信息，并修改项目状态为5,
    public int settleProject(long projid, String dealCompany, double settlePrice) {

                //ProjectDao userDao = s.getMapper(ProjectDao.class);
                int r=projectDao.settleProject(projid,dealCompany,settlePrice);

                return r;
//            } finally {
//                MyBatisUtil.close(s);
//            }
       // }
    }
}
