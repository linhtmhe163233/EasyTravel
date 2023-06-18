
import dao.impl.HotelDAOImpl;
import entity.Hotel;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ngan Ha
 */
public class Test {
    public static void main(String[] args) throws Exception {
        HotelDAOImpl dao = new HotelDAOImpl();
        List<Hotel> hotel = dao.get(10);
        System.out.println(hotel);
    }
}
