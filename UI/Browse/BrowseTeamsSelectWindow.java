package uk.ac.glos.ct5025.s1804317.footballStats.UI.Browse;

import uk.ac.glos.ct5025.s1804317.footballStats.Team;
import uk.ac.glos.ct5025.s1804317.footballStats.Tournament;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.CardLayoutWindow;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.Create.CreatePlayerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseTeamsSelectWindow extends BrowseTeamsWindow implements ActionListener {

    private String mode;

    public BrowseTeamsSelectWindow(JPanel panel, CardLayoutWindow clw, String switchMode) {
        super(panel, clw);
        mode = switchMode;

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
            Team team = Tournament.getActiveTournament().getTeam(teamIndex);
            if (mode.equals("BROWSE")) {
                BrowsePlayersWindow browsePlayersWindow = new BrowsePlayersWindow(contentPane, CardLayoutWindow.cardLayoutWindow, team,"BROWSE");
                contentPane.add(browsePlayersWindow, "MKE_BROWSE_PLAYERS");
                cardLayout.show(contentPane, "MKE_BROWSE_PLAYERS");
            } else if(mode.equals("CREATE")){
                CreatePlayerWindow createPlayerWindow = new CreatePlayerWindow(contentPane, CardLayoutWindow.cardLayoutWindow, team);
                contentPane.add(createPlayerWindow,"MKE_CREATE_PLAYER");
                cardLayout.show(contentPane, "MKE_CREATE_PLAYER");
            } else if(mode.equals("HOME")){
                BrowsePlayersWindow browsePlayersWindow = new BrowsePlayersWindow(contentPane, CardLayoutWindow.cardLayoutWindow,team,"HOME");
                contentPane.add(browsePlayersWindow,"MKE_BROWSE_PLAYERS");
                cardLayout.show(contentPane,"MKE_BROWSE_PLAYERS");
            } else if(mode.equals("AWAY")){
                BrowsePlayersWindow browsePlayersWindow = new BrowsePlayersWindow(contentPane, CardLayoutWindow.cardLayoutWindow,team,"AWAY");
                contentPane.add(browsePlayersWindow,"MKE_BROWSE_PLAYERS");
                cardLayout.show(contentPane,"MKE_BROWSE_PLAYERS");
            }
            // Calls static function to set the selected tournament as active
//            Tournament.selectTournament(tournamentList.getSelectedIndex());
        }

    }
}
