package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import uk.ac.glos.ct5025.s1804317.footballStats.Team;
import uk.ac.glos.ct5025.s1804317.footballStats.Tournament;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class BrowseTournamentWindow extends MyWindow implements ActionListener {

    private JComponent browseTournamentWindow;
    private Point lastLocation = null;

    public BrowseTournamentWindow(JPanel panel, CardLayoutWindow clw) {
        super(panel, clw);
    }

    public void displayBrowseTournamentWindow(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Set window location.
        if (lastLocation != null) {
            //Move the window over and down 40 pixels.
            lastLocation.translate(40, 40);
            if ((lastLocation.x > MyWindow.getSizeX() || (lastLocation.y > MyWindow.getSizeY()))) {
                lastLocation.setLocation(0, 0);
            }
            frame.setLocation(lastLocation);
        } else {
            lastLocation = frame.getLocation();
        }

        JLabel menuLabel = new JLabel(Tournament.activeTournament.getTournamentName());

        JComponent buttonBack = factoryButtonPane("..", "NAV_BROWSE");

        JTable tournamentTable = new JTable(new MyTableModel());

//        DefaultTableModel model = (DefaultTableModel) tournamentTable.getModel();

//        DefaultTableModel model1 = new DefaultTableModel() {
//            @Override
//            public boolean isCellEditable(int row, int col) {
//                return false;
//            }
//        };


//        DefaultTableModel model = new DefaultTableModel() {
//
//            String[] colNames = {"Team Name","GP","W","L","D","GF","GA","GD","Pts"};
//
//            @Override
//            public boolean isCellEditable(int row, int col) {
//                return false;
//            }
//
//            @Override
//            public String getColumnName(int index) {
//                return colNames[index];
//            }
//        };

        ArrayList<Team> teamList = Tournament.activeTournament.getTournamentTeams();
        Object rowData[] = new Object[9];

//        for (int i = 0; i < teamList.size(); i++) {
//            rowData[0] = teamList.get(i).getName();
//            rowData[1] = teamList.get(i).getGamesPlayed();
//            rowData[2] = teamList.get(i).getGamesWon();
//            rowData[3] = teamList.get(i).getGamesLost();
//            rowData[4] = teamList.get(i).getGamesDrawn();
//            rowData[5] = teamList.get(i).getGoalsFor();
//            rowData[6] = teamList.get(i).getGoalsAgainst();
//            rowData[7] = teamList.get(i).getGoalsDifference();
//            rowData[8] = teamList.get(i).getPoints();
//            model.addRow(rowData);
//        }

        tournamentTable.setPreferredScrollableViewportSize(new Dimension(700, 150));
        tournamentTable.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(tournamentTable);

        // Creates GridBagLayout panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Sets the padding
        gbc.insets = new Insets(3,3,3,3);

        // Adds components
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(menuLabel,gbc);

        // Increments Y grid
        gbc.gridy++;
        panel.add(buttonBack,gbc);

        gbc.gridy = 0; gbc.gridx++;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(scrollPane,gbc);

        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}