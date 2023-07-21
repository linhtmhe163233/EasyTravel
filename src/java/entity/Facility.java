
package entity;

public class Facility {
    private int bookingId;
    private int vehicleId;
    private int staffId;
    private int hotelId;
    private int restaurantId;
    private String vehicleInfo;
    private String staffInfo;
    private String hotelInfo;
    private String restaurantInfo;

    public Facility(int bookingId, int vehicleId, int staffId, int hotelId, int restaurantId) {
        this.bookingId = bookingId;
        this.vehicleId = vehicleId;
        this.staffId = staffId;
        this.hotelId = hotelId;
        this.restaurantId = restaurantId;
    }

    public Facility(int bookingId, String vehicleInfo, String staffInfo, String hotelInfo, String restaurantInfo) {
        this.bookingId = bookingId;
        this.vehicleInfo = vehicleInfo;
        this.staffInfo = staffInfo;
        this.hotelInfo = hotelInfo;
        this.restaurantInfo = restaurantInfo;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(String vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public String getStaffInfo() {
        return staffInfo;
    }

    public void setStaffInfo(String staffInfo) {
        this.staffInfo = staffInfo;
    }

    public String getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(String hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public String getRestaurantInfo() {
        return restaurantInfo;
    }

    public void setRestaurantInfo(String restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }
    
    
}
