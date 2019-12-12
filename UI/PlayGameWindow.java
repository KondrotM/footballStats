package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import uk.ac.glos.ct5025.s1804317.footballStats.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayGameWindow extends MyWindow {


    private JLabel timeLabel;

    private JFrame frame;

    private Game game;

    ActionListener changeTime = new ActionListener() {
        public void actionPerformed(ActionEvent env) {
            timeLabel.setText(game.getGameTimer().getWatchTime());
            if( game.getGameTimer().getStopWatchTime() > 5400 ){
                finishGame();
            }
        }
    };

    private JLabel scoreLabel;

    JLabel errorMsg = new JLabel();


    public void finishGame(){
        game.finishGame();
        game.getGameTimer().getStopWatch().stop();
        StaticGame currGame = new StaticGame(game);
        Tournament.activeTournament.addGame(currGame);
        frame.dispose();

    }

    private JList homePlayersList;
    private JList awayPlayersList;

    private Point lastLocation = null;
    private Team homeTeam = new Team("Hull");
    private Team awayTeam = new Team("Chelsea");

//    private Player player1 = new Player("Harry","",homeTeam);
//    private Player player2 = new Player("Steve","",homeTeam);
//    private Player player3 = new Player("Jones","",homeTeam);
//    private Player player4 = new Player("Nick","",awayTeam);
//    private Player player5 = new Player("Sebastian","",awayTeam);
//    private Player player6 = new Player("Earl","",awayTeam);


    public PlayGameWindow(JPanel panel, CardLayoutWindow clw, Team homeTeamTemp, Team awayTeamTemp, Game tempGame) {
        super(panel, clw);
        homeTeam = homeTeamTemp;
        awayTeam = awayTeamTemp;
        game = tempGame;
    }

    public void displayGameWindow(){

        new Timer(1000,changeTime).start();

//        homeTeam.addActivePlayers(new ArrayList(Arrays.asList(player1,player2,player3)));
//        awayTeam.addActivePlayers(new ArrayList(Arrays.asList(player4,player5,player6)));
//
        DefaultListModel homeModel = homeTeam.getTeamActivePlayersModel();
        DefaultListModel awayModel = awayTeam.getTeamActivePlayersModel();

        Player currPlayer;
        for(int i = 0; i < homeTeam.getActivePlayers().size(); i++){
            currPlayer = homeTeam.getActivePlayer(i);
            homeModel.add(homeModel.size(),currPlayer.getName());
        }
        for(int i = 0; i < awayTeam.getActivePlayers().size(); i++){
            currPlayer = awayTeam.getActivePlayer(i);
            awayModel.add(awayModel.size(),currPlayer.getName());
        }
        homeModel.add(0,"N/A");
        awayModel.add(0,"N/A");


//        homeModel.add(0,player1.getName());
//        homeModel.add(1,player2.getName());
//        homeModel.add(2,player3.getName());
//
//        DefaultListModel awayModel = awayTeam.getTeamActivePlayersModel();
//        awayModel.add(0,player4.getName());
//        awayModel.add(1,player5.getName());
//        awayModel.add(2,player6.getName());

        homePlayersList = new JList(homeTeam.teamActivePlayersModel);
        awayPlayersList = new JList(awayTeam.teamActivePlayersModel);

        JComponent homeTeamScrollList = factoryList(homePlayersList);
        JComponent awayTeamScrollList = factoryList(awayPlayersList);

        frame = new JFrame();
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

//        JLabel scoreLabel = new JLabel(homeTeam.getName() + " " + homeTeam.getGoals()
//                + " "+ "--" + " "
//                + awayTeam.getGoals() + " " + awayTeam.getName());

        JLabel homeTeamName = new JLabel(homeTeam.getName());
        JLabel awayTeamName = new JLabel(awayTeam.getName());

        scoreLabel = new JLabel(homeTeam.getGoals() + " - " + awayTeam.getGoals());

        timeLabel = new JLabel("00:00");

        JLabel possessionLabel = new JLabel("Possession: "+homeTeam.getName());

        JComponent possessionButton = factoryButtonPane("Change Possession","ACT_POSSESSION");
        JComponent scoreButtonHome = factoryButtonPane("Goal","SCR_GOAL_HOME");
        JComponent scoreButtonAway = factoryButtonPane("Goal","SCR_GOAL_AWAY");
        JComponent endGameButton = factoryButtonPane("End Game","ACT_END_GAME");

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Insets defaultInsets = new Insets(5,5,5,5);

        gbc.insets = defaultInsets;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(homeTeamName,gbc);

        gbc.gridx++;
        panel.add(scoreLabel,gbc);

        gbc.gridx++;
        panel.add(awayTeamName,gbc);

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

        gbc.gridx--;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.SOUTH;

        panel.add(endGameButton,gbc);

        gbc.gridy++;
        panel.add(errorMsg,gbc);

        frame.add(panel);

        frame.pack();
        frame.setVisible(true);



    }

    // User can end game
    // if endgamebutton.pressed || time = 90 mins



    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.contains("SCR_GOAL")) {
            try {
                Player currPlayer;
                if (command.contains("HOME")) {
                    if (homePlayersList.getSelectedIndex() == 0) {
                        homeTeam.scoreGoal();
                        Timeline.addToTimeline(game.getGameTimer().getWatchTime(), homeTeam.getName());
                        scoreLabel.setText(homeTeam.getGoals() + " - " + awayTeam.getGoals());
                        errorMsg.setText("");
                        return;
                    }
                    currPlayer = homeTeam.getActivePlayer(homePlayersList.getSelectedIndex() - 1);
                } else { // if (command.contains("AWAY")){
                    if (awayPlayersList.getSelectedIndex() == 0) {
                        awayTeam.scoreGoal();
                        Timeline.addToTimeline(game.getGameTimer().getWatchTime(), awayTeam.getName());
                        scoreLabel.setText(homeTeam.getGoals() + " - " + awayTeam.getGoals());
                        errorMsg.setText("");
                        return;
                    }
                    currPlayer = awayTeam.getActivePlayer(awayPlayersList.getSelectedIndex() - 1);
                }
                currPlayer.scoreGoal();
                Timeline.addToTimeline(game.getGameTimer().getWatchTime(), currPlayer.getName());
                scoreLabel.setText(homeTeam.getGoals() + " - " + awayTeam.getGoals());
                Timeline.printTimeline();
                errorMsg.setText("");
            } catch (NullPointerException ex) {
                errorMsg.setText("Select player");
            }
        } if( command.contains("ACT_END_GAME") ){
            finishGame();
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            //    @Override is not allowed when implementing interface method
//            public void run() {
//                new PlayGameWindow(null,null).displayGameWindow();
//            }
//        });
//    }

}
