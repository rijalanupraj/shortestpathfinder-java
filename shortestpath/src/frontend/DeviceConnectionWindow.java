package frontend;

import backend.*;
// Import Swing Package
import javax.swing.*;
// Import Event Package
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

public class DeviceConnectionWindow {

    DeviceConnectionWindow() {
        JFrame fDeviceConnection = new JFrame("Device Connection");
        JLabel lDeviceA, lDeviceB;
        JComboBox<String> cbDeviceA, cbDeviceB;
        JButton btnAddConnection, btnRemoveConnection, btnBack;
        JPanel panel;

        // Using GridBag Layout
        panel = new JPanel();
        panel.setSize(500, 500);
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        // Assigning Data for the array to keep in combo box
        ArrayList<Device> devicesList = Device.getAllDevices();
        String[] devicesListForComboBox = new String[devicesList.size()];
        for (int i = 0; i < devicesList.size(); i++) {
            String id = devicesList.get(i).id;
            String modelId = devicesList.get(i).modelId;
            String name = devicesList.get(i).name;
            devicesListForComboBox[i] = id + " " + modelId + " " + name;
        }

        // -------------- Device A Starts ----------------------
        // Label - Device A
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 20;
        lDeviceA = new JLabel("Device A");
        panel.add(lDeviceA, gbc);

        // ComboBox - Device A

        gbc.gridx = 1;
        gbc.gridy = 0;
        cbDeviceA = new JComboBox<String>(devicesListForComboBox);
        cbDeviceA.setPreferredSize(new Dimension(250, 30));
        panel.add(cbDeviceA, gbc);

        // -------------- Device A Ends ----------------------

        // -------------- Device B Starts ----------------------
        // Label - Device B
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 20;
        lDeviceB = new JLabel("Device B");
        panel.add(lDeviceB, gbc);

        // ComboBox - Device B

        gbc.gridx = 1;
        gbc.gridy = 1;
        cbDeviceB = new JComboBox<String>(devicesListForComboBox);
        cbDeviceB.setPreferredSize(new Dimension(250, 30));
        panel.add(cbDeviceB, gbc);

        // -------------- Device B Ends ----------------------

        // -------------- Button Starts ----------------------

        // Button - Register
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        btnAddConnection = new JButton("Connect");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(btnAddConnection, gbc);

        // Button - Login
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        btnRemoveConnection = new JButton("Disconnect");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(btnRemoveConnection, gbc);

        // Back Button
        btnBack = new JButton("Back");
        btnBack.setBounds(10, 10, 100, 30);
        fDeviceConnection.add(btnBack);

        // -------------- Button Ends ----------------------

        fDeviceConnection.add(panel);
        // fRegisterFrame.setLayout(null);
        fDeviceConnection.setVisible(true);
        fDeviceConnection.setSize(500, 500);

        // Stop the program once the cross button is clicked
        fDeviceConnection.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // On Click - Back Button
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dashboard();
                fDeviceConnection.dispose();
            }
        });

        // btnAddConnection on Click
        btnAddConnection.addActionListener(e -> {
            int checkboxAIndex = cbDeviceA.getSelectedIndex();
            int checkboxBIndex = cbDeviceB.getSelectedIndex();

            if (checkboxAIndex == -1 || checkboxBIndex == -1) {
                JOptionPane.showMessageDialog(fDeviceConnection, "First add Device", "Alert",
                        JOptionPane.WARNING_MESSAGE);
            } else if (checkboxAIndex == checkboxBIndex) {
                JOptionPane.showMessageDialog(fDeviceConnection, "Select two different Device", "Alert",
                        JOptionPane.WARNING_MESSAGE);
            } else {

                Device deviceA = devicesList.get(checkboxAIndex);
                Device deviceB = devicesList.get(checkboxBIndex);

                GraphMatrix graphMatrixObj = new GraphMatrix();
                graphMatrixObj.createIfFileDoesnotExist();
                graphMatrixObj.readMatrixFromFile();
                int indexForDeviceA = Device.getIndexOfIdRelation(deviceA.id);
                int indexForDeviceB = Device.getIndexOfIdRelation(deviceB.id);
                graphMatrixObj.addEdges(indexForDeviceA, indexForDeviceB, 1);
                JOptionPane.showMessageDialog(fDeviceConnection, "Connection Added");
            }

        });

        // btnRemoveConnection on Click
        btnRemoveConnection.addActionListener(e -> {
            int checkboxAIndex = cbDeviceA.getSelectedIndex();
            int checkboxBIndex = cbDeviceB.getSelectedIndex();

            if (checkboxAIndex == -1 || checkboxBIndex == -1) {
                JOptionPane.showMessageDialog(fDeviceConnection, "First add Device", "Alert",
                        JOptionPane.WARNING_MESSAGE);
            } else if (checkboxAIndex == checkboxBIndex) {
                JOptionPane.showMessageDialog(fDeviceConnection, "Select two different Device", "Alert",
                        JOptionPane.WARNING_MESSAGE);
            } else {

                Device deviceA = devicesList.get(checkboxAIndex);
                Device deviceB = devicesList.get(checkboxBIndex);

                GraphMatrix graphMatrixObj = new GraphMatrix();
                graphMatrixObj.createIfFileDoesnotExist();
                graphMatrixObj.readMatrixFromFile();
                int indexForDeviceA = Device.getIndexOfIdRelation(deviceA.id);
                int indexForDeviceB = Device.getIndexOfIdRelation(deviceB.id);
                graphMatrixObj.removeEdges(indexForDeviceA, indexForDeviceB);
                JOptionPane.showMessageDialog(fDeviceConnection, "Connection Removed");

            }
        });

    }

}
