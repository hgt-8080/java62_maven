package cn.java62.spring4;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
@Aspect
public class UserLogger {
    //前置增强
    @Before("within(cn.java62.spring4..*)")
    public void bofore(JoinPoint jp){
        System.out.println(jp.getTarget()+" ,"+jp.getSignature()+","+jp.getStaticPart());
        System.out.println("执行前，当前时间是："+new Date());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
    }

    //环绕增强
    @Around("within(cn.java62.spring4..*)")
    public Object lanjie(ProceedingJoinPoint jp) throws Throwable {
        long start=System.nanoTime();
        System.out.println("拦截的目标是："+jp.getTarget());

        System.out.println("执行的方法："+jp.getSignature().getName());

        System.out.println("方法的参数："+ Arrays.toString(jp.getArgs()));
        Object o=jp.proceed();//放行
        System.out.println("方法返回值是："+o);
        long end=System.nanoTime();
        System.out.println("执行花费"+(end-start)+"毫微秒");
        return o;
    }

}
