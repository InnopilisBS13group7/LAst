package controllers;

import DateBase.DBHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class EntireController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestParam(value = "name", required = false, defaultValue = "ITP_Project") String name,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        @CookieValue(value = "user_code", required = false) Cookie cookieUserCode,
                        Model model) throws SQLException {
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String getQuery;
        if (cookieUserCode != null) {
            getQuery = "select * from users where cookieId = '" + cookieUserCode.getValue() + "'";
            ResultSet resultSet = statement.executeQuery(getQuery);
//            if (resultSet.next()) return "index";
        }
//        model.addAttribute("name", name);
        return "index";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "ITP_Project") String name, Model model) {
        model.addAttribute("name", name);
        return "welcome";
    }
}
