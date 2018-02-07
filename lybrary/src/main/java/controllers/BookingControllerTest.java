package controllers;

import DateBase.DBHandler;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class BookingControllerTest {

    RegistrationController reg= new RegistrationController();
    EnterController ent=new EnterController();
    BookingController bk=new BookingController();
    Model model;
    HttpServletRequest request;
    HttpServletResponse response;
    String f=reg.registration("Fname","Fsurname","Femail","pass");
    String s=reg.registration("Sname","Ssurname","Semail","pass");
//    String e=ent.enter("Semail","pass",request,response,model);

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void test1() throws SQLException {
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        statement.executeQuery("SELECT*FROM users WHERE email = '"+"Semail"+"'");
        ResultSet result = statement.getResultSet();
        result.next();
        Cookie cookie = new Cookie("user_code", result.getString("cookieId"));
        bk.takeItem("2",cookie);


        ResultSet resultBooks=statement.executeQuery("SELECT * FROM documents WHERE id= '2'");
        int currentAmount = resultBooks.getInt("amount");
        assertEquals(1,currentAmount);

    }

    @org.junit.Test
    public void test3() {

    }

    @org.junit.Test
    public void test4() {
    }

    @org.junit.Test
    public void test5() {
    }

    @org.junit.Test
    public void test6() {
    }

    @org.junit.Test
    public void test7() {
    }

    @org.junit.Test
    public void test8() {
    }

    @org.junit.Test
    public void test9() {
    }

    @org.junit.Test
    public void test10() {
    }
}