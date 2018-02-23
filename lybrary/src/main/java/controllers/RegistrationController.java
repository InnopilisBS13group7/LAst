package controllers;



import DateBase.DBHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class RegistrationController extends controllers.Controller {
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
                               @RequestParam(value = "password", required = false, defaultValue = "Not found") String password,
                               HttpServletResponse response) {
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
            String query = "insert into users (email,password,name,surname,status) values('"
                    + email + "\', \'"
                    + encodedPassword + "\', \'"
                    + name + "\', \'"
                    + surname + "\', \'"
                    + "disabled" + "\')";
            statement.execute(query);
            resultSet = statement.executeQuery(getQuery);
            resultSet.next();
            userId = resultSet.getString("id");
            //-------create Cookie

            createNewCookieForUser(email, response);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        //------create page
        return createUserCardPage(userId);
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


}
