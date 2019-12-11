package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import uk.ac.glos.ct5025.s1804317.footballStats.Player;
import uk.ac.glos.ct5025.s1804317.footballStats.Team;
import uk.ac.glos.ct5025.s1804317.footballStats.Timeline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayGameWindow extends MyWindow {

    private GameTimer gt = new GameTimer();

    private JLabel timeLabel;

    ActionListener changeTime = new ActionListener() {
        public void actionPerformed(ActionEvent env) {
            timeLabel.setText(gt.getWatchTime());
        }
    };






    private JList homePlayersList;
    private JList awayPlayersList;

    private Point lastLocation = null;
    private Team homeTeam = new Team("Hull");
    private Team awayTeam = new Team("Chelsea");

    private Player player1 = new Player("Harry","",homeTeam);
    private Player player2 = new Player("Steve","",homeTeam);
    private Player player3 = new Player("Jones","",homeTeam);
    private Player player4 = new Player("Nick","",awayTeam);
    private Player player5 = new Player("Sebastian","",awayTeam);
    private Player player6 = new Player("Earl","",awayTeam);


    public PlayGameWindow(JPanel panel, CardLayoutWindow clw) {
        super(panel, clw);
    }

    public void displayGameWindow(){

        new Timer(1000,changeTime).start();

        homeTeam.addActivePlayers(new ArrayList(Arrays.asList(player1,player2,player3)));
        awayTeam.addActivePlayers(new ArrayList(Arrays.asList(player4,player5,player6)));

        DefaultListModel homeModel = homeTeam.getTeamActivePlayersModel();
        homeModel.add(0,player1.getName());
        homeModel.add(1,player2.getName());
        homeModel.add(2,player3.getName());

        DefaultListModel awayModel = awayTeam.getTeamActivePlayersModel();
        awayModel.add(0,player4.getName());
        awayModel.add(1,player5.getName());
        awayModel.add(2,player6.getName());

        homePlayersList = new JList(homeModel);
        awayPlayersList = new JList(awayModel);

        JComponent homeTeamScrollList = factoryList(homePlayersList);
        JComponent awayTeamScrollList = factoryList(awayPlayersList);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Set window location.
        if (lastLocation != null) {
            //Move the window over and down 40 pixels.
            lastLocation.translate(40, 40);
            if ((lastLocation.x > getSizeX() || (lastLocation.y > getSizeY()))) {
                lastLocation.setLocation(0, 0);
            }
            frame.setLocation(lastLocation);
        } else {
            lastLocation = frame.getLocation();
        }

        JLabel scoreLabel = new JLabel(homeTeam.getName() + " " + homeTeam.getGoals()
                + " "+ "--" + " "
                + awayTeam.getGoals() + " " + awayTeam.getName());

        timeLabel = new JLabel("00:00");

        JLabel possessionLabel = new JLabel("Possession: "+homeTeam.getName());

        JComponent possessionButton = factoryButtonPane("Change Possession","ACT_POSSESSION");
        JComponent scoreButtonHome = factoryButtonPane("Goal","SCR_GOAL_HOME");
        JComponent scoreButtonAway = factoryButtonPane("Goal","SCR_GOAL_AWAY");

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Insets defaultInsets = new Insets(5,5,5,5);

        gbc.insets = defaultInsets;

        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(scoreLabel,gbc);

        gbc.gridy++;
        panel.add(timeLabel,gbc);

        gbc.gridy++;
        panel.add(possessionButton,gbc);

        gbc.gridy++;
        panel.add(possessionLabel,gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(scoreButtonHome,gbc);

        gbc.gridy++;
        panel.add(homeTeamScrollList,gbc);

        gbc.gridy--; gbc.gridx = 2;
        panel.add(scoreButtonAway,gbc);

        gbc.gridy++;
        panel.add(awayTeamScrollList,gbc);

        frame.add(panel);

        frame.pack();
        frame.setVisible(true);



    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.contains("SCR_GOAL")) {
            if (command.contains("HOME")){
                Player currPlayer = homeTeam.getActivePlayer(homePlayersList.getSelectedIndex());
                Timeline.addToTimeline(gt.getWatchTime(),currPlayer.getName());
            } else if (command.contains("AWAY")){
                Player currPlayer = awayTeam.getActivePlayer(awayPlayersList.getSelectedIndex());
                Timeline.addToTimeline(gt.getWatchTime(),currPlayer.getName());
            }
            Timeline.printTimeline();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            //    @Override is not allowed when implementing interface method
            public void run() {
                new PlayGameWindow(null,null).displayGameWindow();
            }
        });
    }

}
