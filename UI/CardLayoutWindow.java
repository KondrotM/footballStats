package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import javax.swing.*;
import java.awt.*;

public class CardLayoutWindow {
    private BrowseWindow browseWindow;
    private MainWindow mainWindow;
    private CreateWindow createWindow;


    public void displayGUI() {
        JFrame frame = new JFrame("Football Stats");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout gridBagLayout = new GridBagLayout();
        frame.setLayout(gridBagLayout);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new CardLayout());

        browseWindow = new BrowseWindow(contentPane,this);
        mainWindow = new MainWindow(contentPane,this);
        createWindow = new CreateWindow(contentPane, this);

        contentPane.add(mainWindow,"NAV_MAIN");
        contentPane.add(browseWindow,"NAV_BROWSE");
        contentPane.add(createWindow, "NAV_CREATE");

        frame.getContentPane().add(contentPane);
//        frame.pack();
        frame.setSize(MyWindow.getSizeX(),MyWindow.getSizeY());
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CardLayoutWindow().displayGUI();
            }
        });
    }

}
