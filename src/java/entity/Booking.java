/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 18-06-2023      1.0                 DucTM           First Implement
 */
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
    private String touristPhone;
    private String touristEmail;
    private int tourLength;
    private String reason;

    public Booking() {
    }

    public Booking(int id, String status, String reason) {
        this.id = id;
        this.status = status;
        this.reason = reason;
    }

    public Booking(int touristId, int tourId, String status) {
        this.touristId = touristId;
        this.tourId = tourId;
        this.status = status;
    }
    
    
    //use this when load booking list of travel agent
    public Booking(int id, int touristId, int tourId, Timestamp bookTime, Date startDate, int touristsQuantity,
            String status, String note, String touristName, String tourName, String touristPhone, String touristEmail,
            int tourLength, String reason) {
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
        this.touristPhone = touristPhone;
        this.touristEmail = touristEmail;
        this.tourLength = tourLength;
        this.reason = reason;
    }
    //use this when load tour history of tourist
    public Booking(int id, int touristId, int tourId, Timestamp bookTime, Date startDate, int touristsQuantity,
            String status, String note, String tourName, String reason) {
        this.id = id;
        this.touristId = touristId;
        this.tourId = tourId;
        this.bookTime = bookTime;
        this.startDate = startDate;
        this.touristsQuantity = touristsQuantity;
        this.status = status;
        this.note = note;
        this.tourName = tourName;
        this.reason = reason;
    }
    //use this when create a new booking entry
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getTourLength() {
        return tourLength;
    }

    public void setTourLength(int tourLength) {
        this.tourLength = tourLength;
    }

    public String getTouristPhone() {
        return touristPhone;
    }

    public void setTouristPhone(String touristPhone) {
        this.touristPhone = touristPhone;
    }

    public String getTouristEmail() {
        return touristEmail;
    }

    public void setTouristEmail(String touristEmail) {
        this.touristEmail = touristEmail;
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
