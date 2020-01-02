package cn.java62.spring3;

public class User {
    private int id;
    private String username,pass,tele;

    public User() {
    }

    public User(int id, String username, String pass, String tele) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.tele = tele;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", tele='" + tele + '\'' +
                '}';
    }
}
