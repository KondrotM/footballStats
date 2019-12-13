package uk.ac.glos.ct5025.s1804317.footballStats.UI.Create;

import uk.ac.glos.ct5025.s1804317.footballStats.Tournament;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.CardLayoutWindow;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.MyWindow;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.SelectTournamentWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTournamentWindow extends MyWindow implements ActionListener {

    private JComponent createTournamentWindow;
    private JTextField textFieldTournament;

    public CreateTournamentWindow(JPanel panel, CardLayoutWindow clw) {
        super(panel, clw);
        contentPane = panel;
        setOpaque(true);

        createTournamentWindow = factoryTournamentCreateWindow();
        add(createTournamentWindow);
    }

    public JComponent factoryTournamentCreateWindow() {
        JLabel menuLabel = new JLabel("CREATE TOURNAMENT");

        JComponent buttonBack = factoryButtonPane("..", "NAV_CREATE");
        JComponent buttonCreate = factoryButtonPane("Create", "ACT_CREATE_TOURNAMENT");
        textFieldTournament = factoryTextField("Tournament Name");

        JPanel panel = new JPanel();

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(3, 3, 3, 3);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(menuLabel, gbc);

        gbc.gridy++;
        panel.add(buttonBack, gbc);

        gbc.gridy++;
        panel.add(textFieldTournament, gbc);

        gbc.gridy++;
        panel.add(buttonCreate, gbc);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.contains("NAV_")){
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, command);
        } else if (command.equals("ACT_CREATE_TOURNAMENT")){
            if(!textFieldTournament.getText().equals("")) {
                System.out.println(textFieldTournament.getText());
                Tournament.factoryTournament(textFieldTournament.getText().toUpperCase());
                System.out.println(Tournament.activeTournament.getTournamentName());
                textFieldTournament.setText(null);
            }
        }

    }
}
