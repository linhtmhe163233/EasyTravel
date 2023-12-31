/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 31-05-2023      1.0                 DucTM           First Implement
 */
package entity;

import java.sql.Date;

/*
 * This class represents staff entity in the database
 * 
 * @author DucTM
 */
public class Staff {
    private int id;
    private String name;
    private Date DOB;
    private String phone;
    private boolean gender;
    private int agentID;

    public Staff(int ID, String name, Date DOB, String phone, boolean gender, int agentID) {
        this.id = ID;
        this.name = name;
        this.DOB = DOB;
        this.phone = phone;
        this.gender = gender;
        this.agentID = agentID;
    }

    public Staff(String name, Date DOB, String phone, boolean gender, int agentID) {
        this.name = name;
        this.DOB = DOB;
        this.phone = phone;
        this.gender = gender;
        this.agentID = agentID;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getAgentID() {
        return agentID;
    }

    public void setAgentID(int agentID) {
        this.agentID = agentID;
    }
    
}
