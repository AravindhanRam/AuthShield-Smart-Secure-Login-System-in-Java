import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class ProfileFrame extends JFrame
{
    Toolkit tk;
    Dimension dm;
    int wid,hgh;
    JLabel jl1,jl2,jl3,jl4,jl5,jl6;
    JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
    JButton jb1,jb2,jb3,jb4;
    String[] arr;
    UtilDateModel model;
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;
    String selectedDateStr;
    long OTP;
    ProfileFrame(String user,String email,JFrame Application)
    {
        tk = Toolkit.getDefaultToolkit();
        dm = tk.getScreenSize();
        wid=(int)dm.getWidth();
        hgh=(int)dm.getHeight();
        setLayout(null);
        setSize(wid,hgh);
        setTitle("Profile Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        arr= new ProfileDatabasesSelection().profileDetails(user);

        jl1 = new JLabel("User Name: ",JLabel.CENTER);
        jl1.setFont(new Font("SERIF",Font.ITALIC,25));
        jl1.setForeground(Color.WHITE);
        jl1.setBackground(new Color(45, 45, 45));
        jl1.setOpaque(true);
        jl1.setBounds(250,100,250,40);

        jtf1 = new JTextField(arr[1]);
        jtf1.setFont(new Font("SERIF",Font.ITALIC,25));
        jtf1.setForeground(Color.WHITE);
        jtf1.setBackground(new Color(60, 63, 65));
        jtf1.setOpaque(true);
        jtf1.setEnabled(false);
        jtf1.setBounds(520,100,250,40);
        jtf1.getDocument().addDocumentListener(new DocumentListener() {
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

                isAvailable(user,jtf1.getText());
            }
        });

        jl2 = new JLabel("Name: ",JLabel.CENTER);
        jl2.setFont(new Font("SERIF",Font.ITALIC,25));
        jl2.setForeground(Color.WHITE);
        jl2.setBackground(new Color(45, 45, 45));
        jl2.setOpaque(true);
        jl2.setBounds(250,160,250,40);

        jtf2 = new JTextField(arr[0]);
        jtf2.setFont(new Font("SERIF",Font.ITALIC,25));
        jtf2.setForeground(Color.WHITE);
        jtf2.setBackground(new Color(60, 63, 65));
        jtf2.setOpaque(true);
        jtf2.setEnabled(false);
        jtf2.setBounds(520,160,250,40);

        jl3 = new JLabel("Password: ",JLabel.CENTER);
        jl3.setFont(new Font("SERIF",Font.ITALIC,25));
        jl3.setForeground(Color.WHITE);
        jl3.setBackground(new Color(45, 45, 45));
        jl3.setOpaque(true);
        jl3.setBounds(250,220,250,40);

        jtf3 = new JTextField(arr[2]);
        jtf3.setFont(new Font("SERIF",Font.ITALIC,25));
        jtf3.setForeground(Color.WHITE);
        jtf3.setBackground(new Color(60, 63, 65));
        jtf3.setOpaque(true);
        jtf3.setEnabled(false);
        jtf3.setBounds(520,220,250,40);

        jl4 = new JLabel("Email-Id: ",JLabel.CENTER);
        jl4.setFont(new Font("SERIF",Font.ITALIC,25));
        jl4.setForeground(Color.WHITE);
        jl4.setBackground(new Color(45, 45, 45));
        jl4.setOpaque(true);
        jl4.setBounds(250,280,250,40);

        jtf4 = new JTextField(arr[5]);
        jtf4.setFont(new Font("SERIF",Font.ITALIC,25));
        jtf4.setForeground(Color.WHITE);
        jtf4.setBackground(new Color(60, 63, 65));
        jtf4.setOpaque(true);
        jtf4.setEnabled(false);
        jtf4.setBounds(520,280,250,40);
        jtf4.getDocument().addDocumentListener(new DocumentListener() {
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
                new ValidEmail().isAvailableEmail(email,jtf4.getText(),jtf4,jb3);
            }
        });

        jb3 = new JButton("Verify");
        jb3.setFont(new Font("SERIF",Font.ITALIC,25));
        jb3.setForeground(Color.WHITE);
        jb3.setBackground(Color.GREEN);
        jb3.setOpaque(true);
        jb3.setVisible(false);
        jb3.setBounds(790,280,150,40);
        long Val = new RandomOTPGeneration().randomOTP();
        System.out.println(Val);
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OTP = new SendEmail().sendEmail(jtf3.getText(),ProfileFrame.this,Val);
                System.out.println(OTP);

                if (Val == OTP) {
                    JOptionPane.showMessageDialog(null,"Validation Completed");
                    jb3.setText("Verified");
                    jb3.setBackground(Color.WHITE);
                    jb3.setForeground(Color.GREEN);
                } else {
                    JOptionPane.showMessageDialog(null,"Validation Failed");
                }
            }
        });

        jl5 = new JLabel("Date of Birth: ",JLabel.CENTER);
        jl5.setFont(new Font("SERIF",Font.ITALIC,25));
        jl5.setForeground(Color.WHITE);
        jl5.setBackground(new Color(45, 45, 45));
        jl5.setOpaque(true);
        jl5.setBounds(250,340,250,40);

        jtf5 = new JTextField(arr[3]);
        jtf5.setFont(new Font("SERIF",Font.ITALIC,25));
        jtf5.setForeground(Color.WHITE);
        jtf5.setBackground(new Color(60, 63, 65));
        jtf5.setOpaque(true);
        jtf5.setEnabled(false);
        jtf5.setEditable(false);
        jtf5.setBounds(520,340,250,40);

        model = new UtilDateModel();

        model.addChangeListener(e -> {
            java.util.Date selectedDate = (java.util.Date) model.getValue();
            if (selectedDate != null) {
                selectedDateStr = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
                jtf5.setText(selectedDateStr);
            }
        });
        datePanel =  new JDatePanelImpl(model,new Properties());
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBounds(790,340,45,40);
        datePicker.setBackground(new Color(60, 63, 65));
        datePicker.setForeground(Color.WHITE);
        datePicker.setOpaque(true);
        datePicker.setVisible(false);

        jl6 = new JLabel("Phone Number: ",JLabel.CENTER);
        jl6.setFont(new Font("SERIF",Font.ITALIC,25));
        jl6.setForeground(Color.WHITE);
        jl6.setBackground(new Color(45, 45, 45));
        jl6.setOpaque(true);
        jl6.setBounds(250,400,250,40);

        jtf6 = new JTextField(arr[4]);
        jtf6.setFont(new Font("SERIF",Font.ITALIC,25));
        jtf6.setForeground(Color.WHITE);
        jtf6.setBackground(new Color(60, 63, 65));
        jtf6.setOpaque(true);
        jtf6.setEnabled(false);
        jtf6.setBounds(520,400,250,40);
        jtf6.getDocument().addDocumentListener(new DocumentListener() {
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
                String phone = jtf6.getText();
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
                    jtf6.setForeground(Color.red);
                } else if (phone.length() == 10) {
                    jtf6.setForeground(Color.green);
                } else {
                    showErrorAndClear("Enter Valid Mobile Number with 10 digits");
                }
            }

            private void showErrorAndClear(String message) {
                // Use invokeLater to avoid mutating document inside the listener
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, message);
                    jtf6.setText("");
                });
            }
        });

        jb1 = new JButton("Update");
        jb1.setFont(new Font("SERIF",Font.ITALIC,20));
        jb1.setForeground(Color.BLACK);
        jb1.setBackground(new Color(0, 123, 255));
        jb1.setOpaque(true);
        jb1.setBounds(520,50,100,40);
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtf1.setEnabled(true);
                jtf2.setEnabled(true);
                jtf3.setEnabled(true);
                jtf4.setEnabled(true);
                jtf5.setEnabled(true);
                jtf6.setEnabled(true);
                jb3.setVisible(true);
                datePicker.setVisible(true);
                jb2.setVisible(true);
            }
        });

        jb2 = new JButton("Save");
        jb2.setFont(new Font("SERIF",Font.ITALIC,20));
        jb2.setForeground(Color.WHITE);
        jb2.setBackground(Color.GREEN);
        jb2.setOpaque(true);
        jb2.setBounds(760,50,80,40);
        jb2.setVisible(false);
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int val=0;
//                System.out.println(jtf1.isEnabled());
                if((jtf1.getForeground()==Color.green || jtf1.getForeground()==Color.white) && (jtf4.getForeground()==Color.green||jtf4.getForeground()==Color.white) && jb3.getText()=="Verified" && jtf5.getText()!=null && jtf3.getText()!=null && jtf2.getText()!=null)
                {
                    val=new ProfileDatabasesSelection().updateProfileDetails(user,jtf1.getText(),jtf2.getText(),jtf4.getText(),jtf6.getText(),java.sql.Date.valueOf(jtf5.getText()),jtf3.getText());
                    JOptionPane.showMessageDialog(null,"Database Updated Successfully.\n Return to Application Page");
                    setVisible(false);
                    System.out.println("Back to Application with Update");

                    Application.setVisible(true);
                    new SendEmail().profileUpdateACK(jtf4.getText());
                }
                else if(((jtf1.getForeground()!=Color.green || jtf1.getForeground()==Color.white) || (jtf4.getForeground()!=Color.green || jtf4.getForeground()==Color.white) || jb3.getText()!="Verified" || jtf5.getText()==null || jtf3.getText()==null || jtf2.getText()!=null))
                {
                    JOptionPane.showMessageDialog(null,"Update the Red Colored Field and Empty Field to Update the Database\nVerify the Email-Id and Try Again");
                }
