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

        } catch (SQLException e) {
            e.printStackTrace();
        }
//        model.addAttribute("name", name);
//        model.addAttribute("surname", surname);
        System.out.println(name + "%20" + surname);
        return name + "%20" + surname;
    }

}
