package cn.java62.spring1;

import cn.java62.spring1.printer.Printer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test3 {
    public static void main(String[] args) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
        //Printer p=new Printer();
        Printer p=(Printer)ac.getBean("printer");
        p.show();
        p.print("《三国演义》（Three Kingdoms Era）全名为《三国志通俗演义》（又称《三国志演义》），是元末明初的小说家罗贯中创作的长篇章回体历史演义小说。该作品成书后有嘉靖壬午本等多个版本传于世，到了明末清初，毛宗岗对《三国演义》整顿回目、修正文辞、改换诗文\n" +
                "《三国演义》描写了从东汉末年到西晋初年之间近百年的历史风云，以描写战争为主，诉说了东汉末年的群雄割据混战和魏、蜀、吴三国之间的政治和军事斗争，最终司马炎一统三国，建立晋朝的故事。反映了三国时代各类社会斗争与矛盾的转化，并概括了这一时代的历史巨变，塑造了一群叱咤风云的三国英雄人物。\n" +
                "全书可大致分为黄巾起义、董卓之乱、群雄逐鹿、三国鼎立、三国归晋五大部分。在广阔的历史舞台上，上演了一幕幕气势磅礴的战争场面。作者罗贯中将兵法三十六计融于字里行间，既有情节，也有兵法韬略。\n" +
                "《三国演义》是中国文学史上第一部章回小说，是历史演义小说的开山之作，也是第一部文人长篇小说。中国古典四大名著之一。");
    }
}