//                else{
//
////                    b=false;
////                    new JFrame(); // Application Page Visible Enabled;
//                }
//                if(val>0 && b) {
//                    JOptionPane.showMessageDialog(null, "Database Updated Successfully.\n Return to Application Page");
//                    setVisible(false);
//                    System.out.println("Update Done Successfully");
////                    new JFrame(); // Application Page Visible Enabled;
//                }
            }
        });

        jb4 = new JButton("Cancel");
        jb4.setFont(new Font("SERIF",Font.ITALIC,20));
        jb4.setForeground(Color.WHITE);
        jb4.setBackground(Color.RED);
        jb4.setOpaque(true);
        jb4.setBounds(640,50,100,40);
        jb4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Application.setVisible(true);
//                new JFrame(); Application Frame
            }
        });

        add(jl1);
        add(jl2);
        add(jl3);
        add(jl4);
        add(jl5);
        add(jl6);
        add(jtf1);
        add(jtf2);
        add(jtf3);
        add(jtf4);
        add(jtf5);
        add(jtf6);
        add(jb1);
        add(jb2);
        add(jb3);
        add(datePicker);
        add(jb4);


        setVisible(true);
    }
    public void isAvailable(String old,String s)
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata_management","root","Aravindhan@123");
            Statement stmt = con.createStatement();
            if(!old.equals(s)) {
                ResultSet rs = stmt.executeQuery("select * from userdata where username='" + s + "'");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "User Name Already Exist");
                    jtf1.setForeground(Color.red);
                } else {
                    jtf1.setForeground(Color.green);
                }
            }
            else
            {
                jtf1.setForeground(Color.green);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in the checking the Databases");
            e.printStackTrace();
        }
//        return true;
    }

//    public static void main(String[] ar)
//    {
//        new ProfileFrame("AravindhanRam","aravindhan.ram.2005@gmail.com");
//    }
}
