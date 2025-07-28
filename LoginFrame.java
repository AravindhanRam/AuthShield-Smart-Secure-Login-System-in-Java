import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, signupButton;

    public LoginFrame() {
        setTitle("Login - AuthShield");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up");
        panel.add(loginButton);
        panel.add(signupButton);

        add(panel);

        loginButton.addActionListener(e -> login());
        signupButton.addActionListener(e -> {
            dispose();
            new SignupFrame().setVisible(true);
        });
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String hash = AuthUtils.hashPassword(password);

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password_hash=?")) {
            ps.setString(1, username);
            ps.setString(2, hash);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }
}
