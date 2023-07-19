/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author macqu
 */
public class CustomRestaurant {
    
    private int id;
    private String type;
    private int table_available;
    private String phone;
    private int agent_id;
    private String agentName;

    public CustomRestaurant(int id, String type, int table_available, String phone, int agent_id) {
        this.id = id;
        this.type = type;
        this.table_available = table_available;
        this.phone = phone;
        this.agent_id = agent_id;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
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

    public int getTable_available() {
        return table_available;
    }

    public void setTable_available(int table_available) {
        this.table_available = table_available;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(int agent_id) {
        this.agent_id = agent_id;
    }
}
