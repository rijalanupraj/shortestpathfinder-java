package frontend;

import backend.*;
// Import Swing Package
import javax.swing.*;
// Import Event Package
import java.awt.event.*;
import java.awt.*;

public class RegisterWindow {

    JFrame fRegisterFrame = new JFrame("Register");
    JLabel lUsername, lPassword, lConfirmPassword;
    JTextField tfUsername;
    JPasswordField pfPassword, pfConfirmPassword;
    JButton btnLogin, btnSignUp;
    JPanel panel;

    RegisterWindow() {

        // Using GridBag Layout
        panel = new JPanel();
        panel.setSize(500, 500);
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        // -------------- Username Starts ----------------------

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

        // -------------- Confirm Password Starts --------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label - Confirm Password
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        gbc.gridx = 0;
        gbc.gridy = 2;
        lConfirmPassword = new JLabel("Confirm Password");
        panel.add(lConfirmPassword, gbc);

        // TextField - Confirm Password
        gbc.gridx = 1;
        gbc.gridy = 2;
        pfConfirmPassword = new JPasswordField();
        pfConfirmPassword.setPreferredSize(new Dimension(150, 30));
        panel.add(pfConfirmPassword, gbc);

        // -------------- Confirm Password Ends ----------------------

        // -------------- Button Starts --------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Button - Register
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        btnSignUp = new JButton("Create A Account");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(btnSignUp, gbc);

        // Button - Login
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        btnLogin = new JButton("Have A Account");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(btnLogin, gbc);

        // -------------- Button Ends ----------------------

        fRegisterFrame.add(panel);
        // fRegisterFrame.setLayout(null);
        fRegisterFrame.setVisible(true);
        fRegisterFrame.setSize(500, 500);

        // Stop the program once the cross button is clicked
        fRegisterFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // On SignUp Button Clicked
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String username = tfUsername.getText();
                String password = pfPassword.getText();
                String confirmPassword = pfConfirmPassword.getText();

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(fRegisterFrame, "Field cannot be empty");
                } else if (password.length() < 4) {
                    JOptionPane.showMessageDialog(fRegisterFrame, "Password should be at least 4 characters");
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(fRegisterFrame, "Passwords didn't match");
                } else if (User.usernameExists(username)) {
                    JOptionPane.showMessageDialog(fRegisterFrame, "Username Already Exists", "Alert",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    // Register User
                    User.registerUser(username, password);
                    JOptionPane.showMessageDialog(fRegisterFrame, "Account Created");
                    new LoginWindow();
                    fRegisterFrame.dispose();
                }

            }
        });

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginWindow();
                fRegisterFrame.dispose();
            }
        });

    }

}