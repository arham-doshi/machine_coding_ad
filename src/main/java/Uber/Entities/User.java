package Uber.Entities;

public class User {
    String userid;
    String name;
    String email;
    String number;

    public User(String userid, String name, String email, String number) {
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
