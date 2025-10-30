//import java.util.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Properties;

class UserSignUp extends JFrame
{
    Toolkit tk;
    Dimension dim;
    int wid,hght;
    public JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7;
    public JTextField jtf1,jtf2,jtf3,jtf6,jtf7;
    public JButton jb1,jb2,jb3;
    public JPasswordField jp1,jp2;
    long OTP;
    String ps1,ps2;
    UtilDateModel model;
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;
    String selectedDateStr;
    UserSignUp(JFrame UserInterfaceLogin)
    {
        setLayout(null);
        setTitle("User Sign Up Page");
        tk=Toolkit.getDefaultToolkit();
        dim = tk.getScreenSize();
        wid =(int)dim.getWidth();
        hght = (int)dim.getHeight();
        setSize(wid,hght);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jl1 = new JLabel("Enter Your Name : ",JLabel.CENTER);
        jl1.setFont(new Font("SERIF",Font.ITALIC,30));
        jl1.setBounds(100,100,450,40);
        jl1.setForeground(Color.WHITE);
        jl1.setBackground(new Color(45, 45, 45));
        jl1.setOpaque(true);

        jtf1 = new JTextField();
        jtf1.setFont(new Font("SERIF",Font.ITALIC,30));
        jtf1.setBounds(570,100,450,40);
        jtf1.setForeground(Color.WHITE);
        jtf1.setBackground(new Color(60, 63, 65));
        jtf1.setOpaque(true);

        jl2 = new JLabel("Enter Your Preferred User Name : ",JLabel.CENTER);
        jl2.setFont(new Font("SERIF",Font.ITALIC,30));
        jl2.setBounds(100,160,450,40);
        jl2.setForeground(Color.WHITE);
        jl2.setBackground(new Color(45, 45, 45));
        jl2.setOpaque(true);

        String ss="";
        jtf2 = new JTextField();
        jtf2.setFont(new Font("SERIF",Font.ITALIC,30));
        jtf2.setBounds(570,160,450,40);
        jtf2.setForeground(Color.WHITE);
        jtf2.setBackground(new Color(60, 63, 65));
        jtf2.setOpaque(true);
        jtf2.getDocument().addDocumentListener(new DocumentListener() {
            //String s="";
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkUserName();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkUserName();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkUserName();
            }
            public void checkUserName()
            {
                isAvailable(jtf2.getText());
            }
        });

        jl3 = new JLabel("Email Id ",JLabel.CENTER);
        jl3.setBounds(100,220,450,40);
        jl3.setForeground(Color.WHITE);
        jl3.setBackground(new Color(45, 45, 45));
        jl3.setOpaque(true);
        jl3.setFont(new Font("SERIF",Font.ITALIC,30));

        jtf3 = new JTextField();
        jtf3.setBounds(570,220,450,40);
        jtf3.setForeground(Color.WHITE);
        jtf3.setBackground(new Color(60, 63, 65));
        jtf3.setOpaque(true);
        jtf3.setFont(new Font("Serif",Font.ITALIC,30));
        jtf3.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkEmail();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkEmail();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkEmail();
            }
            public void checkEmail()
            {
                new ValidEmail().isAvailableEmail(jtf3.getText(),jtf3,jb1,UserSignUp.this, UserInterfaceLogin);
            }
        });

        jb1 =new JButton("Verify");
        jb1.setBounds(1040,220,150,40);
        jb1.setForeground(Color.WHITE);
        jb1.setBackground(Color.GREEN);
        jb1.setOpaque(true);
        jb1.setFont(new Font("Serif",Font.ITALIC,30));
        long Val = new RandomOTPGeneration().randomOTP();
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OTP = new SendEmail().sendEmail(jtf3.getText(),UserSignUp.this,Val);
                System.out.println(OTP);

                if (Val == OTP) {
                    JOptionPane.showMessageDialog(null,"Validation Completed");
                    jb1.setText("Verified");
                    jb1.setBackground(Color.WHITE);
                    jb1.setForeground(Color.GREEN);
                } else {
                    JOptionPane.showMessageDialog(null,"Validation Failed");
                }
            }
        });

        jl4 = new JLabel("Create Password",JLabel.CENTER);
        jl4.setBounds(100,280,450,40);
        jl4.setBackground(new Color(45, 45, 45));
        jl4.setForeground(Color.white);
        jl4.setOpaque(true);
        jl4.setFont(new Font("SERIF",Font.ITALIC,30));

        jp1 = new JPasswordField();
        jp1.setEchoChar('*');
        jp1.setBackground(new Color(60, 63, 65));
        jp1.setForeground(Color.WHITE);
        jp1.setBounds(570,280,450,40);
        jp1.setOpaque(true);
        jp1.setFont(new Font("SERIF",Font.ITALIC,30));
        jp1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                getPass();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                getPass();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                getPass();
            }
            public void getPass()
            {
                ps1=new CheckAndUpdatePassword().getPassword1(jp1.getPassword());
            }
        });

        jl5 = new JLabel("Re-Enter Password",JLabel.CENTER);
        jl5.setBounds(100,340,450,40);
        jl5.setBackground(new Color(45, 45, 45));
        jl5.setForeground(Color.white);
        jl5.setOpaque(true);
        jl5.setFont(new Font("SERIF",Font.ITALIC,30));

        jp2 = new JPasswordField();
        jp2.setEchoChar('*');
        jp2.setBounds(570,340,450,40);
        jp2.setBackground(new Color(60, 63, 65));
