package frontend;

import backend.*;
// Import Swing Package
import javax.swing.*;
// Import Event Package
import java.awt.*;
import java.awt.event.*;

public class EditDeviceWindow {
    Device device;

    EditDeviceWindow(Device device) {
        this.device = device;

        JFrame fEditDeviceFrame = new JFrame("Edit Device");
        JLabel lModelId, lName, lDesc;
        JTextField tfModelId, tfName, tfDesc;
        JButton btnEdit, btnBack;
        JPanel panel;

        // Using GridBag Layout
        panel = new JPanel();
        panel.setSize(500, 500);
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

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
        btnEdit = new JButton("Save");
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(btnEdit, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Button - Add
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        btnBack = new JButton("Cancel");
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(btnBack, gbc);

        // -------------- Button Ends ----------------------

        // Setting Default Value
        tfModelId.setText(device.modelId);
        tfName.setText(device.name);
        tfDesc.setText(device.description);

        fEditDeviceFrame.add(panel);
        // fRegisterFrame.setLayout(null);
        fEditDeviceFrame.setVisible(true);
        fEditDeviceFrame.setSize(500, 500);

        // Stop the program once the cross button is clicked
        fEditDeviceFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // On Click - Back Button
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewDeviceWindow();
                fEditDeviceFrame.dispose();
            }
        });

        // On Click - Edit Button
        btnEdit.addActionListener(e -> {
            String modelId = tfModelId.getText();
            String name = tfName.getText();
            String desc = tfDesc.getText();

            // Validating the fields
            if (modelId.isEmpty() || name.isEmpty() || desc.isEmpty()) {
                JOptionPane.showMessageDialog(fEditDeviceFrame, "Field cannot be empty");
            } else {

                Device deviceObj = new Device(this.device.id, modelId, name, desc);
                boolean result = Device.updateDevice(deviceObj);
                if (result) {
                    JOptionPane.showMessageDialog(fEditDeviceFrame, "Updated Successfully");
                    fEditDeviceFrame.dispose();
                    new ViewDeviceWindow();
                } else {
                    JOptionPane.showMessageDialog(fEditDeviceFrame, "Something went wrong", "Alert",
                            JOptionPane.WARNING_MESSAGE);
                }
            }

        });

    }

}
