package gui;

import model.LocationStats;
import services.RequestHttp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cases extends JFrame implements ActionListener {

    JMenuBar jMenuBar;
    JMenuItem exitItem;
    JMenu fileMenu;
    JMenu editMenu;
    JMenu helpMenu;
    ImageIcon exitIcon;

    Cases() throws IOException, InterruptedException {
       configureFrame();
       RequestHttp requestHttp = new RequestHttp();
       getCases(requestHttp.getResponse());
    }

    private void configureFrame() {
        // Add icon to our frame
        ImageIcon logo = new ImageIcon(getClass().getResource("/icon-app.png"));
        this.setIconImage(logo.getImage());
        this.setSize(500, 500); // Set size of our frame
        this.setTitle("Corona Virus Tracker"); // Set title of our frame

        jMenuBar = new JMenuBar(); // It's used to have menu bar on the top of our frame

        // Create menu option
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        helpMenu = new JMenu("Help");

        exitItem = new JMenuItem("Exit"); // Create a item for menu option

        exitItem.addActionListener(this); // Add action when the user click's on the option
        exitItem.setMnemonic(KeyEvent.VK_E); // E for exit

        this.setJMenuBar(jMenuBar); // Add menu bar to the frame

        exitIcon = new ImageIcon(getClass().getResource("/exit-icon.png"));
        Image image = exitIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        exitIcon = new ImageIcon(newimg);
        exitItem.setIcon(exitIcon); // Set icon for our item present in the option menu
        fileMenu.add(exitItem);

        jMenuBar.add(fileMenu);
        jMenuBar.add(editMenu);
        jMenuBar.add(helpMenu);
/*
        // Add label title
        JLabel title = new JLabel();
        title.setText("Today the number of cases on planet are: ");
        title.setVerticalTextPosition(JLabel.TOP);
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setIconTextGap(10); // Set gap of text to image
        title.setOpaque(true); // Display background color
        this.add(title); */

        //addTable();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void getCases(LocationStats[] locationStats) {
        addTable(locationStats);
    }

    public void addTable(LocationStats[] locationStats) {
        JPanel panel = new JPanel();

        List<String> columnNames = new ArrayList<String>();
        columnNames.add("State");
        columnNames.add("Country");
        columnNames.add("Latest Total Cases");
        columnNames.add("Differed From PrevDay");

      /*  Object[][] data = {
                for (LocationStats locationStats1: locationStats) {
                    LocationStats locationStats2 = new LocationStats();
                    locationStats2.setState(locationStats1.getState());
                    locationStats2.setCountry(locationStats1.getCountry());
                    locationStats2.setLatestTotalCases(locationStats1.getLatestTotalCases());
                    locationStats2.setDiffFromPrevDay(locationStats1.getDiffFromPrevDay());
                }
        }; */
        List<String[]> values = new ArrayList<String[]>();
        for (LocationStats currentLocation : locationStats) {
            values.add(new String[]{currentLocation.getState(), currentLocation.getCountry(), String.valueOf(currentLocation.getLatestTotalCases()), String.valueOf(currentLocation.getDiffFromPrevDay())});
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columnNames.toArray());
        JTable table = new JTable(tableModel);

        this.add(new JScrollPane(table), BorderLayout.CENTER);

        this.add(table.getTableHeader(), BorderLayout.NORTH);

    }

    public void SetRowHeight(JTable table){
        int height = table.getRowHeight();
        table.setRowHeight(height+10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitItem) {
            System.exit(0);
        }
    }
}
