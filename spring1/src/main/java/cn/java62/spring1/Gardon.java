package cn.java62.spring1;
import java.util.*;
public class Gardon {
    private List<String> flowers=new ArrayList<>();
    private Map<String,Object> emps=new HashMap<>();

    private Properties intro=new Properties();

    public void intro(){
        System.out.println("本花园培育的品种："+flowers);
        System.out.println("有职工"+emps.size()+"人，"+emps);
        System.out.println("其他信息："+intro);
    }

    public void setFlowers(List<String> flowers) {
        this.flowers = flowers;
    }

    public void setEmps(Map<String, Object> emps) {
        this.emps = emps;
    }

    public void setIntro(Properties intro) {
        this.intro = intro;
    }
}
