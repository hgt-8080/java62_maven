package cn.java62.spring2.printer;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service("ink")
//@Repository
//@Component
//@Controller
public class ColorInk implements Ink {
    @Override
    public String getColor() {
        return "真彩色";
    }
}
