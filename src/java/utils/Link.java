package utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Link {

    public static Set<String> all = new HashSet<>(Arrays.asList("home", "tour"));
    public static Set<String> notLoggedIn = new HashSet<>(Arrays.asList("login", "register", "forgotpassword", 
            "checkcode", "newpassword"));
    public static Set<String> forgotPassword = new HashSet<>(Arrays.asList("checkcode", "newpassword"));
    public static Set<String> user = new HashSet<>(Arrays.asList("profile", "changepassword", "logout"));
    public static Set<String> tourist = new HashSet<>(Arrays.asList("history", "book", "handlebooking", "feedback"));
    public static Set<String> agent = new HashSet<>(Arrays.asList("tours", "hotels", "vehicles", "RestaurantList",
             "staff", "bookinglist", "CreateRestaurant", "DeleteRestau", "EditRestau", "edit", "delete", "handlebooking"));
    public static Set<String> admin = new HashSet<>(Arrays.asList("usermanage"));
}
