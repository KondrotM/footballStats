package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateWindow extends MyWindow implements ActionListener {

    private JComponent createWindow;

    public CreateWindow(JPanel panel, CardLayoutWindow clw) {
        super(panel, clw);
        contentPane = panel;
        setOpaque(true);

        createWindow = createCreateWindow();
        add(createWindow);
    }

    private JComponent createCreateWindow() {

        // Title label
        JLabel menuLabel = new JLabel("CREATE");

        // Creates identical buttons with a title and a command to be executed when the button is clicked
        JComponent buttonBack = createButtonPane("..","NAV_MAIN");
        JComponent buttonCreateTournament = createButtonPane("Tournament", "NAV_CREATE_TOURNAMENT");
        JComponent buttonCreateTeam = createButtonPane("Team","NAV_CREATE_TEAM");
        JComponent buttonCreatePlayer = createButtonPane("Player","NAV_CREATE_PLAYER");

        // Creates a new panel to append buttons to
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
        panel.add(buttonCreateTournament,gbc);

        gbc.gridy++;
        panel.add(buttonCreateTeam,gbc);

        gbc.gridy++;
        panel.add(buttonCreatePlayer,gbc);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();

        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, command);
    }
}
