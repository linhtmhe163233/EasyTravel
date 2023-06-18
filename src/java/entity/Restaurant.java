package entity;

public class Restaurant {

    private int id;
    private String type;
    private int tableAvailable;
    private String phone;
    private int agentId;

    public Restaurant() {
    }

    public Restaurant(int id, String type, int tableAvailable, String phone, int agentId) {
        this.id = id;
        this.type = type;
        this.tableAvailable = tableAvailable;
        this.phone = phone;
        this.agentId = agentId;
    }

    public Restaurant(String type, int tableAvailable, String phone, int agentId) {
        this.type = type;
        this.tableAvailable = tableAvailable;
        this.phone = phone;
        this.agentId = agentId;
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

    public int getTableAvailable() {
        return tableAvailable;
    }

    public void setTableAvailable(int tableAvailable) {
        this.tableAvailable = tableAvailable;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

}
