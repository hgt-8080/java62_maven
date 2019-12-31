package cn.java62.spring1;

public class Person {
    private String person;
    private String words;
    public Person(String p,String w){
        this.person=p;
        this.words=w;
    }
    public void setPerson(String p){
        this.person=p;
    }
    public void setWords(String s){
        this.words=s;
    }
    public void say(){
        System.out.println(person+"说:"+words);
    }
}
