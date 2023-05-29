package models;

import java.sql.Date;

public class User {

    private String userame;
    private String password;
    private String fullname;
    private String email;
    private String phonenumber;
    private int role;
    private Date dob;

    public User() {
    }

    public User(String userame, String password, String fullname, String email, String phonenumber, int role, Date dob) {
        this.userame = userame;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.role = role;
        this.dob = dob;
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

    @Override
    public String toString() {
        return "User{" + "userame=" + userame + ", password=" + password + ", fullname=" + fullname + ", email=" + email + ", phonenumber=" + phonenumber + ", role=" + role + ", dob=" + dob + '}';
    }

}
