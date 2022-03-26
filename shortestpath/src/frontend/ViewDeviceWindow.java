package frontend;

import backend.*;
// Import Swing Package
import javax.swing.*;
// Import Event Package
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewDeviceWindow {
    ViewDeviceWindow() {
        JFrame fViewDeviceFrame = new JFrame("View Devices");
        JButton btnBack, btnEditDevice, btnDeleteDevice;
        JPanel panel;

        // Using GridBag Layout
        panel = new JPanel();
        panel.setSize(500, 500);
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        // Header For table
        String header[] = { "Id", "Model Id", "Name", "Description" };
        // Getting all books from Database using book class
        ArrayList<Device> devicesArray = Device.getAllDevices();

        // Data to show in the table
        Object data[][] = assignDataToTable(devicesArray, header);

        // --------------- Table View Starts -----------------------
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 20;
        // Table it will take two parameter: data && header
        JTable jBookViewTable = new JTable(data, header);
        JScrollPane bookViewScrollPane = new JScrollPane(jBookViewTable);
        bookViewScrollPane.setPreferredSize(new Dimension(500, 200));
        panel.add(bookViewScrollPane, gbc);
        // --------------- Table View End -----------------------

        // -------------- Button Starts --------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Button - Register
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        btnEditDevice = new JButton("Edit Device");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(btnEditDevice, gbc);

        // Button - Login
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        btnDeleteDevice = new JButton("Delete Device");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(btnDeleteDevice, gbc);

        // Back Button
        btnBack = new JButton("Back");
        btnBack.setBounds(10, 10, 100, 30);
        fViewDeviceFrame.add(btnBack);

        // -------------- Button Ends ----------------------

        fViewDeviceFrame.add(panel);
        // fViewDeviceFrame.setLayout(null);
        fViewDeviceFrame.setVisible(true);
        fViewDeviceFrame.setSize(700, 700);

        // Stop the program once the cross button is clicked
        fViewDeviceFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // On Click - Back Button
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dashboard();
                fViewDeviceFrame.dispose();
            }
        });

        // On Click - Delete Device
        btnDeleteDevice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = jBookViewTable.getSelectedRow();
                if (row >= 0) {
                    String deviceId = devicesArray.get(row).id;
                    boolean result = Device.deleteDevice(deviceId);
                    if (result) {
                        JOptionPane.showMessageDialog(fViewDeviceFrame, "Device Deleted");
                        new ViewDeviceWindow();
                        fViewDeviceFrame.dispose();

                    } else {
                        JOptionPane.showMessageDialog(fViewDeviceFrame, "Something went wrong", "Alert",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(fViewDeviceFrame, "Select the Row first");
                }

            }
        });

        // On Click - Update Device
        btnEditDevice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = jBookViewTable.getSelectedRow();
                if (row >= 0) {
                    Device deviceObj = devicesArray.get(row);
                    fViewDeviceFrame.dispose();
                    new EditDeviceWindow(deviceObj);

                } else {
                    JOptionPane.showMessageDialog(fViewDeviceFrame, "Select the Row first");
                }

            }
        });

    }

    private Object[][] assignDataToTable(ArrayList<Device> booksArray, String[] header) {
        Object data[][] = new Object[booksArray.size()][header.length];
        // For loop to assign value to the data
        for (int i = 0; i < booksArray.size(); i++) {
            Device row = booksArray.get(i);
            data[i][0] = row.id;
            data[i][1] = row.modelId;
            data[i][2] = row.name;
            data[i][3] = row.description;
        }
        return data;
    }

}