//        jp2.setForeground(Color.cyan);
        jp2.setOpaque(true);
        jp2.setFont(new Font("SERIF",Font.ITALIC,30));
        jp2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                getPass();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                getPass();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                getPass();
            }
            public void getPass()
            {
                ps2=new CheckAndUpdatePassword().getPassword2(jp2.getPassword());
                new VerifyPassword().verify(ps1,ps2,jp2);
            }
        });
//        if(jp2.getBackground()==Color.red)
//            JOptionPane.showMessageDialog(null,"Re - Entered Password not Matched!");
        jl6 = new JLabel("Date of Birth",JLabel.CENTER);
        jl6.setBounds(100,400,450,40);
        jl6.setBackground(new Color(45, 45, 45));
        jl6.setForeground(Color.white);
        jl6.setOpaque(true);
        jl6.setFont(new Font("SERIF",Font.ITALIC,30));

        model = new UtilDateModel();
        model.addChangeListener(e -> {
            java.util.Date selectedDate = (java.util.Date) model.getValue();
            if (selectedDate != null) {
                selectedDateStr = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
//                System.out.println("Stored Date String: " + selectedDateStr);
            }
        });
        datePanel =  new JDatePanelImpl(model,new Properties());
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBounds(570,400,450,40);
        datePicker.setFont(new Font("SERIF",Font.ITALIC,30));
        datePicker.setOpaque(true);
        datePicker.setForeground(Color.WHITE);
        datePicker.setBackground(new Color(60, 63, 65));
//        jtf6 = new JTextField("YYYY-MM-DD");
//        jtf6.setBounds(570,400,450,40);
//        jtf6.setBackground(Color.LIGHT_GRAY);
//        jtf6.setForeground(Color.CYAN);
//        jtf6.setOpaque(true);
//        jtf6.setFont(new Font("SERIF",Font.ITALIC,30));

        jl7 = new JLabel("Enter Phone Number",JLabel.CENTER);
        jl7.setBounds(100,460,450,40);
        jl7.setBackground(new Color(45, 45, 45));
        jl7.setForeground(Color.white);
        jl7.setOpaque(true);
        jl7.setFont(new Font("SERIF",Font.ITALIC,30));

        jtf7 = new JTextField();
        jtf7.setBounds(570,460,450,40);
        jtf7.setBackground(new Color(60, 63, 65));
        jtf7.setForeground(Color.WHITE);
        jtf7.setOpaque(true);
        jtf7.setFont(new Font("SERIF",Font.ITALIC,30));
        jtf7.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkNumber();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkNumber();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkNumber();
            }

            public void checkNumber() {
                String phone = jtf7.getText();
                int count = 0;
                for (char c : phone.toCharArray()) {
                    if (count == 0 && c == '0') {
                        showErrorAndClear("Enter Valid Number");
                        return;
                    }
                    count++;
                    if (!(c >= '0' && c <= '9')) {
                        showErrorAndClear("Enter Valid Number");
                        return;
                    }
                }
                if (phone.length() < 10) {
                    jtf7.setForeground(Color.red);
                } else if (phone.length() == 10) {
                    jtf7.setForeground(Color.green);
                } else {
                    showErrorAndClear("Enter Valid Mobile Number with 10 digits");
                }
            }

            private void showErrorAndClear(String message) {
                // Use invokeLater to avoid mutating document inside the listener
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, message);
                    jtf7.setText("");
                });
            }
        });

        jb2 = new JButton("Back to Login");
        jb2.setOpaque(true);
