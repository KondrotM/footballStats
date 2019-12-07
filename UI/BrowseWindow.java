package uk.ac.glos.ct5025.s1804317.footballStats.UI;

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
        JComponent buttonBrowseGames = factoryButtonPane("Games", "NAV_GAMES");
        JComponent buttonBrowseTeams = factoryButtonPane("Teams", "NAV_TEAMS");
        JComponent buttonBrowseTournament = factoryButtonPane("Tournament", "NAV_TOURNAMENT");

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
        panel.add(buttonBrowseGames,gbc);

        return panel;
    }


}