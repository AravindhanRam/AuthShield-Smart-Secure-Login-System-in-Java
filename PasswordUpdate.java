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
import java.util.Arrays;

public class PasswordUpdate extends JFrame
{
    JLabel jl1,jl2;
    JPasswordField jp1,jp2;
    JButton jb1,jb2;
    String pass;
    Long send,recieve;
    PasswordUpdate(String user,JFrame login)
    {

        setLayout(null);
        setSize(900,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jl1 = new JLabel("Enter New Password :",JLabel.CENTER);
        jl1.setOpaque(true);
        jl1.setForeground(Color.WHITE);
        jl1.setBackground(new Color(45, 45, 45));
        jl1.setFont(new Font("SERIF",Font.ITALIC,30));
        jl1.setBounds(50,50,350,40);

        jp1=new JPasswordField();
        jp1.setForeground(Color.WHITE);
        jp1.setOpaque(true);
        jp1.setBackground(new Color(60, 63, 65));
        jp1.setFont(new Font("SERIF",Font.ITALIC,30));
        jp1.setBounds(420,50,350,40);
        jp1.setEchoChar('*');
        jp1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkPass();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkPass();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkPass();
            }
            public void checkPass()
            {
                try
                {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata_management","root","Aravindhan@123");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from userdata where username='"+user+"';");
                    while(rs.next())
                    {
                        if(rs.getString(3).equals(String.valueOf(jp1.getPassword()))) {
                            jp1.setForeground(Color.red);
                            JOptionPane.showMessageDialog(null,"Not use the Existing Password");
                        }
                        else
                            jp1.setForeground(Color.green);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        jl2 = new JLabel("Re-Enter New Password :",JLabel.CENTER);
        jl2.setOpaque(true);
        jl2.setForeground(Color.WHITE);
        jl2.setBackground(new Color(45, 45, 45));
        jl2.setFont(new Font("SERIF",Font.ITALIC,30));
        jl2.setBounds(50,110,350,40);

        jp2=new JPasswordField();
        jp2.setOpaque(true);
        jp2.setForeground(Color.WHITE);
        jp2.setBackground(new Color(60, 63, 65));
        jp2.setFont(new Font("SERIF",Font.ITALIC,30));
        jp2.setEchoChar('*');
        jp2.setBounds(420,110,350,40);
        jp2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkPass();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkPass();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkPass();
            }
            public void checkPass()
            {
                if(jp1.getForeground()!=Color.green || jp1.getPassword().length==0)
                {
                    JOptionPane.showMessageDialog(null,"Change the Password");
                    return;
                }
                if(Arrays.equals(jp1.getPassword(),jp2.getPassword()))
                {
                    jp2.setForeground(Color.green);
                }
                else
                    jp2.setForeground(Color.red);
                pass =String.valueOf(jp2.getPassword());
            }
        });

        jb1= new JButton("Update");
        jb1.setOpaque(true);
        jb1.setForeground(Color.WHITE);
        jb1.setBackground(Color.green);
        jb1.setBounds(150,170,250,40);
        jb1.setFont(new Font("SERIF",Font.ITALIC,30));
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jp1.getForeground()!=Color.green || jp1.getPassword().length!=jp2.getPassword().length || jp1.getPassword().length==0)
                {
                    JOptionPane.showMessageDialog(null,"Change the Password");
                    return;
                }
                try
                {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata_management","root","Aravindhan@123");
                    Statement stmt = con.createStatement();
                    int rs = stmt.executeUpdate("update userdata set password='"+pass+"' where username='"+user+"';");
                    if(rs>0)
                    {
                        JOptionPane.showMessageDialog(null,"Updated Password SuccessFully!\nTry to Login Again");
                        //email

                        setVisible(false);
                        login.setVisible(true);
                    }
                    ResultSet r = stmt.executeQuery("select email from userdata where username='"+user+"';");
                    if(r.next())
                    {
                        System.out.println(r.getString(1));
                        new SendEmail().passUpdate(r.getString(1));
                    }
                }
                catch (Exception C)
                {
                    C.printStackTrace();
                }
            }
        });

        jb2 = new JButton("Cancel");
        jb2.setOpaque(true);
        jb2.setForeground(Color.WHITE);
        jb2.setBackground(Color.red);
        jb2.setBounds(420,170,250,40);
        jb2.setFont(new Font("SERIF",Font.ITALIC,30));
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                login.setVisible(true);
            }
        });


        add(jl1);
        add(jp1);
        add(jl2);
        add(jp2);
        add(jb1);
        add(jb2);
        setVisible(true);
    }
//    public static void main(String[] ar)
//    {
//        new PasswordUpdate("Aravindhan",new JFrame());
//    }
}
