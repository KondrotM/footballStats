package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.ArrayList;

public class Tournament {
    private String tournamentName;
    private ArrayList tournamentTeams = new ArrayList();

    public Tournament(String tempName){
        tournamentName = tempName;
    }

    public void addTeam(Team team){
        tournamentTeams.add(team);
    }

    public String getTournamentName(){
        return tournamentName;
    }

    public Team getTeam(int teamNo){
        Team currTeam = (Team) tournamentTeams.get(teamNo);
        return currTeam;
    }

}
