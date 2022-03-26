package frontend;

// Import Swing Package
import javax.swing.*;
// Import Event Package
import java.awt.event.*;

public class Dashboard {

    Dashboard() {
        // Frame
        JFrame fDashboardFrame = new JFrame("Dashboard");

        // jButton
        JButton btnAddDevice, btnViewDevice, btnAddConnectionWindow, btnShortestPathWindow, btnViewAll, btnViewGraph;

        // Add Book Window Button
        btnAddDevice = new JButton("Add Device");
        btnAddDevice.setBounds(100, 100, 200, 60);
        fDashboardFrame.add(btnAddDevice);

        // View Book Window Button
        btnViewDevice = new JButton("View Device");
        btnViewDevice.setBounds(320, 100, 200, 60);
        fDashboardFrame.add(btnViewDevice);

        // View Book Window Button
        btnAddConnectionWindow = new JButton("Add Connection");
        btnAddConnectionWindow.setBounds(100, 180, 200, 60);
        fDashboardFrame.add(btnAddConnectionWindow);

        // Shortest Path Window Button
        btnShortestPathWindow = new JButton("Find Shortest Path");
        btnShortestPathWindow.setBounds(320, 180, 200, 60);
        fDashboardFrame.add(btnShortestPathWindow);

        // View Book Window Button
        btnViewAll = new JButton("View Connections");
        btnViewAll.setBounds(100, 260, 200, 60);
        fDashboardFrame.add(btnViewAll);

        // View Graph Window Button
        btnViewGraph = new JButton("View Graph");
        btnViewGraph.setBounds(320, 260, 200, 60);
        fDashboardFrame.add(btnViewGraph);

        // On Click - Add Book Button
        btnAddDevice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddDeviceWindow();
                fDashboardFrame.dispose();
            }
        });

        // On Click - View Book Button
        btnViewDevice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewDeviceWindow();
                fDashboardFrame.dispose();
            }
        });

        // On Click - Add Connection Button
        btnAddConnectionWindow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DeviceConnectionWindow();
                fDashboardFrame.dispose();
            }
        });

        // On Click - Short Path Button
        btnShortestPathWindow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GetShortestPathWindow();
                fDashboardFrame.dispose();
            }
        });

        // On Click - View All Connections
        btnViewAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewAllConnectionWindow();
                fDashboardFrame.dispose();
            }
        });

        // On Click - View All Graph
        btnViewGraph.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewGraph();
            }
        });

        fDashboardFrame.setLayout(null);
        fDashboardFrame.setVisible(true);
        fDashboardFrame.setSize(700, 500);

        // Stop the program once the cross button is clicked
        fDashboardFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
