/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * ??-??-2023      1.0                 DungMQ           First Implement
 * 03-06-2023      1.0                 DucTM            Change variables name
 */
package entity;

public class Vehicle {
    private int id;
    private String type;
    private String driverName;
    private String driverPhone;
    private int maxPassenger;
    private int agentID;

    public Vehicle() {
    }

    public Vehicle(int ID, String type, String driverName, String driverPhone, int maxPassenger, int agentID) {
        this.id = ID;
        this.type = type;
        this.driverName = driverName;
        this.driverPhone = driverPhone;
        this.maxPassenger = maxPassenger;
        this.agentID = agentID;
    }

    public Vehicle(String type, String driverName, String driverPhone, int maxPassenger, int agentID) {
        this.type = type;
        this.driverName = driverName;
        this.driverPhone = driverPhone;
        this.maxPassenger = maxPassenger;
        this.agentID = agentID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public int getMaxPassenger() {
        return maxPassenger;
    }

    public void setMaxPassenger(int maxPassenger) {
        this.maxPassenger = maxPassenger;
    }

    public int getAgentID() {
        return agentID;
    }

    public void setAgentID(int agentID) {
        this.agentID = agentID;
    }
    
}
