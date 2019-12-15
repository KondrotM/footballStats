package uk.ac.glos.ct5025.s1804317.footballStats.UI.Browse;

import uk.ac.glos.ct5025.s1804317.footballStats.UI.CardLayoutWindow;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.MyWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseWindow extends MyWindow implements ActionListener {

    private JComponent browseWindow;

    public BrowseWindow(JPanel panel, CardLayoutWindow clw) {
        super(panel, clw);
        contentPane = panel;
        setOpaque(true);

        browseWindow = createBrowseSection();
        add(browseWindow);
    }

    public JComponent createBrowseSection() {
        JLabel menuLabel = new JLabel("BROWSE");

        JComponent buttonBack = factoryButtonPane("..", "NAV_MAIN");
        JComponent buttonBrowseGames = factoryButtonPane("Games", "MKE_BROWSE_GAMES");
        JComponent buttonBrowsePlayers = factoryButtonPane("Players","MKE_TEAMS_SELECT");
        JComponent buttonBrowseTeams = factoryButtonPane("Teams", "MKE_TEAMS_BROWSE");
        JComponent buttonBrowseTournament = factoryButtonPane("Tournament", "MKE_BROWSE_TOURNAMENT");



        JPanel panel = new JPanel();

        // GridBagLayout is not needed for a simple menu, but it was used for consistency throughout the program
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Sets the padding
        gbc.insets = new Insets(3,3,3,3);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(menuLabel,gbc);

        gbc.gridy++;
        panel.add(buttonBack,gbc);

        gbc.gridy++;
        panel.add(buttonBrowseTournament,gbc);

        gbc.gridy++;
        panel.add(buttonBrowseTeams,gbc);

        gbc.gridy++;
        panel.add(buttonBrowsePlayers,gbc);

        gbc.gridy++;
        panel.add(buttonBrowseGames,gbc);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.contains("NAV_")) {
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, command);
        } else if (command.contains("MKE_TEAMS")){
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            // Creates new window to show teams for current
            BrowseTeamsWindow browseTeamsWindow;
            if(command.contains("SELECT")){
                browseTeamsWindow = new BrowseTeamsSelectWindow(contentPane,CardLayoutWindow.cardLayoutWindow,"BROWSE");
            } else { // if(command.contains("BROWSE")){
                browseTeamsWindow = new BrowseTeamsWindow(contentPane,CardLayoutWindow.cardLayoutWindow);
            }
            contentPane.add(browseTeamsWindow,command);
            cardLayout.show(contentPane,command);
        } else if (command.contains("MKE_BROWSE_GAMES")){
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            BrowseGamesWindow browseGamesWindow = new BrowseGamesWindow(contentPane, CardLayoutWindow.cardLayoutWindow);
            contentPane.add(browseGamesWindow,command);
            cardLayout.show(contentPane,command);
        }



        else if (command.equals("MKE_BROWSE_TOURNAMENT")){
            BrowseTournamentWindow browseTournamentWindow = new BrowseTournamentWindow(contentPane,CardLayoutWindow.cardLayoutWindow);
            browseTournamentWindow.displayBrowseTournamentWindow();
        }
    }

}


//    Component[] ar = (contentPane.getComponents());
//            int i = 0;
//            for(Component component : ar){
//                System.out.println(component.getName() + i);
//                i++;
//            }