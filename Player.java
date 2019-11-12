package uk.ac.glos.ct5025.s1804317.footballStats;

public class Player {

    private String playerForename;
    private String playerSurname;
    private String playerDoB;
    private String playerRole;


    //Creates a Player object which can then be used to assign new players
    public Player(String tempFName, String tempDoB){
        playerForename = tempFName;
//        playerSurname = tempSName;
//        playerRole = tempRole;
        playerDoB = tempDoB;
    }

    public String getPlayerName(){
        return playerForename;
    }
    public String getPlayerSurname() { return playerSurname; }
    public String getPlayerRole(){
        return playerRole;
    }
    public String getPlayerDoB(){
        return playerDoB;
    }
    public String getPlayerTeam() {return "";}
    }
