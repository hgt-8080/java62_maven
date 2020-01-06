package cn.bx.bid.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bx.bid.entity.*;
import cn.bx.bid.service.ProfessorService;
import cn.bx.bid.service.ProjectService;
import cn.bx.bid.util.DocUtil;
import com.alibaba.fastjson.JSON;
import cn.bx.bid.service.RoleService;
import cn.bx.bid.service.UserService;
import cn.bx.bid.util.StringUtil;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = "*.do")
public class MainServlet extends HttpServlet {
    private static final Logger Log = LoggerFactory.getLogger(MainServlet.class);

    /**
     * Constructor of the object.
     */
    public MainServlet() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();// /login.do
        System.out.println(path);
        path = path.substring(1, path.length() - 3);

//        if(!path.equals("ulogin")){
//            if(request.getSession().getAttribute("USER_LOGIN")==null){
//                //response.sendError(200,"您还没有登录，不能访问！");
//                response.getWriter().print("no login,error!");
//                return;
//            }
//        }
        try {
            Method m = this.getClass().getDeclaredMethod(path, HttpServletRequest.class, HttpServletResponse.class);
            Object o = m.invoke(this, request, response);
            //if(o==null)
            //    return ;
            //按照json的格式发给客户端
        } catch (Exception e) {
            e.printStackTrace();
        }

//		if ("userSearch".equals(path)) {
//			userSearch(request, response);
//		} else if ("userAdd".equals(path)) {
//			userAdd(request, response);
//		} else if ("findRoles".equals(path)) {
//			findRoles(request, response);
//		} else if ("userGet".equals(path)) {
//			userGet(request, response);
//		}else if ("userUpdate".equals(path)) {
//			userUpdate(request, response);
//		}else if ("batchDel".equals(path)) {
//			batchDel(request, response);
//		}else if ("professorSearch".equals(path)) {
//            professorSearch(request, response);
//        }else if ("professorGet".equals(path)) {
//			professorGet(request, response);
//		}else if ("exportProToWord".equals(path)) {
//			exportProToWord(request, response);
//		}else if ("workhistoryDel".equals(path)) {
//			workhistoryDel(request, response);
//		}else if ("historyAdd".equals(path)) {
//			historyAdd(request, response);
//		}

    }

    public void professorAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Professor f = new Professor();
        f.setAddress(request.getParameter("address"));
        f.setAdministrative(request.getParameter("administrative"));
        f.setBirthday(StringUtil.toDate(request.getParameter("birthday")));
        f.setEducation(request.getParameter("education"));
        f.setGender(request.getParameter("gender"));
        f.setEmploycompany(request.getParameter("employcompany"));
        f.setIdentitynumber(request.getParameter("identitynumber"));
        f.setPhone(request.getParameter("phone"));
        f.setMobliephone(request.getParameter("mobliephone"));
        f.setInstitution(request.getParameter("institution"));
        f.setCreatedate(new Date());
        User u = (User) request.getSession().getAttribute("USER_LOGIN");
        f.setCreateby(u.getUserName());

    }

    //确定参加
    public void profRoleProject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long pfid = Long.parseLong(request.getParameter("pfid"));//专家projid
        long projid = Long.parseLong(request.getParameter("projid"));//项目

        int r=this.projectService.profRoleProject(pfid,projid);

        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("{\"result\":"+(r>0)+"}");
        out.flush();

    }

    //不参加
    public void profnotPresentProject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long pfid = Long.parseLong(request.getParameter("pfid"));
        long projectid = Long.parseLong(request.getParameter("proid"));
        String reason = request.getParameter("reason");
        //待续
        int r=this.projectService.profnotPresentProject(pfid,projectid,reason);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("{\"result\":"+(r>0)+"}");
        out.flush();

    }

    public void inputSettle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long projid=Long.parseLong(request.getParameter("projid"));
        String dealCompany=request.getParameter("dealCompany");
        double settlePrice=Double.parseDouble(request.getParameter("settlePrice"));
        int r=this.projectService.settleProject(projid,dealCompany,settlePrice);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("{\"result\":"+(r>0)+"}");
        out.flush();
    }
    //开始抽选
    public void randomSelect(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long pjid = Long.parseLong(request.getParameter("pjid"));
        String category = request.getParameter("category");
        Double distance = null;
        try {
            distance = Double.parseDouble(request.getParameter("distance"));
        } catch (Exception e) {
        }
        Project p = this.projectService.get(pjid);
        //考虑了能来的，去掉确定不来的，不确定的也加上了
        int hasSelected = this.projectService.getPrentProfessors(pjid);
        String msg = "";
        try {
            if (p.getStatus() == 1) {
                msg = "{\"result\":false,\"msg\":\"此项目已经结束，不能再选\"}";
            } else if (hasSelected >= p.getProfessorNum()) {
                msg = "{\"result\":false,\"msg\":\"此项目人数已经满足，无需再选\"}";
            } else {
                List<Professor> list = projectService.getProfessorsForChoose(pjid, category);
                //
                if (distance != null ) {//distance>0
                    //根据距离过滤
                    Jw location = getPos(p.getAddress());//项目开标地址的经纬度
                    System.out.println("开会地点。。。。。。" + location);
                    if (location != null) {
                        Iterator<Professor> r = list.iterator();
                        while (r.hasNext()) {
                            Professor t = r.next();
                            Jw l2 = getPos(t.getAddress());//获得专家的经纬度
                            if (l2 == null)
                                continue;//地址不确定的先不考虑
                            System.out.println("专家：" + t.getProfessorname() + " 住址：" + l2 + ">>>距离：" + l2.dis(location) + "公里");
                            if (l2.dis(location) > distance) {
                                r.remove();//删除当前专家
                            }
                        }
                    }
                }
                if (list.size() < (p.getProfessorNum() - hasSelected)) {
                    msg = "{\"result\":false,\"msg\":\"没有找到满足人数的专家，缺少"+(Math.abs(list.size() - (p.getProfessorNum() - hasSelected)))+"人，先修改条件或者重新选择距离再试\"}";
                } else {
                    //开始随机选择
                    int need = p.getProfessorNum() - hasSelected;
                    long ids[] = new long[need];
                    int index = 0;
                    while (true) {
                        int r = (int) (Math.random() * list.size());
                        ids[index++] = list.get(r).getId();
                        list.remove(r);
                        if (index >= ids.length)
                            break;
                    }
                    //保存到数据库
                    int result = this.projectService.saveChooseResult(p, ids);
                    msg = "{\"result\":true,\"msg\":\"成功抽选"+need+"名专家！\"}";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "{\"result\":false,\"msg\":\"网络不稳定，稍后再试！\"}";
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(msg);
        out.flush();

    }

    //考虑把key放入外部文件中，好灵活配置
    public static final String url = "http://api.map.baidu.com/geocoder/v2/?address=%s&output=json&ak=B4bd9e22c0a580cd04b0e84b17bf9eb6";
	/**
	获得省：可以先获得经纬度，然后利用经纬度获得省
	http://api.map.baidu.com/geocoder/v2/?output=json&ak=B4bd9e22c0a580cd04b0e84b17bf9eb6&location=lat,lng

	*/
    // 获得经纬度
    private Jw getPos(String addr) {
        String str = getDistance(String.format(url, addr));
        Map m = (Map) JSON.parse(str);

        if (!new Integer(0).equals(m.get("status"))) {
            return null;
        }
        Map m2 = (Map) ((Map) m.get("result")).get("location");
        System.out.println(m2);
        double lng = Double.parseDouble(m2.get("lng").toString());
        double lat = Double.parseDouble(m2.get("lat").toString());
        return new Jw(lat, lng);
    }

    //
    public String getDistance(String url) {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(url);

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                // System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
                return EntityUtils.toString(responseEntity);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public void projectChoosingProfs(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long projid = Long.parseLong(request.getParameter("projid"));

        Page<ProfessorEnroll> p = this.projectService.searchProfessorInProjs(projid);
        String str = JSON.toJSONStringWithDateFormat(p, "yyyy-MM-dd hh时MM分", SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(str);
        out.flush();
    }

    public void projectsSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pageNo = 1;
        try {
            pageNo = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {

        }
        int limit = Page.PAGE_SIZE;
        try {
            limit = Integer.parseInt(request.getParameter("limit"));
        } catch (Exception e) {

        }

        String name = request.getParameter("name");
        String category = request.getParameter("category");
        Page<Project> p = this.projectService.paging(pageNo, limit, name, category);

        String str = JSON.toJSONStringWithDateFormat(p, "yyyy-MM-dd hh时");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(str);
        out.flush();
    }

    public void profprojectshow(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Project> list = this.professorService.findProjs(Long.parseLong(request.getParameter("pid")));
        Page<Project> p = new Page<>(list.size(), list);
        String str = JSON.toJSONStringWithDateFormat(p, "yyyy-MM-dd hh时");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(str);
        out.flush();
    }

    public void ulogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User u = this.userService.login(username, password);
        if (u != null)
            request.getSession().setAttribute("USER_LOGIN", u);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("{\"msg\":" + (u != null) + ",\"obj\":" + JSON.toJSONStringWithDateFormat(u, "yyyy-MM-dd") + "}");
        out.flush();
    }

    //验证用户名是否重复
    public void checkUserNameRepeat(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("userName");
        Long id = null;
        try {
            id = Long.parseLong(request.getParameter("id"));//为了在修改的时候也判断，加了id
        } catch (Exception e) {
        }
        boolean repeat = this.userService.checkUserNameRepeat(id, name);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("{\"msg\":" + repeat + "}");
        out.flush();
    }

    public void workhistoryGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    public void workhistoryEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    public void historyAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //long id = Long.parseLong(request.getParameter("id"));
        long proid = Long.parseLong(request.getParameter("professorid"));
        String strt = request.getParameter("startdate");
        String enddate = request.getParameter("enddate");
        String job = request.getParameter("job");
        String jobduty = request.getParameter("jobduty");
        String empley = request.getParameter("employcompany");
        WorkHistory w = new WorkHistory(proid, job, strt, enddate, jobduty, empley);
        int r = this.professorService.addHistory(w);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("{\"msg\":" + (r > 0) + "}");
        out.flush();
    }

    public void workhistoryDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        long hisid = Long.parseLong(request.getParameter("hisid"));
        int r = this.professorService.delHis(id, hisid);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("{\"msg\":" + (r > 0) + "}");
        out.flush();
    }

    public void exportProToWord(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Professor p = this.professorService.get(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("p", p);
        String path = super.getServletContext().getRealPath("/" + Math.random() + ".doc");
        DocUtil.exportProfessor(path, map);
        File file = new File(path);
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(p.getProfessorname() + ".doc", "UTF-8"));
        FileInputStream fin = new FileInputStream(file);
        byte[] b = new byte[fin.available()];
        fin.read(b);
        fin.close();
        ServletOutputStream out = response.getOutputStream();
        out.write(b);
        out.flush();
        file.delete();//下载后删除文件
    }

    public void professorGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));

        String str = JSON.toJSONStringWithDateFormat(this.professorService.get(id), "yyyy-MM-dd");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(str);
        out.flush();
        // request.setAttribute("page", page);
        // request.getRequestDispatcher("/xxx.jsp").forward(request, response);
    }

    public void professorSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pageNo = 1;
        try {
            pageNo = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {

        }
        int limit = Page.PAGE_SIZE;
        try {
            limit = Integer.parseInt(request.getParameter("limit"));
        } catch (Exception e) {

        }
        String name = request.getParameter("name");
        String tele = request.getParameter("tele");
        String idcard = request.getParameter("idcard");

        // int limit=
        // Page<User> page = userService.paging(pageNo);
        Page<Professor> page = professorService.paging(pageNo, limit, name, tele, idcard);

        String str = JSON.toJSONStringWithDateFormat(page, "yyyy-MM-dd");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(str);
        out.flush();
        // request.setAttribute("page", page);
        // request.getRequestDispatcher("/xxx.jsp").forward(request, response);
    }


    public void userGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        User user = this.userService.get(id);
        String str = JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(str);
        out.flush();
    }

    public void findRoles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Userrole> li = roleService.findALL();
        String str = JSON.toJSONStringWithDateFormat(li, "yyyy-MM-dd");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(str);
        out.flush();
    }

    public void userAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User u = new User();
        u.setName(request.getParameter("name"));
        u.setUserName(request.getParameter("userName"));
        u.setTelephone(request.getParameter("telephone"));
        u.setPassword(request.getParameter("password"));
        u.setUnit(request.getParameter("unit"));
        u.setLandMark(request.getParameter("landmark"));
        u.setRole(Long.parseLong(request.getParameter("role")));
        u.setEnterdate(StringUtil.toDate(request.getParameter("enterdate")));

        int r = userService.add(u);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("{\"msg\":" + (r > 0) + "}");
        out.flush();
    }

    public void userUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = new User();
        u.setId(Long.parseLong(request.getParameter("id")));
        u.setName(request.getParameter("name"));
        u.setUserName(request.getParameter("userName"));
        u.setTelephone(request.getParameter("telephone"));
        u.setPassword(request.getParameter("password"));
        u.setUnit(request.getParameter("unit"));
        u.setLandMark(request.getParameter("landMark"));
        u.setRole(Long.parseLong(request.getParameter("role")));
        u.setEnterdate(StringUtil.toDate(request.getParameter("enterdate")));

        int r = userService.update(u);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("{\"msg\":" + (r > 0) + "}");
        out.flush();
    }

    public void batchDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Log.info(">>>>>>>>>>>>>>>>>>>>");
        String ar[] = request.getParameterValues("ids[]");//特别注意，后面要加[]符号
        //用逗号分隔

        //转换成long数组
        long[] ids = new long[ar.length];
        for (int i = 0; i < ids.length; i++)
            ids[i] = Long.parseLong(ar[i]);

        Log.info(Arrays.toString(ids) + ">>>>被删除了。。。");
        //int r = userService.batchDel(ids);
        int r = (int) (System.nanoTime() % 10);//假删除
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("{\"msg\":" + (r > 0) + "}");
        out.flush();
    }


    public void userSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pageNo = 1;
        try {
            pageNo = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {

        }
        int limit = Page.PAGE_SIZE;
        try {
            limit = Integer.parseInt(request.getParameter("limit"));
        } catch (Exception e) {

        }
        String name = request.getParameter("name");
        Date start = StringUtil.toDate(request.getParameter("startDate"));
        Date end = StringUtil.toDate(request.getParameter("endDate"));

        // int limit=
        // Page<User> page = userService.paging(pageNo);
        Page<User> page = userService.paging(pageNo, limit, name, start, end);

        String str = JSON.toJSONStringWithDateFormat(page, "yyyy/MM/dd");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(str);
        out.flush();
        // request.setAttribute("page", page);
        // request.getRequestDispatcher("/xxx.jsp").forward(request, response);
    }

    ///////////user///////////
    ProfessorService professorService = new ProfessorService();
    UserService userService = new UserService();
    RoleService roleService = new RoleService();
    ProjectService projectService = new ProjectService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
        // response.setContentType("text/html");
        // PrintWriter out = response.getWriter();
        // out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01
        // Transitional//EN\">");
        // out.println("<HTML>");
        // out.println(" <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        // out.println(" <BODY>");
        // out.print(" This is ");
        // out.print(this.getClass());
        // out.println(", using the POST method");
        // out.println(" </BODY>");
        // out.println("</HTML>");
        // out.flush();
        // out.close();
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here
    }

}

