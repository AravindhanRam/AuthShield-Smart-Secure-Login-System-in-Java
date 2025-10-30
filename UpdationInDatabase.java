import javax.swing.*;
import java.util.Date;
import java.sql.*;

public class UpdationInDatabase
{
    public void insertDatabase(String name, String user, String mail, String pass, Date dob, String phone)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata_management","root","Aravindhan@123");
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate("insert into userdata(Name,username,password,dob,mobile,email) values ('"+name+"','"+user+"','"+pass+"','"+dob+"','"+phone+"','"+mail+"');");
            if(rs>0) {
                new SendEmail().signUpACK(mail);
                System.out.println("Success fully Inserted into Databases and ACK Send to User");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void updatePassword(String username,JFrame Login)
    {
        Login.setVisible(false);
        new PasswordUpdate(username,Login);
    }
//    public static void main(String[] ar)
//    {
//        String dobstr = "2005-02-04";
//        java.sql.Date dob = java.sql.Date.valueOf(dobstr);
////        System.out.println(dd);
//        new UpdationInDatabase().insertDatabase("Aravindhan R","Aravindh","aravindhan.r@gmail.com","Aravindhan@123",dob,"9489943898");
//    }
}