package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import uk.ac.glos.ct5025.s1804317.footballStats.Player;
import uk.ac.glos.ct5025.s1804317.footballStats.Team;
import uk.ac.glos.ct5025.s1804317.footballStats.Tournament;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatePlayerWindow extends MyWindow implements ActionListener {

    private JComponent createPlayerWindow;
    private JTextField textFieldPlayerName;
    private Team currTeam;

    public CreatePlayerWindow(JPanel panel, CardLayoutWindow clw, Team team) {
        super(panel, clw);

        setOpaque(true);

        currTeam = team;

        createPlayerWindow = factoryPlayerCreateWindow();
        add(createPlayerWindow);
    }

    public JComponent factoryPlayerCreateWindow(){
        JLabel menuLabel = new JLabel("CREATE TEAM");

        JComponent buttonBack = factoryButtonPane("..", "NAV_CREATE");
        JComponent buttonCreate = factoryButtonPane("Create", "ACT_CREATE_TEAM");
        textFieldPlayerName = factoryTextField("Player Name");

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
        panel.add(textFieldPlayerName, gbc);

        gbc.gridy++;
        panel.add(buttonCreate, gbc);

        gbc.gridy++;
        panel.add(getErrorMessage(), gbc);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.contains("NAV_")){
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, command);
        } else if (command.equals("ACT_CREATE_TEAM")){
            if(!textFieldPlayerName.getText().equals("")) {
                System.out.println(textFieldPlayerName.getText());
                Player player = new Player(textFieldPlayerName.getText(),"",currTeam);
                player.addToTeam();
                textFieldPlayerName.setText(null);
                DefaultListModel model = currTeam.getTeamPlayersModel();
                model.add(model.getSize(),player.getName());
//                System.out.println(Tournament.activeTournament.getTournamentName());
//                textFieldTournament.setText(null);

                // tournament list model and appends new tournament to it
//                DefaultListModel model = SelectTournamentWindow.getTournamentModel();
//                model.add(model.getSize(), tournament.getTournamentName());
            }
        }

    }
}
