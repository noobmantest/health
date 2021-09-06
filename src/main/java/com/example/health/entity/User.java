package com.example.health.entity;

public class User {
    private int id;
    private String user;
    private String password;
    private int days;
    private String today;
    private String email;
    private String city_code;
    private String address;
    private int open = 1;
    private int inviteNums = 0;
    private int addDays = 0;

    public User() {
    }

    public User(String user, String password, int days, String today, String email, String city_code, String address) {
        this.user = user;
        this.password = password;
        this.days = days;
        this.today = today;
        this.email = email;
        this.city_code = city_code;
        this.address = address;
    }

    public User(int id, String user, String password, int days, String today, String email, String city_code, String address, int open, int inviteNums, int addDays) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.days = days;
        this.today = today;
        this.email = email;
        this.city_code = city_code;
        this.address = address;
        this.open = open;
        this.inviteNums = inviteNums;
        this.addDays = addDays;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public int getInviteNums() {
        return inviteNums;
    }

    public void setInviteNums(int inviteNums) {
        this.inviteNums = inviteNums;
    }

    public int getAddDays() {
        return addDays;
    }

    public void setAddDays(int addDays) {
        this.addDays = addDays;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", days=" + days +
                ", today='" + today + '\'' +
                ", email='" + email + '\'' +
                ", city_code='" + city_code + '\'' +
                ", address='" + address + '\'' +
                ", open=" + open +
                ", inviteNums=" + inviteNums +
                ", addDays=" + addDays +
                '}';
    }
}
