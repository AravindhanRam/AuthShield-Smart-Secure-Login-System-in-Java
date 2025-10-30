import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ValidEmail
{
    public void isAvailableEmail(String s,JTextField jtf2, JButton jb1 ,JFrame user,JFrame Login)
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata_management","root","Aravindhan@123");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from userdata where email='"+s+"'");
            if(rs.next())
            {
                JOptionPane.showMessageDialog(null,"Email Id is Already Exist!");
//                jtf2.setText("");
                jtf2.setForeground(Color.red);
                jb1.setVisible(false);
                int choice=JOptionPane.showConfirmDialog(null,"Login to our Application?","Visiting Login Page",JOptionPane.YES_NO_CANCEL_OPTION);
                if(choice==JOptionPane.YES_OPTION) {
                    user.setVisible(false);
                    Login.setVisible(true);
                }
            }
            else {
                jtf2.setForeground(Color.green);
                jb1.setVisible(true);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in the checking the Databases");
            e.printStackTrace();
        }
    }
    public void isAvailableEmail(String old,String s,JTextField jtf2, JButton jb1 )
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata_management","root","Aravindhan@123");
            Statement stmt = con.createStatement();
            if(!old.equals(s)) {
                ResultSet rs = stmt.executeQuery("select * from userdata where email='" + s + "'");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Email Id is Already Exist!");
//                jtf2.setText("");
                    jtf2.setForeground(Color.red);
                    jb1.setVisible(false);
                } else {
                    jtf2.setForeground(Color.green);
                    jb1.setVisible(true);
                }
            }
            else
            {
                jtf2.setForeground(Color.green);
                jb1.setVisible(true);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in the checking the Databases");
            e.printStackTrace();
        }
    }
}
