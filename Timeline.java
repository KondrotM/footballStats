package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.ArrayList;

public class Timeline {

    private static ArrayList<String[]> timeline = new ArrayList<String[]>();

    public Timeline(){
        timeline = new ArrayList<String[]>();
    }

    public static void addToTimeline(String time, String command){
        String[] event = new String[]{time,command};
        System.out.println(event[0]);
        timeline.add(event);
    }

    public static ArrayList<String[]> getTimeline(){
        return timeline;
    }

    public static void printTimeline(){
        for(int i=0; i < timeline.size();i++){
            String[] action = (String[]) timeline.get(i);
            System.out.println(action[0] +" "+ action[1]);
        }
    }
}