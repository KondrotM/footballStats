package uk.ac.glos.ct5025.s1804317.footballStats.UI.Browse;

import uk.ac.glos.ct5025.s1804317.footballStats.Game;
import uk.ac.glos.ct5025.s1804317.footballStats.Player;
import uk.ac.glos.ct5025.s1804317.footballStats.Team;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.CardLayoutWindow;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.MyWindow;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.PlayGameWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BrowsePlayersWindow extends MyWindow implements ActionListener, ListSelectionListener {

    private JComponent browsePlayersWindow;
    private JList playersList;
    private JList activePlayersList;
    private Team currTeam;
    private String mode;
    private static Team homeTeam;

    public static Team getHomeTeam(){
        return homeTeam;
    }

    public BrowsePlayersWindow(JPanel panel, CardLayoutWindow clw, Team team, String switchMode) {
        super(panel, clw);
        contentPane = panel;

        // Gets which team to show the players of
        currTeam = team;
        mode = switchMode;

        browsePlayersWindow = factoryBrowseTournamentWindow();
        add(browsePlayersWindow);

    }

    public JComponent factoryBrowseTournamentWindow(){
        JLabel menuLabel = new JLabel("SELECT PLAYER");

        JLabel currTeamLabel = new JLabel("TEAM: " + currTeam.getName());

        // Initialises playerList with with model of players within the team
        playersList = new JList(currTeam.getTeamPlayersModel());

        if (mode.equals("HOME") || mode.equals("AWAY")){
            playersList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        }

        activePlayersList = new JList(currTeam.getTeamActivePlayersModel());


        // Makes playerList scrollable
        JComponent playerScrollList = factoryList(playersList);

        // select up to 11 players if mode is home or away
        JComponent buttonBack = factoryButtonPane("..","NAV_CLOSE");
        JComponent buttonSelectPlayer = factoryButtonPane("Select","ACT_SELECT_PLAYER");

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

        gbc.gridy++;
        panel.add(currTeamLabel,gbc);

        gbc.gridy++;
        panel.add(playerScrollList,gbc);

        gbc.gridy++;
        panel.add(buttonSelectPlayer,gbc);

        return panel;


    }

    //    @Override is not allowed when implementing interface method
    public void valueChanged(ListSelectionEvent listSelectionEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        CardLayout cardLayout = (CardLayout) contentPane.getLayout();


        if (command.equals("NAV_CLOSE")){
            if (mode.equals("AWAY")){
                homeTeam.resetActivePlayers();
            }
            contentPane.remove(contentPane.getComponents().length-1);
            cardLayout.show(contentPane,"MKE_TEAMS_SELECT");


        } else if (command.equals("ACT_SELECT_PLAYER")){
            if(mode.equals("BROWSE")){
                Player player = currTeam.getPlayer(playersList.getSelectedIndex());
                displayFrame(player);
            } else if(mode.equals("HOME")){
                int[] players = playersList.getSelectedIndices();
                for (int i : players){
                    currTeam.addActivePlayer(i);
                }
                homeTeam = currTeam;
                BrowseTeamsSelectWindow browseTeamsSelectWindow = new BrowseTeamsSelectWindow(contentPane,CardLayoutWindow.cardLayoutWindow,"AWAY");
                contentPane.add(browseTeamsSelectWindow,command);
                cardLayout.show(contentPane,command);

            } else if(mode.equals("AWAY")){
                int[] players = playersList.getSelectedIndices();
                for (int i : players){
                    currTeam.addActivePlayer(i);
                }
                Game game = new Game(BrowsePlayersWindow.getHomeTeam(),currTeam);
                game.initialiseGame();
                PlayGameWindow playGameWindow = new PlayGameWindow(contentPane,CardLayoutWindow.cardLayoutWindow,BrowsePlayersWindow.getHomeTeam(),currTeam,game);
                playGameWindow.displayGameWindow();
                contentPane.remove(contentPane.getComponents().length-1);
                cardLayout.show(contentPane,"NAV_MAIN");
            }

//            // Calls static function to set the selected tournament as active
//            Tournament.selectTournament(tournamentList.getSelectedIndex());
        }

    }

}



//    Component[] ar = (contentPane.getComponents());
//    int i = 0;
//            for(Component component : ar){
//                    System.out.println(component.getName() + i);
//                    i++;
//                    }