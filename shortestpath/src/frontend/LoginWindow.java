package frontend;

import backend.*;
// Import Swing Package
import javax.swing.*;
// Import Event Package
import java.awt.event.*;
import java.awt.*;

public class LoginWindow {

    JFrame fLoginFrame = new JFrame("Login");
    JLabel lUsername, lPassword;
    JTextField tfUsername;
    JPasswordField pfPassword;
    JButton btnLogin, btnSignUp;
    JPanel panel;

    LoginWindow() {

        // Using GridBag Layout
        panel = new JPanel();
        panel.setSize(500, 500);
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        // -------------- Username Starts --------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label - Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 20;

        lUsername = new JLabel("Username");
        panel.add(lUsername, gbc);

        // TextField - Username
        gbc.gridx = 1;
        gbc.gridy = 0;
        tfUsername = new JTextField();
        tfUsername.setPreferredSize(new Dimension(150, 30));
        panel.add(tfUsername, gbc);

        // -------------- Username Ends ----------------------

        // -------------- Password Starts --------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label - Password
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        gbc.gridx = 0;
        gbc.gridy = 1;
        lPassword = new JLabel("Password");
        panel.add(lPassword, gbc);

        // TextField - Password
        gbc.gridx = 1;
        gbc.gridy = 1;
        pfPassword = new JPasswordField();
        pfPassword.setPreferredSize(new Dimension(150, 30));
        panel.add(pfPassword, gbc);

        // -------------- Password Ends ----------------------

        // -------------- Button Starts --------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Button - Login
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        btnLogin = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(btnLogin, gbc);

        // Button - Register
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        btnSignUp = new JButton("Create A Account");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(btnSignUp, gbc);

        // -------------- Button Ends ----------------------

        fLoginFrame.add(panel);
        // fLoginFrame.setLayout();
        fLoginFrame.setVisible(true);
        fLoginFrame.setSize(500, 500);
        // Stop the program once the cross button is clicked
        fLoginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // On Login Button Clicked
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                String psw = pfPassword.getText();
                if (username.isEmpty() || psw.isEmpty()) {
                    JOptionPane.showMessageDialog(fLoginFrame, "Empty Fields", "Alert", JOptionPane.WARNING_MESSAGE);

                } else if (User.checkCredentials(username, psw)) {
                    JOptionPane.showMessageDialog(fLoginFrame, "Welcome Back");
                    new Dashboard();
                    fLoginFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(fLoginFrame, "Username/Password  Invalid", "Alert",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegisterWindow();
                fLoginFrame.dispose();
            }
        });

    }

    public static void main(String[] args) {
        LoginWindow loginObj = new LoginWindow();

    }

}
