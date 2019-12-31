package cn.java62.spring2.printer;

public class TextPaper implements Paper {
    private int linewords;//一行写几个字
    private int pageLines;//一页几行

    public void setLinewords(int linewords) {
        this.linewords = linewords;
    }

    public void setPageLines(int pageLines) {
        this.pageLines = pageLines;
    }

    @Override
    public void print(String content) {
        int words=0;
        int lines=0;
        int page=1;
        char []cr=content.toCharArray();
        //模拟打印，以及换纸
        for(int i=0;i<cr.length;i++){
            System.out.print(cr[i]);
            words++;
            if(words>=linewords){
                //一行到头
                words=0;
                lines++;
                System.out.println();
                if(lines>=pageLines){
                    //一页写完
                    lines=0;
                    System.out.println("------"+page+++"----");
                }
            }
        }
        if(lines!=0||words!=0)
            System.out.println("\n------"+page+++"----");
    }
}
