package cn.bx.bid.ctrl;

import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoleInterceptor extends HandlerInterceptorAdapter {
    //private long time;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getSession().getAttribute("USER_LOGIN")==null){
            request.setAttribute("msg","您没有权限访问此地址！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("执行结束："+handler);
        if(modelAndView!=null)
            System.out.println(",返回值："+modelAndView.getViewName()+",返回值："+modelAndView.getModelMap());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
