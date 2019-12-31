package cn.java62.spring2.printer;

import org.springframework.stereotype.Service;

@Service("blackink")
public class WhiteAndBlackInk implements Ink {
    @Override
    public String getColor() {
        return "黑白色";
    }
}
