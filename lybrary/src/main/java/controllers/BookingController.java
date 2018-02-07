package controllers;


import DateBase.DBHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class BookingController {

    @RequestMapping(value = "/takeItem", method = POST)
    public String takeItem(@RequestParam(value = "documentId", required = true, defaultValue = "Not found") String documentId,
                           @CookieValue(value = "user_code", required = false) Cookie cookieUserCode,
                           Model model){

        Date date = new Date();
        DBHandler db;
        try{
            db = new DBHandler();
            Statement statement = db.getConnection().createStatement();
            String getQuery = "select * from documents where id = '"+ documentId + "'";
            ResultSet resultSet = statement.executeQuery(getQuery);
            if (!resultSet.next()) return "false";
            int currentAmount = resultSet.getInt("amount");
            if (currentAmount == 0) return "false";
            //-- some conditions
            long keepingTime = 0;
            String userStatus = resultSet.getString("status");
            if (userStatus.equals("disabled") || userStatus.equals("activated")) keepingTime = 1728000000;
            else keepingTime = 2*1728000000L;
            System.out.println(keepingTime);
            //--

            String queryForDocument = "update documents set amount = " + (currentAmount-1) + "where id = " + documentId;
            String queryForOrder = "insert into orders (userId, itemId, startTime, finishTime) values (" +
                                    getIdFromCookie(cookieUserCode.getValue()) + ", " +
                                    documentId + ", " +
                                    date.getTime() + ", " +
                                    keepingTime;

            statement.execute(queryForDocument);
            statement.execute(queryForOrder);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return "true";
    }

    @RequestMapping(value = "/listItems", method = POST)
    public String listItems(){
        DBHandler db;
        String divPattern = "<div class=books_box> <img src=1.jpg class=cover width=66px height=100px /> " +
                "<p class=bookname>Три поросёнка</p>" +
                " <p class=type>Порно, сказки, животные, зоофилия, хоррор</p>" +
                " <p class=description>Трое чёрных поросят зря сказали серому волку, что мамы нет дома...</p> " +
                "<div class=bookit>Book</div> <p class=number>В наличии 3</p> " +
                "</div>";
        String divList = "";
        try {
            db = new DBHandler();
            Statement statement = db.getConnection().createStatement();
            String getQuery = "select * from documents";
            ResultSet resultSet = statement.executeQuery(getQuery);
            String title, author, description,image,teg,type;
            int amount;
            while(resultSet.next()){
                title = resultSet.getString("title");
                author = resultSet.getString("author");
                description = resultSet.getString("description");
                amount = resultSet.getInt("amount");
                teg = resultSet.getString("teg");
                type = resultSet.getString("type");
                divList = divList + "<div class=books_box> <img src=1.jpg class=cover width=66px height=100px /> " +
                        "<p class=bookname>" +
                        title +
                        "</p>" +
                        " <p class=type>" +
                        teg +
                        "</p>" +
                        " <p class=description>" +
                        description +
                        "</p> " +
                        "<div class=bookit>" +
                        type +
                        "</div> <p class=number>В наличии " +
                        amount +
                        "</p> " +
                        "</div>";
            }
        } catch (SQLException e){
            e.printStackTrace();
        }


        return divList;
    }

    public static void main(String[] args){

        DBHandler db;
        try{
            db = new DBHandler();
            Statement statement = db.getConnection().createStatement();
            String getQuery = "select * from orders";
            ResultSet resultSet = statement.executeQuery(getQuery);
            resultSet.next();
            long longg = resultSet.getLong("startTime");
            System.out.println(getDate(longg));
            Date date = new Date();
//            System.out.println(getIdFromCookie("111222111123456"));
             long keepingTime = 1728000000L*2;
            System.out.println(keepingTime);
            System.out.println(getDate(date.getTime()+keepingTime));
        }catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println(listItemsC());
    }

    public static String getDate(){
        Date date = new Date();
        SimpleDateFormat formatForDate = new SimpleDateFormat("dd.MM.yyyy");
        return formatForDate.format(date);
    }
    public static String getDate(long currentTime){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis( currentTime );
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(cal.getTime());
    }
    public static int getIdFromCookie(String cookieUserCode){
        return Integer.parseInt(cookieUserCode.substring(6,cookieUserCode.length()-6));
    }

    public static String listItemsC(){
        DBHandler db;
        String divPattern = "<div class=books_box> <img src=1.jpg class=cover width=66px height=100px /> " +
                "<p class=bookname>Три поросёнка</p>" +
                " <p class=type>Порно, сказки, животные, зоофилия, хоррор</p>" +
                " <p class=description>Трое чёрных поросят зря сказали серому волку, что мамы нет дома...</p> " +
                "<div class=bookit>Book</div> <p class=number>В наличии 3</p> " +
                "</div>";
        String divList = "";
        try {
            db = new DBHandler();
            Statement statement = db.getConnection().createStatement();
            String getQuery = "select * from documents";
            ResultSet resultSet = statement.executeQuery(getQuery);
            String title, author, description,image,teg,type;
            int amount;
            while(resultSet.next()){
                title = resultSet.getString("title");
                author = resultSet.getString("author");
                description = resultSet.getString("description");
                amount = resultSet.getInt("amount");
                teg = resultSet.getString("teg");
                type = resultSet.getString("type");
                divList = divList + "<div class=books_box> <img src=1.jpg class=cover width=66px height=100px /> " +
                        "<p class=bookname>" +
                        title +
                        "</p>" +
                        " <p class=type>" +
                        teg +
                        "</p>" +
                        " <p class=description>" +
                        description +
                        "</p> " +
                        "<div class=bookit>" +
                        type +
                        "</div> <p class=number>В наличии " +
                        amount +
                        "</p> " +
                        "</div>";
            }
        } catch (SQLException e){
            e.printStackTrace();
        }


        return divList;
    }

}
