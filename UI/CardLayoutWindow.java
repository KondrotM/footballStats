package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import uk.ac.glos.ct5025.s1804317.footballStats.UI.Browse.BrowseTeamsWindow;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.Browse.BrowseWindow;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.Create.CreateTeamWindow;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.Create.CreateTournamentWindow;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.Create.CreateWindow;

import javax.swing.*;
import java.awt.*;

public class CardLayoutWindow {
    private BrowseWindow browseWindow;
    private MainWindow mainWindow;
    private CreateWindow createWindow;
    private CreateTournamentWindow createTournamentWindow;
    private SelectTournamentWindow selectTournamentWindow;
    private CreateTeamWindow createTeamWindow;
    private BrowseTeamsWindow browseTeamsWindow;

    public static CardLayoutWindow cardLayoutWindow = new CardLayoutWindow();

    public void displayGUI() {
        JFrame frame = new JFrame("Football Stats");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout gridBagLayout = new GridBagLayout();
        frame.setLayout(gridBagLayout);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new CardLayout());

        browseWindow = new BrowseWindow(contentPane,cardLayoutWindow);
        mainWindow = new MainWindow(contentPane,cardLayoutWindow);
        createWindow = new CreateWindow(contentPane, cardLayoutWindow);
        createTournamentWindow = new CreateTournamentWindow(contentPane,cardLayoutWindow);
        selectTournamentWindow = new SelectTournamentWindow(contentPane,cardLayoutWindow);
        createTeamWindow = new CreateTeamWindow(contentPane, cardLayoutWindow);

        contentPane.add(mainWindow,"NAV_MAIN");
        contentPane.add(browseWindow,"NAV_BROWSE");
        contentPane.add(createWindow, "NAV_CREATE");
        contentPane.add(createTournamentWindow,"NAV_CREATE_TOURNAMENT");
        contentPane.add(selectTournamentWindow,"NAV_SELECT_TOURNAMENT");
        contentPane.add(createTeamWindow,"NAV_CREATE_TEAM");

        frame.getContentPane().add(contentPane);
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
