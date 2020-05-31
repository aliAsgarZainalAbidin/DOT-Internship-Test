package com.example.dotinternshiptest.data.remote.models;

public class Profile {
    private int id;
    private String username;
    private String fullname;
    private String email;
    private String phone;
    private String avatar;

    public Profile(int id, String username, String fullname, String email, String phone, String avatar) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
