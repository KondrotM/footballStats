package uk.ac.glos.ct5025.s1804317.footballStats;


import java.util.ArrayList;

public class Timeline {

    private ArrayList<Entry> timeline;
    private Game game;

    public Timeline(Game tempGame){
        timeline = new ArrayList<Entry>();
        game = tempGame;
    }

    // method for when the user wants to write their own comment
    public void writeComment(String time, String comment){
        String output = comment.toUpperCase();
        String action = "COMMENT";
        // creates new entry with no data, only player input as output
        Entry entry = new Entry(time,action,new String[]{""},output);
        timeline.add(entry);
    }

    // method for when a goal is scored
    public void writeGoal(String time, int teamNo, int playerNo){
        int[] data = new int[]{teamNo,playerNo};
        String action = "SCR_GOAL";
        String output;

        if (teamNo == 0) {
            if (playerNo == -1) {
                // Gets team name if no player scored the goal
                output = game.getHomeTeam().getName() + " SCORED A GOAL!";
            }
            else {
                output = game.getHomeTeam().getActivePlayer(playerNo).getName() + " SCORED A GOAL!";
            }
        } else if (teamNo == 1) {
            if (playerNo == -1) {
                // Gets team name if no player scored the goal
                output = game.getAwayTeam().getName() + " SCORED A GOAL!";
            }
            else {
                output = game.getAwayTeam().getActivePlayer(playerNo).getName() + " SCORED A GOAL!";
            }
        } else {
            // there are only two teams, 0 or 1. if the user manages to break this somehow then the code keeps running but lets the user know something is wrong
            output = "INCORRECT TIMELINE PARSE";
        }

        Integer.toString(data[0]);

        // saves team number (0 home / 1 away) and player index
        String[] sData = new String[]{Integer.toString(data[0]),Integer.toString(data[1])};

        Entry entry = new Entry(time,action,sData,output);

        timeline.add(entry);
    }

    // method for when the possession is changed
    public void writePossession(String time, boolean possession){
        String action = "CGE_POSSESSION";
        String output;
        if (possession){
            output = game.getHomeTeam().getName() + " HAS POSSESSION";
        } else {
            output = game.getAwayTeam().getName() + " HAS POSSESSION";
        }

        // saves boolean possession
        String[] strPossession = new String[]{Boolean.toString(possession)};

        Entry entry = new Entry(time,action,strPossession,output);

        timeline.add(entry);
    }

    // method for when the game ends
    public void writeEndGame(String time, boolean possession){
        String action = "END_GAME";
        String output = "GAME OVER";

        Entry entry = new Entry(time,action,new String[]{Boolean.toString(possession)},output);

        timeline.add(entry);
    }

    // gets second value from string timer 00:00
    public int getTimeFromTimer(String timer){
        int minutes = Integer.parseInt(timer.substring(0,2));
        int seconds = Integer.parseInt(timer.substring(3,5));
        int time = (minutes*60)+seconds;
        return time;
    }

    // calculates possession
    public float getPossession(){
        float homeTeamTimePossession = 0;
        float awayTeamLastTimePossession = 0;
        float homeTeamPossession = 4f;
        float currTime = 0;

        for (int i = 0; i < timeline.size(); i++) {
            Entry entry = timeline.get(i);
            if (entry.getAction().equals("CGE_POSSESSION")){
                if(!Boolean.valueOf(entry.getData()[0])){
                    // calculates time the home team has had the ball based from
                    // the current time and last time the enemy team had possession
                    currTime = getTimeFromTimer(entry.getTime());
                    homeTeamTimePossession += currTime - awayTeamLastTimePossession;
                } else {
                    // saves the last time the away team had the ball
                    currTime = getTimeFromTimer(entry.getTime());
                    awayTeamLastTimePossession = currTime;
                }
            } if(entry.getAction().equals("END_GAME")) {
                if(!Boolean.valueOf(entry.getData()[0])){
                    // calculates time the home team has had the ball based from
                    // the current time and last time the enemy team had possession

                    homeTeamTimePossession += getTimeFromTimer(entry.getTime()) - currTime;
                } else {
                    // saves the last time the away team had the ball
                    currTime = getTimeFromTimer(entry.getTime());
                }
                if (currTime != 0) {
                    // divides time home team has had the ball over the total time
                    homeTeamPossession = (homeTeamTimePossession / currTime);
                }
            }
        }
        return homeTeamPossession;
    }


    // returns timeline
    public ArrayList getTimeline(){
        return timeline;
    }

}