//经纬度描述类
class Jw {
    public double lat, lng;

    public Jw() {
    }

    public Jw(double lat, double lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    public double dis(Jw w) {
        return getDistance(this.lat, this.lng, w.lat, w.lng);
    }

    @Override
    public String toString() {
        return "Jw [lat=" + lat + ", lng=" + lng + "]";
    }

    public static double getDistance(double Lat_A, double Lng_A, double Lat_B, double Lng_B) {
        double ra = 6378.140;
        double rb = 6356.755;
        double flatten = (ra - rb) / ra;
        double rad_lat_A = Math.toRadians(Lat_A);
        double rad_lng_A = Math.toRadians(Lng_A);
        double rad_lat_B = Math.toRadians(Lat_B);
        double rad_lng_B = Math.toRadians(Lng_B);
        double pA = Math.atan(rb / ra * Math.tan(rad_lat_A));
        double pB = Math.atan(rb / ra * Math.tan(rad_lat_B));
        double xx = Math
                .acos(Math.sin(pA) * Math.sin(pB) + Math.cos(pA) * Math.cos(pB) * Math.cos(rad_lng_A - rad_lng_B));
        double c1 = (Math.sin((xx) - xx) * (Math.sin(pA) + Math.sin(pB)) * 2 / Math.cos(xx / 2) * 2);
        double c2 = (Math.sin(xx) + xx) * (Math.sin(pA) - Math.sin(pB)) * 2 / Math.sin(xx / 2) * 2;
        double dr = flatten / 8 * (c1 - c2);
        double distance = ra * (xx + dr);
        // DecimalFormat format = new DecimalFormat("###.000");
        // distance = Double.parseDouble(format.format(distance));
        return Math.abs(distance);
    }
}