//        jb2.setForeground(Color.WHITE);
        jb2.setBackground(new Color(0, 123, 255));
        jb2.setFont(new Font("SERIF",Font.ITALIC,30));
        jb2.setBounds(300,520,250,40);
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                UserInterfaceLogin.setVisible(true);
            }
        });

        jb3 = new JButton("SignUp");
        jb3.setOpaque(true);
//        jb3.setForeground(Color.BLACK);
        jb3.setBackground(new Color(0, 123, 255));
        jb3.setFont(new Font("SERIF",Font.ITALIC,30));
        jb3.setBounds(570,520,150,40);
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jtf2.getForeground()==Color.red)
                {
                    String ErrorMsg = "Enter the Valid User Name. You Entered UserName is Already Exist";
                    sendError(ErrorMsg);
                }
                else if(jtf3.getForeground()==Color.red || !jb1.getText().equals("Verified"))
                {
                    String Errormsg = "Enter Proper Mail Id And Verify Properly";
                    sendError(Errormsg);
                }
                else if(jp2.getForeground()==Color.red)
                {
                    String Errorms = "Re Enter the Password Correctly";
                    sendError(Errorms);
                }
                else if(selectedDateStr==null)
                {
                    String Error = "Enter Valid Date of Birth";
                    sendError(Error);
                }
                else if(jtf7.getForeground()==Color.red)
                {
                    String Errorms1 = "Enter Valid Mobile Number";
                    sendError(Errorms1);
                }
                else
                {
                    java.sql.Date dob = java.sql.Date.valueOf(selectedDateStr);
                    new UpdationInDatabase().insertDatabase(jtf1.getText(),jtf2.getText(),jtf3.getText(),ps2,dob,jtf7.getText());
                    JOptionPane.showMessageDialog(null,"SuccessFully Create the Account\nBack to Login Page!");
                    setVisible(false);
                    UserInterfaceLogin.setVisible(true);
                }
            }
            public void sendError(String s)
            {
                JOptionPane.showMessageDialog(null,s);
            }
        });

        add(jl1);
        add(jl2);
        add(jtf1);
        add(jtf2);
        add(jl3);
        add(jtf3);
        add(jb1);
        add(jl4);
        add(jp1);
        add(jl5);
        add(jp2);
        add(jl6);
        add(datePicker);
        add(jl7);
        add(jtf7);
        add(jb2);
        add(jb3);

        setVisible(true);
    }
    public void isAvailable(String s)
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata_management","root","Aravindhan@123");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from userdata where username='"+s+"'");
            if(rs.next())
            {
                JOptionPane.showMessageDialog(null,"User Name Already Exist");
//                jtf2.setText("");
                jtf2.setForeground(Color.red);
                jtf3.setEnabled(false);
//                return false;
            }
            else {
                jtf2.setForeground(Color.green);
                jtf3.setEnabled(true);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in the checking the Databases");
            e.printStackTrace();
        }
//        return true;
    }
}

