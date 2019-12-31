package cn.java62.spring2.printer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class Printer {
    @Value("天威打印机z1000")
    private String name;
    @Value("1200")
    private int price;

    @Resource
    private Ink ink;

    //@Autowired
    //@Qualifier("b5")

    @Resource(name="a4")
    private Paper paper;

    public void show(){
        System.out.println("这个打印机是"+name+",价格是："+price+"元");
    }

    public void print(String str){
        System.out.println("用"+ink.getColor()+"打印：");
        paper.print(str);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setInk(Ink ink) {
        this.ink = ink;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }
}
