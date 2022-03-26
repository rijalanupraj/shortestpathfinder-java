package frontend;

import backend.*;
// Import Swing Package
import javax.swing.*;
// Import Event Package
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;

public class ViewAllConnectionWindow {

    ViewAllConnectionWindow() {
        JFrame fViewAllConnectionFrame = new JFrame("View All Connection");
        JLabel lJLabelFirst, lJLabelSecond;
        JList listFirst, listSecond;
        JPanel panel1, panel2, panel;
        JButton btnBack;
        DefaultListModel listModelFirst = new DefaultListModel();
        DefaultListModel listModelSecond = new DefaultListModel();

        // Jpanel
        panel = new JPanel(new GridLayout(0, 1));
        panel1 = new JPanel(new GridLayout(1, 0));
        panel2 = new JPanel(new GridLayout(1, 0));

        ArrayList<Device> devicesList = Device.getAllDevices();

        for (int i = 0; i < devicesList.size(); i++) {
            Device device = devicesList.get(i);
            String data = device.id + "-" + device.modelId + " - " + device.name;
            listModelFirst.addElement(data);
        }

        lJLabelFirst = new JLabel("All Devices");
        panel1.add(lJLabelFirst);

        lJLabelSecond = new JLabel("Connected Devices");
        panel1.add(lJLabelSecond);

        // First JList
        listFirst = new JList(listModelFirst);
        panel2.add(listFirst);

        // Second JList
        listSecond = new JList(listModelSecond);
        panel2.add(listSecond);

        // Back Button
        btnBack = new JButton("Back");
        btnBack.setBounds(10, 10, 100, 30);
        fViewAllConnectionFrame.add(btnBack);

        panel.add(panel1);
        panel.add(panel2);
        fViewAllConnectionFrame.add(panel);
        fViewAllConnectionFrame.pack();
        fViewAllConnectionFrame.setLocationRelativeTo(null);
        fViewAllConnectionFrame.setSize(500, 500);
        // fViewAllConnectionFrame.setLayout(null);
        fViewAllConnectionFrame.setVisible(true);

        // Stop the program once the cross button is clicked
        fViewAllConnectionFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // On Click - Back Button
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dashboard();
                fViewAllConnectionFrame.dispose();
            }
        });

        listFirst.addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                listModelSecond.clear();
                int index = listFirst.getSelectedIndex();
                Device currentDevice = devicesList.get(index);

                GraphMatrix graphMatrixObj = new GraphMatrix();
                graphMatrixObj.createIfFileDoesnotExist();
                graphMatrixObj.readMatrixFromFile();
                ArrayList<Integer> connectedPointOfSelectedDevice = graphMatrixObj.getConnectedPoint(index);
                for (int i = 0; i < connectedPointOfSelectedDevice.size(); i++) {
                    String id = Device.getKeyFromValue(connectedPointOfSelectedDevice.get(i));
                    Device current = Device.getDeviceById(id);
                    String data = current.id + "-" + current.modelId + " - " + current.name;
                    listModelSecond.addElement(data);

                }

            }
        });

    }
}
