package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import uk.ac.glos.ct5025.s1804317.footballStats.Team;
import uk.ac.glos.ct5025.s1804317.footballStats.Tournament;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseTeamsSelectWindow extends BrowseTeamsWindow implements ActionListener {

    public BrowseTeamsSelectWindow(JPanel panel, CardLayoutWindow clw) {
        super(panel, clw);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("NAV_BROWSE")){
            // Removes content pane to minimise memory usage, user goes back to 'Browse' menu
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            contentPane.remove(contentPane.getComponents().length-1);
            cardLayout.show(contentPane,command);
        }
        else if (command.contains("NAV_")){
            // Navigates to requested page
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, command);
        } else if (command.equals("MKE_SELECT")){
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            int teamIndex = teamsList.getSelectedIndex();
            Team team = Tournament.activeTournament.getTeam(teamIndex);
            BrowsePlayersWindow browsePlayersWindow = new BrowsePlayersWindow(contentPane,CardLayoutWindow.cardLayoutWindow,team);
            contentPane.add(browsePlayersWindow,"MKE_BROWSE_PLAYERS");
            cardLayout.show(contentPane,"MKE_BROWSE_PLAYERS");
            // Calls static function to set the selected tournament as active
//            Tournament.selectTournament(tournamentList.getSelectedIndex());
        }

    }
}
