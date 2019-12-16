package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import uk.ac.glos.ct5025.s1804317.footballStats.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayGameWindow extends MyWindow {


    private JLabel timeLabel = new JLabel("00:00");

    private JFrame frame;

    private Game game;

    private void playEntry(){
        String s;
        if (timeline.size()!=0) {
            if (timeline.get(0).getTime().equals(game.getGameTimer().getWatchTime())) {
                System.out.println(timeline.get(0).getTime());
                System.out.println(timeline.get(0).getAction());
                if (timeline.get(0).getAction().equals("SCR_GOAL")) {
                    if (timeline.get(0).getData()[0].equals("0")) {
                        staticHomeGoals++;
                    } else {
                        staticAwayGoals++;
                    }
                    scoreLabel.setText(staticHomeGoals + " -- " + staticAwayGoals);
                    s = " (SYS): ";
                } else if (timeline.get(0).getAction().equals("CGE_POSSESSION")) {
                    String s1;
                    if (timeline.get(0).getData()[0].equals("true")) {
                        s1 = ("POSSESSION: " + homeTeam.getName());
                    } else {
                        s1 = ("POSSESSION: " + awayTeam.getName());
                    }
                    possessionLabel.setText(s1);
                    s = " (SYS): ";
                } else if (timeline.get(0).getAction().equals("COMMENT")) {
                    s = " (USR): ";


                } else { // timeline.get(0).getAction().equals("END_GAME")
                    s = " (SYS): ";
                    timer.stop();
                    frame.dispose();

                }
                textArea.append("(" + game.getGameTimer().getWatchTime() + ")" + s + timeline.get(0).getOutput() + "\n");
                timeline.remove(0);
                playEntry();
            }
        }
    }

    private ActionListener changeTime = new ActionListener() {
        public void actionPerformed(ActionEvent env) {
            timeLabel.setText(game.getGameTimer().getWatchTime());
            if (mode.equals("PLAYBACK")){
                playEntry();
            }
            if( game.getGameTimer().getStopWatchTime() > 5400 ){
                finishGame();
            }
        }
    };

    private JLabel scoreLabel;

    JLabel errorMsg = new JLabel();


    public void finishGame(){
        game.getGameTimer().getStopWatch().stop();
        game.getTimeLine().writeEndGame(game.getGameTimer().getWatchTime(),game.getPossession());
        game.getTimeLine().getPossession();
        StaticGame currGame = new StaticGame(game);
        Tournament.getActiveTournament().addGame(currGame);
        game.finishGame();
        homeModel.clear();
        awayModel.clear();
        frame.dispose();
    }


    private int staticHomeGoals = 0;
    private int staticAwayGoals = 0;
    private JLabel possessionLabel;
    private JList homePlayersList;
    private JList awayPlayersList;
    private DefaultListModel homeModel;
    private DefaultListModel awayModel;
    private Point lastLocation = null;
    private JScrollPane scrollTextArea;
    private JTextField textField;
    private Team homeTeam = new Team("Hull");
    private Team awayTeam = new Team("Chelsea");
    private JTextArea textArea;
    private String mode;
    private StaticGame staticGame = null;
    private ArrayList<Entry> timeline;
    private Timer timer;

    public PlayGameWindow(JPanel panel, CardLayoutWindow clw, Team homeTeamTemp, Team awayTeamTemp, Game tempGame, String tempMode) {
        super(panel, clw);
        homeTeam = homeTeamTemp;
        awayTeam = awayTeamTemp;
        homeModel = homeTeam.getTeamActivePlayersModel();
        awayModel = awayTeam.getTeamActivePlayersModel();
        game = tempGame;
        mode = tempMode;
    }

    public PlayGameWindow(JPanel panel, CardLayoutWindow clw, StaticGame tempStaticGame, String tempMode){
        super(panel,clw);
        staticGame = tempStaticGame;
        homeTeam = staticGame.getHomeTeamObject();
        awayTeam = staticGame.getAwayTeamObject();
        game = new Game(homeTeam,awayTeam);
        homeModel = new DefaultListModel();
        awayModel = new DefaultListModel();
        mode = tempMode;
        timeline = (ArrayList<Entry>) staticGame.getTimeline().clone();
    }

    public void displayGameWindow(){

        timer = new Timer(1000,changeTime);
        timer.start();

        JComponent endGameButton;
        JComponent scoreButtonAway;
        JComponent scoreButtonHome;
        JComponent possessionButton;
        JButton submitButton;

        if (mode.equals("RECORD")) {
            Player currPlayer;
            for (int i = 0; i < homeTeam.getActivePlayers().size(); i++) {
                currPlayer = homeTeam.getActivePlayer(i);
                homeModel.add(homeModel.size(), currPlayer.getName());
            }
            for (int i = 0; i < awayTeam.getActivePlayers().size(); i++) {
                currPlayer = awayTeam.getActivePlayer(i);
                awayModel.add(awayModel.size(), currPlayer.getName());
            }

            homeModel.add(0, "N/A");
            awayModel.add(0, "N/A");

            homePlayersList = new JList(homeTeam.teamActivePlayersModel);
            awayPlayersList = new JList(awayTeam.teamActivePlayersModel);

            textField = new JTextField();

            submitButton = new JButton("Submit");
            submitButton.setActionCommand("ACT_SUBMIT");
            submitButton.addActionListener(this);

            possessionButton = factoryButtonPane("Change Possession","ACT_CHANGE_POSSESSION");
            scoreButtonHome = factoryButtonPane("Goal","SCR_GOAL_HOME");
            scoreButtonAway = factoryButtonPane("Goal","SCR_GOAL_AWAY");
            endGameButton = factoryButtonPane("End Game","ACT_END_GAME");

        } else { // if mode.equals("PLAYBACK") {
            String[] currPlayer;
            for (int i = 0; i < staticGame.getHomePlayerData().size(); i++){
                currPlayer = staticGame.getHomePlayerData().get(i);
                homeModel.add(homeModel.size(),currPlayer[0]);
            }
            for (int i = 0; i < staticGame.getAwayPlayerData().size(); i++){
                currPlayer = staticGame.getAwayPlayerData().get(i);
                awayModel.add(awayModel.size(),currPlayer[0]);
            }

            homeModel.add(0, "N/A");
            awayModel.add(0, "N/A");

            homePlayersList = new JList(homeModel);
            awayPlayersList = new JList(awayModel);

            textField = new JTextField();

            submitButton = new JButton("Submit");

            possessionButton = factoryButtonPane("Change Possession","..");
            scoreButtonHome = factoryButtonPane("Goal","..");
            scoreButtonAway = factoryButtonPane("Goal","..");
            endGameButton = factoryButtonPane("End Game","ACT_END_STATIC_GAME");
        }

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


        JLabel homeTeamName = new JLabel(homeTeam.getName());
        JLabel awayTeamName = new JLabel(awayTeam.getName());

        scoreLabel = new JLabel(homeTeam.getGoals() + " - " + awayTeam.getGoals());

        possessionLabel = new JLabel("Possession: "+homeTeam.getName());

        textArea = new JTextArea();
        // makes user unable to edit area
        textArea.setEditable(false);
        // makes text wrap around instead of going in an infinite line
        textArea.setLineWrap(true);
        scrollTextArea = new JScrollPane(textArea);
        scrollTextArea.setPreferredSize(new Dimension(100,100));



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

        gbc.insets = new Insets(0,0,0,0);

        gbc.gridy++; gbc.gridx = 0;
        gbc.gridwidth=3;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollTextArea,gbc);

        gbc.gridy++; gbc.gridwidth = 1;
        panel.add(submitButton,gbc);

        gbc.gridx++; gbc.gridwidth = 2;
        panel.add(textField,gbc);

        frame.add(panel);

        frame.pack();
        frame.setVisible(true);



    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.contains("SCR_GOAL")) {
            boolean isPlayer = false;
            String s;
            try {
                Player currPlayer = null;
                if (command.contains("HOME")) {
                    // checks if no player was selected
                    if (homePlayersList.getSelectedIndex() == 0) {
                        s = homeTeam.getName() + " HAS SCORED A GOAL!";
                        // scores goal and updates timeline and text
                        homeTeam.scoreGoal();
                        game.getTimeLine().writeGoal(game.getGameTimer().getWatchTime(),0,-1);
                    } else {
                        isPlayer = true;
                        s = homePlayersList.getSelectedValue().toString() + " HAS SCORED A GOAL";
                        game.getTimeLine().writeGoal(game.getGameTimer().getWatchTime(),0,homePlayersList.getSelectedIndex() - 1);
                        currPlayer = homeTeam.getActivePlayer(homePlayersList.getSelectedIndex() - 1);
                    }
                    // logs action to timeline
                    // sets who the current player is for later in the code
                } else { // if (command.contains("AWAY")){
                    if (awayPlayersList.getSelectedIndex() == 0) {
                        s = awayTeam.getName() + " HAS SCORED A GOAL!";
                        awayTeam.scoreGoal();
                        game.getTimeLine().writeGoal(game.getGameTimer().getWatchTime(),1,-1);
                        errorMsg.setText("");
                    } else {
                        isPlayer = true;
                        s = awayPlayersList.getSelectedValue().toString() + " HAS SCORED A GOAL";
                        game.getTimeLine().writeGoal(game.getGameTimer().getWatchTime(),1,awayPlayersList.getSelectedIndex() - 1);
                        currPlayer = awayTeam.getActivePlayer(awayPlayersList.getSelectedIndex() - 1);
                    }
                }
                if (isPlayer){
                    currPlayer.scoreGoal();
                }
                scoreLabel.setText(homeTeam.getGoals() + " - " + awayTeam.getGoals());
                textArea.append("(" + game.getGameTimer().getWatchTime() + ")" + " (SYS): " + s+"\n");
                // clears potential error messages
                errorMsg.setText("");
            } catch (NullPointerException ex) {
                errorMsg.setText("Select player");
            }
        }
        if( command.contains("ACT_END_GAME") ){
            finishGame();
        }
        if (command.equals("ACT_CHANGE_POSSESSION")){
            game.changePossession();
            game.getTimeLine().writePossession(game.getGameTimer().getWatchTime(),game.getPossession());
            String s;
            if (game.getPossession()) {
                s = ("POSSESSION: " + homeTeam.getName());
            } else {
                s = ("POSSESSION: " + awayTeam.getName());
            }
            possessionLabel.setText(s);
            textArea.append("(" + game.getGameTimer().getWatchTime() + ")" + " (SYS): " + s+"\n");
        }
        if (command.equals("ACT_SUBMIT")){
            game.getTimeLine().writeComment(game.getGameTimer().getWatchTime(),textField.getText());
            textArea.append("(" + game.getGameTimer().getWatchTime() + ")" + " (USR): " + textField.getText().toUpperCase()+"\n");
            textField.setText("");
        } if (command.equals("ACT_END_STATIC_GAME")){
            frame.dispose();
        }
        textArea.validate();
        JScrollBar vertical = scrollTextArea.getVerticalScrollBar();
        vertical.setValue( vertical.getMaximum() );
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
