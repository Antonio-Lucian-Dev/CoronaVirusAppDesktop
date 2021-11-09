package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Cases extends JFrame implements ActionListener {

    JMenuBar jMenuBar;
    JMenuItem exitItem;
    JMenu fileMenu;
    JMenu editMenu;
    JMenu helpMenu;
    ImageIcon exitIcon;

    Cases() {
       configureFrame();
      // getCases();
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

        // Add label title
        JLabel title = new JLabel();
        title.setText("Today the number of cases on planet are: ");
        title.setVerticalTextPosition(JLabel.TOP);
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setIconTextGap(10); // Set gap of text to image
        title.setOpaque(true); // Display background color
        this.add(title);

        //addTable();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void getCases() {
        addTable();
    }

    public void addTable() {
        String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};

        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", 5, Boolean.FALSE},
                {"John", "Doe",
                        "Rowing", 3, Boolean.TRUE},
                {"Sue", "Black",
                        "Knitting", 2, Boolean.FALSE},
                {"Jane", "White",
                        "Speed reading", 20, Boolean.TRUE},
                {"Joe", "Brown",
                        "Pool", 10, Boolean.FALSE}
        };

        JTable table = new JTable(data, columnNames);
        table.setBounds(30, 40, 200, 300);

        this.add(table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitItem) {
            System.exit(0);
        }
    }
}
