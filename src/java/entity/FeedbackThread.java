
package entity;

import java.sql.Timestamp;

public class FeedbackThread {
    private int id;
    private int rating;
    private Timestamp time;
    private String content;
    private int touristID;
    private int tourID;

    public FeedbackThread() {
    }

    public FeedbackThread(int rating, Timestamp time, String content, int touristID, int tourID) {
        this.rating = rating;
        this.time = time;
        this.content = content;
        this.touristID = touristID;
        this.tourID = tourID;
    }

    public FeedbackThread(int id, int rating, Timestamp time, String content, int touristID, int tourID) {
        this.id = id;
        this.rating = rating;
        this.time = time;
        this.content = content;
        this.touristID = touristID;
        this.tourID = tourID;
    }

    public int getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public Timestamp getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public int getTouristID() {
        return touristID;
    }

    public int getTourID() {
        return tourID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTouristID(int touristID) {
        this.touristID = touristID;
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

    @Override
    public String toString() {
        return "FeedbackThread{" + "id=" + id + ", rating=" + rating + ", time=" + time + ", content=" + content + ", touristID=" + touristID + ", tourID=" + tourID + '}';
    }
    
    

}
