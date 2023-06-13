package entity;

import java.sql.Date;

public class User {

    private int id;
    private String username;
    private String password;
    private String fullname;
    private Date dob;
    private String email;
    private String phone;
    private String role;
    private String status;
    private String key;
    public User() {
    }

    public User(int id, String username, String password, String fullname, Date dob, 
            String email, String phone, String role, String status, String key) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.status = status;
        this.key=key;
    }

    public User(String username, String password, String fullname, Date dob, 
            String email, String phone, String role, String status, String key) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.status = status;
        this.key=key;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
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

    public String getPhone() {
        return phone;
    }

    public String getRole() {
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

    public void setUsername(String username) {
        this.username = username;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
    
   
}
