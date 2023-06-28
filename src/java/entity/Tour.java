/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 26-05-2023      1.0                 DucTM           First Implement
 */
package entity;

import java.sql.Date;

/*
 * This class represents tour entity in the database
 * 
 * @author DucTM
 */
public class Tour {

    private int id;
    private String name;
    private String type;
    private boolean isEnabled;
    private String destination;
    private int tripLength;
    private Date availableFrom;
    private Date availableTo;
    private int maxQuantity;
    private float price;
    private String description;
    private int agentId;
    private String image;

    public Tour(int ID) {
        this.id = ID;
    }
    
    public Tour(int ID, String name, String type, boolean isEnabled, String destination,
            int tripLength, Date availableFrom, Date availableTo, int maxQuantity,
            float price, String description, int agentId, String image) {
        this.id = ID;
        this.name = name;
        this.type = type;
        this.isEnabled = isEnabled;
        this.destination = destination;
        this.tripLength = tripLength;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.maxQuantity = maxQuantity;
        this.price = price;
        this.description = description;
        this.agentId = agentId;
        this.image = image;
    }

    public Tour(String name, String type, boolean isEnabled, String destination,
            int tripLength, Date availableFrom, Date availableTo, int maxQuantity,
            float price, String description, int agentId, String image) {
        this.name = name;
        this.type = type;
        this.isEnabled = isEnabled;
        this.destination = destination;
        this.tripLength = tripLength;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.maxQuantity = maxQuantity;
        this.price = price;
        this.description = description;
        this.agentId = agentId;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public String getDestination() {
        return destination;
    }

    public int getTripLength() {
        return tripLength;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public Date getAvailableTo() {
        return availableTo;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getAgentId() {
        return agentId;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setTripLength(int tripLength) {
        this.tripLength = tripLength;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }

    public void setAvailableTo(Date availableTo) {
        this.availableTo = availableTo;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public void setImage(String image) {
        this.image = image;
    }
    

}
