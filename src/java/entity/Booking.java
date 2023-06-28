package entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Booking {

    private int id;
    private int touristId;
    private int tourId;
    private Timestamp bookTime;
    private Date startDate;
    private int touristsQuantity;
    private String status;
    private String note;
    private String touristName;
    private String tourName;

    public Booking() {
    }

    public Booking(int id, int touristId, int tourId, Timestamp bookTime, Date startDate, int touristsQuantity, 
            String status, String note, String touristName, String tourName) {
        this.id = id;
        this.touristId = touristId;
        this.tourId = tourId;
        this.bookTime = bookTime;
        this.startDate = startDate;
        this.touristsQuantity = touristsQuantity;
        this.status = status;
        this.note = note;
        this.touristName = touristName;
        this.tourName = tourName;
    }

    public Booking(int id, int touristId, int tourId, Timestamp bookTime, Date startDate, int touristsQuantity, 
            String status, String note, String tourName) {
        this.id = id;
        this.touristId = touristId;
        this.tourId = tourId;
        this.bookTime = bookTime;
        this.startDate = startDate;
        this.touristsQuantity = touristsQuantity;
        this.status = status;
        this.note = note;
        this.tourName = tourName;
    }
    
    
    public Booking(int id, int touristId, int tourId, Timestamp bookTime, Date startDate, int touristsQuantity,
            String status, String note) {
        this.id = id;
        this.touristId = touristId;
        this.tourId = tourId;
        this.bookTime = bookTime;
        this.startDate = startDate;
        this.touristsQuantity = touristsQuantity;
        this.status = status;
        this.note = note;
    }

    public Booking(int touristId, int tourId, Timestamp bookTime, Date startDate, int touristsQuantity,
            String status, String note) {
        this.touristId = touristId;
        this.tourId = tourId;
        this.bookTime = bookTime;
        this.startDate = startDate;
        this.touristsQuantity = touristsQuantity;
        this.status = status;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public int getTouristId() {
        return touristId;
    }

    public int getTourId() {
        return tourId;
    }

    public Timestamp getBookTime() {
        return bookTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getTouristsQuantity() {
        return touristsQuantity;
    }

    public String getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTouristId(int touristId) {
        this.touristId = touristId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public void setBookTime(Timestamp bookTime) {
        this.bookTime = bookTime;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setTouristsQuantity(int touristsQuantity) {
        this.touristsQuantity = touristsQuantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTouristName() {
        return touristName;
    }

    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }
    
}
