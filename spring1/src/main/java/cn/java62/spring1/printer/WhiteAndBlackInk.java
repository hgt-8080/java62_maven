package cn.java62.spring1.printer;

public class WhiteAndBlackInk implements Ink {
    @Override
    public String getColor() {
        return "黑白色";
    }
}
