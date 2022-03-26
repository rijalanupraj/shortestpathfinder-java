package frontend;

import backend.*;
// Import Swing Package
import javax.swing.*;
// Import Event Package
import java.awt.event.*;
import java.awt.*;

public class AddDeviceWindow {

    AddDeviceWindow() {
        JFrame fAddDeviceFrame = new JFrame("Add Device");
        JLabel lId, lModelId, lName, lDesc;
        JTextField tfId, tfModelId, tfName, tfDesc;
        JButton btnAdd, btnBack;
        JPanel panel;

        // Using GridBag Layout
        panel = new JPanel();
        panel.setSize(500, 500);
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        // -------------- Id Starts ----------------------
        // Label - ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 20;
        lId = new JLabel("Id");
        panel.add(lId, gbc);

        // TextField - Id
        gbc.gridx = 1;
        gbc.gridy = 0;
        tfId = new JTextField();
        tfId.setPreferredSize(new Dimension(150, 30));
        panel.add(tfId, gbc);

        // -------------- Id Ends ----------------------

        // -------------- Model Id Starts --------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label - Model Id
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        gbc.gridx = 0;
        gbc.gridy = 1;
        lModelId = new JLabel("Model Id");
        panel.add(lModelId, gbc);

        // TextField - Model Id
        gbc.gridx = 1;
        gbc.gridy = 1;
        tfModelId = new JTextField();
        tfModelId.setPreferredSize(new Dimension(150, 30));
        panel.add(tfModelId, gbc);

        // -------------- Model Id Ends ----------------------

        // -------------- Name Starts --------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label - Name
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        gbc.gridx = 0;
        gbc.gridy = 2;
        lName = new JLabel("Name");
        panel.add(lName, gbc);

        // TextField - Name
        gbc.gridx = 1;
        gbc.gridy = 2;
        tfName = new JTextField();
        tfName.setPreferredSize(new Dimension(150, 30));
        panel.add(tfName, gbc);

        // -------------- Name Ends ----------------------

        // -------------- Description Starts --------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label - Description
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        gbc.gridx = 0;
        gbc.gridy = 3;
        lDesc = new JLabel("Description");
        panel.add(lDesc, gbc);

        // TextField - Description
        gbc.gridx = 1;
        gbc.gridy = 3;
        tfDesc = new JTextField();
        tfDesc.setPreferredSize(new Dimension(200, 30));
        panel.add(tfDesc, gbc);

        // -------------- Name Ends ----------------------

        // -------------- Button Starts --------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Button - Add
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        btnAdd = new JButton("Add");
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(btnAdd, gbc);

        // Back Button
        btnBack = new JButton("Back");
        btnBack.setBounds(10, 10, 100, 30);
        fAddDeviceFrame.add(btnBack);

        // -------------- Button Ends ----------------------

        fAddDeviceFrame.add(panel);
        // fRegisterFrame.setLayout(null);
        fAddDeviceFrame.setVisible(true);
        fAddDeviceFrame.setSize(500, 500);

        // Stop the program once the cross button is clicked
        fAddDeviceFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // On Click - Back Button
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dashboard();
                fAddDeviceFrame.dispose();
            }
        });

        // On Add Button Clicked
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String id = tfId.getText();
                String modelId = tfModelId.getText();
                String name = tfName.getText();
                String desc = tfDesc.getText();

                if (id.isEmpty() || modelId.isEmpty() || name.isEmpty() || desc.isEmpty()) {
                    JOptionPane.showMessageDialog(fAddDeviceFrame, "Field cannot be empty");
                } else if (Device.deviceIdExists(id)) {
                    JOptionPane.showMessageDialog(fAddDeviceFrame, "Id already Exists", "Alert",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    // Register Device
                    Device newDevice = new Device(id, modelId, name, desc);
                    Device.registerNewDevice(newDevice);
                    JOptionPane.showMessageDialog(fAddDeviceFrame, "Device Added");
                    tfId.setText("");
                    tfModelId.setText("");
                    tfName.setText("");
                    tfDesc.setText("");
                }

            }
        });

    }

}
