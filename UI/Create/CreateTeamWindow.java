package uk.ac.glos.ct5025.s1804317.footballStats.UI.Create;

import uk.ac.glos.ct5025.s1804317.footballStats.Team;
import uk.ac.glos.ct5025.s1804317.footballStats.Tournament;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.CardLayoutWindow;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.MyWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTeamWindow extends MyWindow implements ActionListener {

    private JComponent createTeamWindow;
    private JTextField textFieldTeam;

    public CreateTeamWindow(JPanel panel, CardLayoutWindow clw) {
        super(panel, clw);
        setOpaque(true);

        createTeamWindow = factoryTeamCreateWindow();
        add(createTeamWindow);
    }

    public JComponent factoryTeamCreateWindow(){
        JLabel menuLabel = new JLabel("CREATE TEAM");

        JComponent buttonBack = factoryButtonPane("src/test", "NAV_CREATE");
        JComponent buttonCreate = factoryButtonPane("Create", "ACT_CREATE_TEAM");
        textFieldTeam = factoryTextField("Team Name");

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
        panel.add(textFieldTeam, gbc);

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
            if(!textFieldTeam.getText().equals("")) {
                System.out.println(textFieldTeam.getText());
                Team team = Team.createTeam(textFieldTeam.getText());
                textFieldTeam.setText(null);
                DefaultListModel model = Tournament.activeTournament.getTournamentTeamsModel();
                model.add(model.getSize(),team.getName());
//                System.out.println(Tournament.activeTournament.getTournamentName());
//                textFieldTournament.setText(null);

                // tournament list model and appends new tournament to it
//                DefaultListModel model = SelectTournamentWindow.getTournamentModel();
//                model.add(model.getSize(), tournament.getTournamentName());
            }
        }

    }
}
