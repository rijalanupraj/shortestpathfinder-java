package frontend;

import backend.*;
// Import Swing Package
import javax.swing.*;
// Import Event Package
import java.awt.event.*;
import java.util.ArrayList;

public class GetShortestPathWindow {

    GetShortestPathWindow() {
        JFrame fShortestPath = new JFrame("Get Shortest Path");
        JLabel lDeviceA, lDeviceB, lShortest, lUnitLabel, lUnitShow;
        JComboBox<String> cbDeviceA, cbDeviceB;
        JButton btnBack, btnShortestPath;
        JList listShortest;
        DefaultListModel listModel = new DefaultListModel();

        ArrayList<Device> devicesList = Device.getAllDevices();
        String[] devicesListForComboBox = new String[devicesList.size()];

        for (int i = 0; i < devicesList.size(); i++) {
            String id = devicesList.get(i).id;
            String modelId = devicesList.get(i).modelId;
            String name = devicesList.get(i).name;
            devicesListForComboBox[i] = id + " " + modelId + " " + name;
        }

        // Device A
        lDeviceA = new JLabel("Device A");
        lDeviceA.setBounds(100, 100, 100, 30);
        fShortestPath.add(lDeviceA);

        cbDeviceA = new JComboBox<String>(devicesListForComboBox);
        cbDeviceA.setBounds(220, 100, 150, 30);
        fShortestPath.add(cbDeviceA);

        // Device B
        lDeviceB = new JLabel("Device B");
        lDeviceB.setBounds(100, 150, 100, 30);
        fShortestPath.add(lDeviceB);

        cbDeviceB = new JComboBox<String>(devicesListForComboBox);
        cbDeviceB.setBounds(220, 150, 150, 30);
        fShortestPath.add(cbDeviceB);

        // lShortest
        lShortest = new JLabel("Shortest Path from top to bottom");
        lShortest.setBounds(150, 270, 200, 30);
        fShortestPath.add(lShortest);

        // Jlist
        listShortest = new JList(listModel);
        listShortest.setBounds(150, 300, 200, 100);
        fShortestPath.add(listShortest);

        // Back Button
        btnBack = new JButton("Back");
        btnBack.setBounds(10, 10, 100, 30);
        fShortestPath.add(btnBack);

        // Shortest Path Button
        btnShortestPath = new JButton("Get Shortest Path");
        btnShortestPath.setBounds(150, 200, 150, 30);
        fShortestPath.add(btnShortestPath);

        // l Unit Label
        lUnitLabel = new JLabel("Distance:");
        lUnitLabel.setBounds(150, 235, 100, 30);
        fShortestPath.add(lUnitLabel);

        // l Unit Label
        lUnitShow = new JLabel("");
        lUnitShow.setBounds(250, 235, 50, 30);
        fShortestPath.add(lUnitShow);

        fShortestPath.setSize(500, 500);
        fShortestPath.setLayout(null);
        fShortestPath.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fShortestPath.setVisible(true);

        // Stop the program once the cross button is clicked
        fShortestPath.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // On Click - Back Button
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dashboard();
                fShortestPath.dispose();
            }
        });

        // On Click - Back Button
        btnShortestPath.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int checkboxAIndex = cbDeviceA.getSelectedIndex();
                int checkboxBIndex = cbDeviceB.getSelectedIndex();
                listModel.clear();
                lUnitShow.setText("");
                if (checkboxAIndex == checkboxBIndex) {
                    JOptionPane.showMessageDialog(fShortestPath, "Select two different Device", "Alert",
                            JOptionPane.WARNING_MESSAGE);
                } else {

                    Device deviceA = devicesList.get(checkboxAIndex);
                    Device deviceB = devicesList.get(checkboxBIndex);

                    GraphMatrix graphMatrixObj = new GraphMatrix();
                    graphMatrixObj.createIfFileDoesnotExist();
                    graphMatrixObj.readMatrixFromFile();

                    int indexForDeviceA = Device.getIndexOfIdRelation(deviceA.id);
                    int indexForDeviceB = Device.getIndexOfIdRelation(deviceB.id);

                    ArrayList<Integer> path = graphMatrixObj.getShortestPath(indexForDeviceA, indexForDeviceB);

                    if (path.size() < 2) {
                        JOptionPane.showMessageDialog(fShortestPath, "No Connection Found", "Alert",
                                JOptionPane.WARNING_MESSAGE);
                    } else {

//                       String distanceToReach = String.valueOf(graphMatrixObj.shortestDistance);
                    	String distanceToReach = String.valueOf(path.size()-1);
                        new ShortestPathViewGraph(path, distanceToReach);

                        lUnitShow.setText(distanceToReach);
                        for (int i = 0; i < path.size(); i++) {
                            String idOfDevice = Device.getKeyFromValue(path.get(i));
                            listModel.addElement(idOfDevice);
                        }
                    }
                }

            }
        });

    }

}
