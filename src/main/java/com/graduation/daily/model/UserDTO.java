package com.graduation.daily.model;

public class UserDTO {
    private String id;
    private String pass1;
    private String pass2;

    private String email;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPass1() {
        return pass1;
    }
    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }
    public String getPass2() {
        return pass2;
    }
    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }


}
