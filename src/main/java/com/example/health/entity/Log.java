package com.example.health.entity;

public class Log {
    private int id;
    private String user;
    private String password;
    private String reason;
    private String time;

    public Log() {
    }

    public Log( String user, String password, String reason, String time) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.reason = reason;
        this.time = time;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", reason='" + reason + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
