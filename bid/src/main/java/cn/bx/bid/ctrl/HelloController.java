package cn.bx.bid.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
//@RequestMapping("/admin/user")
public class HelloController {

    @RequestMapping("/sayHi")
    public String sayHi(HttpServletRequest req){
        req.setAttribute("msg","你好，spring mvc,现在时间是："+new Date());
        //return "hello.jsp";
        return "forward:hello.jsp";
    }

    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public String login(@RequestParam(name="username",required = true) String name,@RequestParam("pass") String pass,HttpServletRequest req){
        if(name.startsWith("java62")&&pass.equals("123456")){
            req.getSession().setAttribute("USER_LOGIN",name);
            return "redirect:welcome.jsp";
        }
        req.setAttribute("msg","用户名或者密码错误");
        return "fail.jsp";
    }
}
