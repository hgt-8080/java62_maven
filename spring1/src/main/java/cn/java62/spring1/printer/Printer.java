package cn.java62.spring1.printer;

public class Printer {
    private String name;
    private int price;

    private Ink ink;
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
