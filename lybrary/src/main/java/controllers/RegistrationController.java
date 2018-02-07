package controllers;



import DateBase.DBHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class RegistrationController {
    /**
     * checks if there is already an account in database
     * @param name
     * @param surname
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "/registration", method = POST)
    public String registration(@RequestParam(value = "name", required = false, defaultValue = "Not found") String name,
                               @RequestParam(value = "surname", required = false, defaultValue = "Not found") String surname,
                               @RequestParam(value = "email", required = false, defaultValue = "Not found") String email,
                               @RequestParam(value = "password", required = false, defaultValue = "Not found") String password) {
        DBHandler db;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String status = "",page = "[uuue";
        String booki = "";
        String userId = "";
        try {
            db = new DBHandler();
            Statement statement = db.getConnection().createStatement();
            String getQuery = "select * from users where email = '"+ email + "'";
            ResultSet resultSet = statement.executeQuery(getQuery);
            if (resultSet.next()) return "false";
            String encodedPassword = encoder.encode(password);
            String query = "insert into users (email,password,name,surname) values('"
                    + email + "\', \'"
                    + encodedPassword + "\', \'"
                    + name + "\', \'"
                    + surname + "\')";
            statement.execute(query);
            resultSet = statement.executeQuery(getQuery);
            resultSet.next();
            userId = resultSet.getString("id");

            //-------create page
            status = resultSet.getString("status");
            Statement historyStatement = db.getConnection().createStatement();
            System.out.println(userId);
            String historyQuery = "select * from orders where userId = '" + userId + "'";
            ResultSet ordersResultSet = statement.executeQuery(historyQuery);
            String title,time;
            long keepingTime;
            int i = 0;
            int margin = -5;
            while (ordersResultSet.next()) {
                i++;
                keepingTime = ordersResultSet.getLong("finishTime");
                booki = booki + "<div class=\"books\" style=\"margin-left:"+ margin +"px\"> " +
                        "<img src=\"/resources/img/books/1.jpg\" width=\"190px\" height=\"289px\" /> " +
                        "<p class=\"bookname\">"+ "3 PIGS ->" + getDate(keepingTime)   +"</p> " +
                        "</div>";
                margin += 198;
                if (i % 4 == 0) margin = -5;

            }

            page = "<div id=\"usercard\">" +
                    "<div id=\"usercard_avatar\" class=\"blocks\"></div>" +
                    "<div class=\"blocks\" id=\"usercard_info\">" +
                    "<p id=\"name\">"+ name +" " +surname +"</p> " +
                    "<p id=\"settings_bottom\">Settings</p> " +
                    "<p class=\"usercard_info_text1\" style=\"margin-top:-8px\">Status:</p> " +
                    "<p class=\"usercard_info_text1\" style=\"margin-top:22px\">fine:</p> " +
                    "<p class=\"usercard_info_text1\" style=\"margin-top:52px\">Chlen:</p> " +
                    "<p class=\"usercard_info_text2\" style=\"margin-top:-8px\">"+ status +"</p> " +
                    "<p class=\"usercard_info_text2\" style=\"margin-top:22px\">228$</p> " +
                    "<p class=\"usercard_info_text2\" style=\"margin-top:52px\">Bolshoi</p> " +
                    "</div> " +
                    "<div class=\"blocks\" id=\"history\"> " +
                    "<div class=\"line\"> " +
                    booki +
                    "</div> " +
                    "</div> " +
                    "</div>";




        } catch (SQLException e) {
            e.printStackTrace();
        }
//        model.addAttribute("name", name);
//        model.addAttribute("surname", surname);
        return page;
        }


    public static void main(String[] args){
        DBHandler db;
        try {
            db = new DBHandler();
            Statement statement = db.getConnection().createStatement();
            String getQuery = "select * from users where id = 3;";
            ResultSet resultSet = statement.executeQuery(getQuery);
            System.out.println(!resultSet.next());
//            if (!resultSet.next()) System.out.println(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static String getDate(long currentTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTime);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(cal.getTime());
    }
}
