package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends MyWindow implements ActionListener {

    private JComponent mainWindow;

    public MainWindow(JPanel panel, CardLayoutWindow clw) {
        super(panel, clw);
        contentPane = panel;
        setOpaque(true);

        mainWindow = createMainMenu();
        add(mainWindow);
    }

    public JComponent createMainMenu(){
        JLabel menuLabel = new JLabel("FOOTBALL STATS");

        JComponent buttonSelectTournament = createButtonPane("Select Tournament", "NAV_SELECT_TOURNAMENT");
        JComponent buttonCreate = createButtonPane("Create..", "NAV_CREATE");
        JComponent buttonBrowse = createButtonPane("Browse..", "NAV_BROWSE");
        JComponent buttonStartGame = createButtonPane("Start Game","NAV_START_GAME");

        JPanel panel = new JPanel();

        // GridBagLayout is not needed for a simple menu, but it was used for consistency throughout the program
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Sets the padding
        gbc.insets = new Insets(3,3,3,3);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(menuLabel,gbc);

        gbc.gridy++;
        panel.add(buttonCreate,gbc);

        gbc.gridy++;
        panel.add(buttonBrowse,gbc);

        gbc.gridy++;
        panel.add(buttonSelectTournament,gbc);

        gbc.gridy++;
        panel.add(buttonStartGame,gbc);

        return panel;
    }

    // Handle action events from all the buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, command);

    }



}
