package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.ArrayList;

public class Timeline {

    private ArrayList<Entry> timeline;
    private Game game;

    public Timeline(Game tempGame){
        timeline = new ArrayList<Entry>();
        game = tempGame;
    }

//    public void addToTimeline(String time, String command){
//        String[] event = new String[]{time,command};
//        System.out.println(event[0]);
//        timeline.add(event);
//    }

    /// TIMELINE
    // time/action/data/output
    // 00:20/SCR_GOAL/int[]{0,5}/homeTeam.getActivePlayer[5].getName() scored a goal!

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

        Entry entry = new Entry(time,action,data,output);

        timeline.add(entry);
    }

    public void writePossession(String time, boolean possession){
        String action = "CGE_POSSESSION";
        String output;
        if (possession){
            output = game.getHomeTeam().getName() + " HAS POSSESSION";
        } else {
            output = game.getAwayTeam().getName() + " HAS POSSESSION";
        }

        Entry entry = new Entry(time,action,possession,output);

        timeline.add(entry);
    }

    public int getTimeFromTimer(String timer){
        int minutes = Integer.parseInt(timer.substring(0,2));
        int seconds = Integer.parseInt(timer.substring(3,5));
        int time = (minutes*60)+seconds;
        return time;
    }

    public void writeEndGame(String time, boolean possession){
        String action = "END_GAME";
        String output = "GAME OVER";

        Entry entry = new Entry(time,action,possession,output);

        timeline.add(entry);
    }

    public float getPossession(){
        float homeTeamTimePossession = 0;
        float awayTeamLastTimePossession = 0;
        float homeTeamPossession = 4f;
        boolean lastPossession = true;
        float currTime = 0;

        for (int i = 0; i < timeline.size(); i++) {
            Entry entry = timeline.get(i);
            if (entry.getAction().equals("CGE_POSSESSION")){
                if(!(Boolean) entry.getData()){
                    // calculates time the home team has had the ball based from
                    // the current time and last time the enemy team had possession
                    currTime = getTimeFromTimer(entry.getTime());
                    homeTeamTimePossession += currTime - awayTeamLastTimePossession;
                    lastPossession = false;
                } else {
                    // saves the last time the away team had the ball
                    currTime = getTimeFromTimer(entry.getTime());
                    awayTeamLastTimePossession = currTime;
                    lastPossession = true;
                }
            } if(entry.getAction().equals("END_GAME")) {
                if(!(Boolean) entry.getData()){
                    // calculates time the home team has had the ball based from
                    // the current time and last time the enemy team had possession
                    
                    homeTeamTimePossession += getTimeFromTimer(entry.getTime()) - currTime;
                } else {
                    // saves the last time the away team had the ball
                    currTime = getTimeFromTimer(entry.getTime());
                }
                if (currTime != 0) {
                    homeTeamPossession = (homeTeamTimePossession / currTime);
                }
            }
        }
        return homeTeamPossession;
    }


    public ArrayList getTimeline(){
        return timeline;
    }

    public void printTimeline(){
//        for(Object data : timeline){
//            data = (ArrayList) data;
//            for (int i = 0;)
//
//        }
    }
}