package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import uk.ac.glos.ct5025.s1804317.footballStats.Tournament;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseTeamsWindow extends MyWindow implements ActionListener, ListSelectionListener {

    private JComponent browseTeamsWindow;
    private JList teamsList;

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

        JComponent buttonBack = factoryButtonPane("..","NAV_BROWSE");
        JComponent buttonSelectTeam = factoryButtonPane("Select","ACT_SELECT_TEAM");

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

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("NAV_BROWSE")){
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            //cardLayout.show(contentPane, command);
            contentPane.remove(contentPane.getComponents().length-1);
        }
        else if (command.contains("NAV_")){
            // Navigates to requested page
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, command);
        } else if (command.equals("ACT_SELECT_TOURNAMENT")){
            // Calls static function to set the selected tournament as active
//            Tournament.selectTournament(tournamentList.getSelectedIndex());
        }

    }
}
