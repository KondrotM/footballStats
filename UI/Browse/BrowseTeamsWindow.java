package uk.ac.glos.ct5025.s1804317.footballStats.UI.Browse;

import uk.ac.glos.ct5025.s1804317.footballStats.Team;
import uk.ac.glos.ct5025.s1804317.footballStats.Tournament;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.CardLayoutWindow;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.MyWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseTeamsWindow extends MyWindow implements ActionListener, ListSelectionListener {

    private JComponent browseTeamsWindow;
    JList teamsList;

    public BrowseTeamsWindow(JPanel panel, CardLayoutWindow clw) {
        super(panel, clw);
        contentPane = panel;
        setOpaque(true);

        browseTeamsWindow = factoryBrowseTeamsWindow();
        add(browseTeamsWindow);
    }

    public JComponent factoryBrowseTeamsWindow(){
        JLabel menuLabel = new JLabel("SELECT TEAM");

        teamsList = new JList(Tournament.activeTournament.getTournamentTeamsModel());

        JComponent teamsScrollList = factoryList(teamsList);

        JComponent buttonBack = factoryButtonPane("src/test","NAV_BROWSE");
        JComponent buttonSelectTeam = factoryButtonPane("Select","MKE_SELECT");

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
        panel.add(teamsScrollList,gbc);

        gbc.gridy++;
        panel.add(buttonSelectTeam,gbc);

        return panel;
    }

//    @Override is not allowed when implementing interface method
    public void valueChanged(ListSelectionEvent listSelectionEvent) {

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
//            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            Team team = Tournament.activeTournament.getTeam(teamsList.getSelectedIndex());
            displayFrame(team);
//            BrowsePlayersWindow browsePlayersWindow = new BrowsePlayersWindow(contentPane,CardLayoutWindow.cardLayoutWindow,team);
//            contentPane.add(browsePlayersWindow,"MKE_BROWSE_PLAYERS");
//            cardLayout.show(contentPane,"MKE_BROWSE_PLAYERS");
            }

    }
}
