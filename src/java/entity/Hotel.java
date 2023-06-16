package entity;


public class Hotel {

    private int id;
    private String name;
    private int stars;
    private int room_available;
    private String phone;
    private int agent_id;
    private String location;
    

    public Hotel() {
        
    }

    public Hotel(int id, String name, int stars, int room_available, String phone, int agent_id, String location) {
        this.id = id;
        this.name = name;
        this.stars = stars;
        this.room_available = room_available;
        this.phone = phone;
        this.agent_id = agent_id;
        this.location = location;
    }


    

    public Hotel(String name, int stars, int room_available, String phone, int agent_id, String location) {
        this.name = name;
        this.stars = stars;
        this.room_available = room_available;
        this.phone = phone;
        this.agent_id = agent_id;
        this.location = location;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getRoom_available() {
        return room_available;
    }

    public void setRoom_available(int room_available) {
        this.room_available = room_available;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
        

   
    

    
}
