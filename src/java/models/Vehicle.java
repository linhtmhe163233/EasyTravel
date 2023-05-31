
package models;

public class Vehicle {
    private int id;
    private String type;
    private String driver_name;
    private String driver_phone;
    private int max_passenger;
    private int agent_id;

    public Vehicle() {
    }

    public Vehicle(int id, String type, String driver_name, String driver_phone, int max_passenger, int agent_id) {
        this.id = id;
        this.type = type;
        this.driver_name = driver_name;
        this.driver_phone = driver_phone;
        this.max_passenger = max_passenger;
        this.agent_id = agent_id;
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

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getDriver_phone() {
        return driver_phone;
    }

    public void setDriver_phone(String driver_phone) {
        this.driver_phone = driver_phone;
    }

    public int getMax_passenger() {
        return max_passenger;
    }

    public void setMax_passenger(int max_passenger) {
        this.max_passenger = max_passenger;
    }

    public int getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(int agent_id) {
        this.agent_id = agent_id;
    }
    
}
