package cn.bx.bid.ctrl;

import cn.bx.bid.entity.Page;
import cn.bx.bid.entity.User;
import cn.bx.bid.service.UserService;
import cn.bx.bid.util.StringUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("useradd")
    public String reg(@ModelAttribute("u") User user, HttpServletRequest req) {
        // ServletContext servletContext = s.getServletContext();
        // servletContext.getRealPath("")
//        CharacterEncodingFilter f=null;
//        f.setEncoding("utf-8");
//        f.setForceEncoding(true);
        //ContextLoaderListener x=null;
        int r = userService.add(user);
        req.setAttribute("msg", r > 0 ? "注册成功" : "注册失败");
        // System.out.println("新用户：" + user + "被保存了");
        //return "regsuccess.jsp";
        //  return "/WEB-INF/pages/regsuccess.jsp";
        return "regsuccess";
    }
    @GetMapping("/userSearch")
    public ModelAndView search(@RequestParam(name = "page", defaultValue = "1") int pageNo, @RequestParam(name = "limit", defaultValue = "10") int limit, @RequestParam(name = "name", required = false) String name, @RequestParam(name = "start", required = false)Date start, @RequestParam(name = "end", required = false) Date end) {

        Page<User> page = userService.paging(pageNo, limit, name, start, end);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("admin-list");
        mv.addObject("p",page);

        mv.addObject("name", name);
        mv.addObject("start", start);
        mv.addObject("end", end);

        return mv;
    }
    //model也可以带数据到视图
    @GetMapping("user-update-{id}")
    public String update(@PathVariable("id") long id, Model m){
        User user=this.userService.get(id);
        m.addAttribute("user",user);
        return "userupdate";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        // true:允许输入空值，false:不能为空值 }
    }

    //局部，只能处理UserController中的异常
    @ExceptionHandler
    public String handlerException(Exception e){
        e.printStackTrace();//Log4j
        return "error";
    }
}