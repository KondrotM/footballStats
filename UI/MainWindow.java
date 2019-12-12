package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import uk.ac.glos.ct5025.s1804317.footballStats.UI.Browse.BrowseTeamsSelectWindow;

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

        JComponent buttonSelectTournament = factoryButtonPane("Select Tournament", "NAV_SELECT_TOURNAMENT");
        JComponent buttonCreate = factoryButtonPane("Create..", "NAV_CREATE");
        JComponent buttonBrowse = factoryButtonPane("Browse..", "NAV_BROWSE");
        JComponent buttonStartGame = factoryButtonPane("Start Game","MKE_SELECT_HOME");

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
        if (command.contains("NAV_")) {
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, command);
        } else if(command.equals("MKE_SELECT_HOME")){
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            BrowseTeamsSelectWindow browseTeamsSelectWindow = new BrowseTeamsSelectWindow(contentPane,CardLayoutWindow.cardLayoutWindow,"HOME");
            contentPane.add(browseTeamsSelectWindow,command);
            cardLayout.show(contentPane,command);
        }
    }



}
