package controllers;

import DateBase.DBHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import sun.tools.tree.ShiftRightExpression;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class EnterController {

    /**
     * searches in database.if exists-checks with saved password
     *
     * @param email
     * @param password
     * @return true-if all correct.false-password doesn`t match. or "Unregistered email"
     */
    @RequestMapping(value = "/enter", method = POST)
    public String enter(@RequestParam(value = "email", required = false, defaultValue = "Not found") String email,
                        @RequestParam(value = "password", required = false, defaultValue = "Not found") String password,
                        HttpServletResponse response) {
        DBHandler db;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String name = "Not", surname = "found:(";
        String status,page = "[uuue";
        String booki = "";
        String userId = "";
        try {
            db = new DBHandler();
            Statement statement = db.getConnection().createStatement();
            String getQuery = "select * from users where email = '" + email + "'";
            Boolean passwordValidation;
            ResultSet resultSet = statement.executeQuery(getQuery);
            //-----catch mistakes
            if (!resultSet.next()) return "false";
            //get parameters
            name = resultSet.getString("name");
            surname = resultSet.getString("surname");
            status = resultSet.getString("status");
            userId = resultSet.getString("id");
            String encodedPassword = resultSet.getString("password");
            passwordValidation = encoder.matches(password, encodedPassword);
            System.out.println(passwordValidation);
            if (!passwordValidation) return "false";
            //create cookie----
            Random random = new Random();
            String cookieId = (random.nextInt(700000) + 100000) + "" + resultSet.getInt(1) + "" + (random.nextInt(700000) + 100000);
            String query = "update users set cookieId = '"
                    + cookieId + "' where email = '" + email + "';";
            statement.execute(query);
            Cookie cookie = new Cookie("user_code", cookieId);
//            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            System.out.println("Object: " + cookie + "; Name: " + cookie.getName() + "; Value: " + cookie.getValue());
            //-------create page
            Statement historyStatement = db.getConnection().createStatement();
            System.out.println(userId);
            String historyQuery = "select * from orders where userId = '" + userId + "'";
            ResultSet ordersResultSet = statement.executeQuery(historyQuery);
            String title;
            int i = 0;
            int margin = -5;
            while (ordersResultSet.next()) {
                i++;

                booki = booki + "<div class=\"books\" style=\"margin-left:"+ margin +"px\"> " +
                        "<img src=\"/resources/img/books/1.jpg\" width=\"190px\" height=\"289px\" /> " +
                        "<p class=\"bookname\">"+ "3 PIGS"    +"</p> " +
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
        return page;
    }

}
