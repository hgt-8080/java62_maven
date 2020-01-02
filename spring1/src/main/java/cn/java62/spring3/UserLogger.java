package cn.java62.spring3;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;
import java.util.Date;

public class UserLogger {
    //环绕增强
    public Object lanjie(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("拦截的目标是："+jp.getTarget());
        System.out.println("执行前："+new Date());
        System.out.println("执行的方法："+jp.getSignature().getName());
        if(jp.getSignature().getName().equals("add")){
            User user= (User) jp.getArgs()[0];
            if(user.getUsername()==null||user.getUsername().length()<2){
               // return 0;//
                throw new Exception("名字太短");
            }
        }
        System.out.println("方法的参数："+ Arrays.toString(jp.getArgs()));
        Object o=jp.proceed();//放行
        System.out.println("方法返回值是："+o);
        return o;
    }
}
