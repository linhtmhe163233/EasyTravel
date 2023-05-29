package models;

import java.sql.Date;

public class User {

    private int id;
    private String userame;
    private String password;
    private String fullname;
    private String email;
    private String phonenumber;
    private int role;
    private Date dob;
    private String status;

    public User() {
    }

    public User(int id, String userame, String password, String fullname, String email, String phonenumber, int role, Date dob, String status) {
        this.id = id;
        this.userame = userame;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.role = role;
        this.dob = dob;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getUserame() {
        return userame;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public int getRole() {
        return role;
    }

    public Date getDob() {
        return dob;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserame(String userame) {
        this.userame = userame;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
