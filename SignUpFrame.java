import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignupFrame extends JFrame {
    JTextField usernameField, emailField;
    JPasswordField passwordField;
    JButton signupButton, backButton;

    public SignupFrame() {
        setTitle("Sign Up - AuthShield");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        signupButton = new JButton("Sign Up");
        backButton = new JButton("Back");
        panel.add(signupButton);
        panel.add(backButton);

        add(panel);

        signupButton.addActionListener(e -> signUp());
        backButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private void signUp() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String hash = AuthUtils.hashPassword(password);

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO users(username, email, password_hash) VALUES(?, ?, ?)")) {
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, hash);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Sign Up Successful!");
            dispose();
            new LoginFrame().setVisible(true);
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(this, "Username already exists!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }
}
