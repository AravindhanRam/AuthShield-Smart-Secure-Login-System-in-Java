import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.util.Properties;

public class SendEmail
{

    String from = "shridhar.ram.2005@gmail.com";
    String pass = "elgrmatjlabcdbnt"; // App Password
    public long sendEmail(String ss, JFrame user,long rand) {
        String OTP = String.valueOf(rand);
        String to = ss;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });
        long l1=0;
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject("AgRam Email-I'd Verification Mail");
            msg.setText(OTP+"\nYou are Welcome to the AgRam Application. Now Onwards You are the One of the Subscriber for Us.If Any Issues that you faced in our Application Kindly contact the Admin:+91-9489943898");

            Transport.send(msg);
            System.out.println("✅ Mail Successfully Sent");
            JOptionPane.showMessageDialog(null,"Message Send to Your Mail Successfully!");
            String val=JOptionPane.showInputDialog("Enter the OTP Send to Your Email");
            System.out.println(val);
            l1=Long.parseLong(val);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Verification OTP recieved Success Fully");
        return l1;
    }
    public void signUpACK(String email)
    {
        String to = email;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject("AgRam Sign-Up Acknowledgement Mail");
            msg.setText("\nYou are Welcome to the AgRam Application. Now Onwards You are the One of the Subscriber for Us.If Any Issues that you faced in our Application Kindly contact the Admin:+91-9489943898");

            Transport.send(msg);
            System.out.println("✅ ACK Mail Successfully Sent");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void passUpdate(String email)
    {
        String to = email;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject("AgRam Account Login Password Updated Mail");
            msg.setText("\nYou are Welcome to the AgRam Application.\nJust now Your Account Login Password get Updated Successfully.\nIf Any Issues that you faced in our Application Kindly contact the Admin:+91-9489943898");

            Transport.send(msg);
            System.out.println("✅ Password Updated Mail Successfully Sent");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void loginACK(String email)
    {
        String to = email;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject("AgRam Account Login Updated Mail");
            msg.setText("\nYou are Welcome to the AgRam Application.\nVerify this Login Was You. If not Contact the Admin\nIf Any Issues that you faced in our Application Kindly contact the Admin:+91-9489943898");

            Transport.send(msg);
            System.out.println("✅ Login ACK Mail Successfully Sent");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void profileUpdateACK(String email)
    {
        String to = email;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject("AgRam Account Profile Updated Mail");
            msg.setText("\nYou are Welcome to the AgRam Application.\nVerify this Login Was You. If not Contact the Admin\nIf Any Issues that you faced in our Application Kindly contact the Admin:+91-9489943898");

            Transport.send(msg);
            System.out.println("✅ Profile Update ACK Mail Successfully Sent");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public long forgetPassVerify(String email,long rand)
    {
        String OTP = String.valueOf(rand);
        String to = email;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });
        long l1=0;
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject("AgRam Email-I'd Verification Mail for Password Update");
            msg.setText(OTP+"\nThis Email Send to You to Verify.\nThe Password Changing ACK and OTP is in this Email\n Enter the OTP to get verified and Update Your Password\nThank You..");

            Transport.send(msg);
            System.out.println("✅ Mail Successfully Sent");
            JOptionPane.showMessageDialog(null,"Message Send to Your Mail Successfully!");
            String val=JOptionPane.showInputDialog("Enter the OTP Send to Your Email");
            System.out.println(val);
            l1=Long.parseLong(val);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Verification OTP recieved Success Fully");
        return l1;
    }
}