class UserInterfaceLoginPage extends JFrame
{
    //JFrame jf;    `
    Toolkit tk;
    Dimension dim;
    int wid,hght;
    JLabel jl1,jl2;
    JButton jb1,jb2,jb3;
    JTextField jt1,jt2;
    JPasswordField jpf;
    //JOptionPane jop;
    long send,recieve;
    UserInterfaceLoginPage()
    {
        setLayout(null);
        setTitle("User Interface Login Page");
        tk=Toolkit.getDefaultToolkit();
        dim = tk.getScreenSize();
        wid =(int)dim.getWidth();
        hght = (int)dim.getHeight();
        setSize(wid,hght);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jl1 = new JLabel("UserName : ",JLabel.CENTER);
        jl1.setFont(new Font("SERIF",Font.ITALIC,30));
        jl1.setBackground(Color.LIGHT_GRAY);
        jl1.setForeground(Color.DARK_GRAY);
        jl1.setBounds(200,100,180,40);
        jl1.setOpaque(true);

        jt1 = new JTextField();
        jt1.setFont(new Font("SERIF",Font.ITALIC,30));
        jt1.setBackground(Color.WHITE);
//        jt1.setForeground(Color.CYAN);
        jt1.setBounds(400,100,180,40);
        jt1.setOpaque(true);

        jl2 = new JLabel(" Password : ",JLabel.CENTER);
        jl2.setFont(new Font("SERIF",Font.ITALIC,30));
        jl2.setBackground(Color.LIGHT_GRAY);
        jl2.setForeground(Color.DARK_GRAY);
        jl2.setBounds(200,160,180,40);
        jl2.setOpaque(true);

        jpf = new JPasswordField();
        jpf.setEchoChar('*');
        jpf.setFont(new Font("SERIF",Font.ITALIC,20));
        jpf.setBackground(Color.WHITE);
//        jpf.setForeground(Color.CYAN);
        jpf.setBounds(400,160,180,40);
        jpf.setOpaque(true);

        jb3 = new JButton("Forget Password");
        jb3.setFont(new Font("SERIF",Font.ITALIC,10));
        jb3.setBounds(460,205,120,20);
        jb3.setForeground(Color.WHITE);
        jb3.setBackground(new Color(0, 123, 255));
        jb3.setOpaque(true);
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jt1.getText()==null || jt1.getText().length()<1)
                {
                    JOptionPane.showMessageDialog(null,"Enter the Username!");
                    return;
                }
                send = new RandomOTPGeneration().randomOTP();
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata_management","root","Aravindhan@123");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select email from userdata where username='"+jt1.getText()+"';");
                    if(rs.next())
                    {
                        recieve=new SendEmail().forgetPassVerify(rs.getString(1),send);
                    }
                }
                catch(Exception er)
                {
                    er.printStackTrace();
                }
                if(recieve==send) {
                    new UpdationInDatabase().updatePassword(jt1.getText(), UserInterfaceLoginPage.this);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Verification get Failed\nRetry Again");
                }
            }
        });

        jb1 = new JButton("login");
        jb1.setFont(new Font("SERIF",Font.BOLD,15));
        jb1.setBounds(400,230,70,40);
        jb1.setBackground(new Color(0, 123, 255));
        jb1.setForeground(Color.WHITE);
        jb1.setOpaque(true);
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = jt1.getText();
                char[] arr = jpf.getPassword();
                String pass = new String(arr);
                try
                {
//                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata_management","root","Aravindhan@123");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from userdata where username='"+user+"'");
                    //ResultSet rs1 = stmt.executeQuery("select username from userdata where username='"+user+"'");
                    if(rs.next()) {
                        if(rs.getString(2).equals(jt1.getText())&& rs.getString(3).equals(pass)) {
                            int choice=JOptionPane.showConfirmDialog(null, "Login to our Application? ","Login Confirmation",JOptionPane.YES_NO_CANCEL_OPTION);
                            if(choice==JOptionPane.YES_OPTION)
                            {
                                System.out.println("You are Entered into Our Application Successfully");
                                //mail
                                jt1.setText("");
                                jpf.setText("");

                                setVisible(false);
                                new ApplicationPage(rs.getString(2),rs.getString(6),UserInterfaceLoginPage.this);
                                new SendEmail().loginACK(rs.getString(6));

                            }
                            if(choice==JOptionPane.NO_OPTION) {
                                System.out.println("You are Clicked to not login your account to enter to our Application");
                                jt1.setText("");
                                jpf.setText("");
                            }
                            if(choice==JOptionPane.CANCEL_OPTION) {
                                System.out.print("You are the Login Process");
                                jt1.setText("");
                                jpf.setText("");
                            }
                        }
                        if (rs.getString(2) == null)
                            JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                        if (!(rs.getString(3)).equals(pass))
                            JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                    }else
                    {
                        JOptionPane.showMessageDialog(null,"Invalid Username");
                    }

                }catch(Exception r)
                {
                    r.printStackTrace();
                }
            }
        });

        jb2 = new JButton("Sign Up");
        jb2.setFont(new Font("SERIF",Font.BOLD,15));
        jb2.setBounds(490,230,90,40);
        jb2.setBackground(new Color(0, 123, 255));
        jb2.setForeground(Color.WHITE);
        jb2.setOpaque(true);
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                jf = new JFrame("User Sign Up Page");
//                jf.setSize(wid,hght);
                UserSignUp us = new UserSignUp(UserInterfaceLoginPage.this);
                setVisible(false);
//                jf.setVisible(true);
            }
        });

        add(jl1);
        add(jt1);
        add(jl2);
        add(jpf);
        add(jb1);
        add(jb2);
        add(jb3);
        setVisible(true);
    }
}
public class JFrameUserInterfaceLoginPage {
    public static void main(String[] ar)
    {
        UserInterfaceLoginPage obj = new UserInterfaceLoginPage();
    }
}
