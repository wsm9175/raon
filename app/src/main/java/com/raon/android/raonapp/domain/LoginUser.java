package com.raon.android.raonapp.domain;

public class LoginUser {
    private static LoginUser instance;
    private String id;
    private String email;

    private LoginUser() {}

    public static LoginUser getInstance(){
        if(instance == null) instance = new LoginUser();
        return instance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void clear(){
        instance = new LoginUser();
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
