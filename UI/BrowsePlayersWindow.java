package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import uk.ac.glos.ct5025.s1804317.footballStats.Team;
import uk.ac.glos.ct5025.s1804317.footballStats.Tournament;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowsePlayersWindow extends MyWindow implements ActionListener, ListSelectionListener {

    private JComponent browsePlayersWindow;
    private JList playersList;
    private Team currTeam;

    public BrowsePlayersWindow(JPanel panel, CardLayoutWindow clw, Team team) {
        super(panel, clw);
        contentPane = panel;

        // Gets which team to show the players of
        currTeam = team;

        browsePlayersWindow = factoryBrowseTournamentWindow();
        add(browsePlayersWindow);

    }

    public JComponent factoryBrowseTournamentWindow(){
        JLabel menuLabel = new JLabel("SELECT PLAYER");

        JLabel currTeamLabel = new JLabel("TEAM: " + currTeam.getName());

        // Initialises playerList with with model of players within the team
        playersList = new JList(currTeam.getTeamPlayersModel());

        // Makes playerList scrollable
        JComponent playerScrollList = factoryList(playersList);

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

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("NAV_CLOSE")){
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            contentPane.remove(contentPane.getComponents().length-1);

            cardLayout.show(contentPane,"MKE_BROWSE_TEAMS");
            Component[] ar = (contentPane.getComponents());
            int i = 0;
            for(Component component : ar){
                System.out.println(component.getName() + i);
                i++;
            }
        } else if (command.equals("ACT_SELECT_PLAYER")){
//            // Calls static function to set the selected tournament as active
//            Tournament.selectTournament(tournamentList.getSelectedIndex());
        }

    }
}
