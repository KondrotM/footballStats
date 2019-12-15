package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.ArrayList;

public class Timeline {

    private ArrayList timeline;
    private Game game;

    public Timeline(Game tempGame){
        timeline = new ArrayList<ArrayList>();
        game = tempGame;
    }

    public void addToTimeline(String time, String command){
        String[] event = new String[]{time,command};
        System.out.println(event[0]);
        timeline.add(event);
    }

    /// TIMELINE
    // time/action/data/output
    // 00:20/SCR_GOAL/int[]{0,5}/homeTeam.getActivePlayer[5].getName() scored a goal!

    public void writeGoal(String time, int teamNo, int playerNo){
        ArrayList entry = new ArrayList();
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

        entry.add(time);
        entry.add(action);
        entry.add(data);
        entry.add(output);

        timeline.add(entry);
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