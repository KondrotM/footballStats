package uk.ac.glos.ct5025.s1804317.footballStats;

// ArrayList necessary for declaring arrays
import java.util.ArrayList;
// Scanner necessary for getting user input
import java.util.Scanner;

// main class to allow user interface interaction
class main {
    // list of tournaments declared and initialised. all tournaments, teams and players are declared in regards to the tournament list
    public static ArrayList<Tournament> tournamentList = new ArrayList<Tournament>();
    // actively selected tournament
    public static Tournament activeTournament;

    // method to get user input
    public static String getInput(String prompt){

        // prints given prompt so long as there is one
        if (prompt != null) {
            System.out.println(prompt);
        }
        // checks what's written on the next line and returns it
        Scanner cmd = new Scanner(System.in);
        return cmd.nextLine();
    }

    // method to print main menu
    public static void getMainMenu(){
        System.out.println("FOOTBALL STATS");
        System.out.println("MAIN MENU");
        System.out.println("1. Create Tournament");
        System.out.println("2. Create Team");
        System.out.println("3. Create Player");
        System.out.println("4. Select Tournament");
        System.out.println("5. Select Teams");

        String choice = getInput(null);
        if (choice.equals("1")){
            createTournament();
        } else if (choice.equals("2")) {
            createTeam();
        } else if (choice.equals("3")) {
            createPlayer();
        } else if (choice.equals("4")) {
            selectTournament();
        } else if (choice.equals("5")) {
            selectTeams();
        }
    }

    // selects active tournament
    public static void selectTournament(){
        System.out.println("Current active tournament: " + activeTournament.getTournamentName());
        // lists all available tournaments
        System.out.println("0. Back");
        for (int i = 0; i < tournamentList.size(); i++) {
            Tournament currTournament = tournamentList.get(i);
            System.out.println(i+1 + ". " + currTournament.getTournamentName());
        }
        // gets user input and sets it as active tournament
        String currTournament = getInput("Select team number");
        if (currTournament.equals("0")){
            getMainMenu();
        } else {
            int tournamentNumber = Integer.parseInt(currTournament);
            activeTournament = tournamentList.get(tournamentNumber - 1);
        }
    }

    public static void selectTeams(){
        // lists all available teams in a tournament
        System.out.println("Teams");
        System.out.println("0. Back");
        activeTournament.viewTeams();
        // gets input
        String currTeam = getInput(null);
        if (currTeam.equals("0")){
            getMainMenu();
        } else {
            int teamNo = Integer.parseInt(currTeam);
            Team activeTeam = activeTournament.getTeam(teamNo-1);
            selectPlayers(activeTeam);

        }
    }

    public static void selectPlayers(Team activeTeam){
        //lists all available players in a team
        System.out.println("Players for "+activeTeam.getTeamName());
        System.out.println("0. Back");
        activeTeam.viewPlayers();
        String currPlayer = getInput(null);
        if (currPlayer.equals("0")){
            selectTeams();
        } else {
            Player player = activeTeam.getPlayer(Integer.parseInt(currPlayer)-1);
            System.out.println("Name: " + player.getPlayerName());
            System.out.println("Date of Birth: " +player.getPlayerDoB());
            selectTeams();
        }
    }

    // creates player and assigns them to a team
    public static void createPlayer(){
        // lists all teams within selected tournament
        ArrayList currTeams = activeTournament.getTournamentTeams();

        for (int i = 0; i < currTeams.size(); i++) {
            Team currTeam = (Team) currTeams.get(i);
            System.out.println(i+1 + ". " + currTeam.getTeamName());
        }
        // asks user for selected team number
        System.out.println("Teams");
        System.out.println("0. Back");
        activeTournament.viewTeams();
//        selectTeam();
        String teamTempNo = getInput(null);
        if (teamTempNo.equals("0")){
            getMainMenu();
        } else {
            int teamNo = Integer.parseInt(teamTempNo);
            // asks user for player information
            String playerName = getInput("Enter player name");
            String playerDoB = getInput("Enter player DoB");
            Player player1 = new Player(playerName, playerDoB);

            // gets selected team from active tournament
            Team currTeam = activeTournament.getTeam(teamNo-1);

            // assigns player to selected team
            currTeam.addPlayer(player1);
            System.out.println(player1.getPlayerName());
        }

    }

        // creates team within active tournament
    public static void createTeam(){
        String teamName = getInput("Enter team name");
        Team team1 = new Team(teamName);
        activeTournament.addTeam(team1);
    }

        // creates new tournament
    public static void createTournament(){
        String tournamentName = getInput("Enter tournament name");
        // creates new tournament and appends it to the tournament list
        Tournament tournament1 = new Tournament(tournamentName);
        tournamentList.add(tournament1);
        // if there is only one tournament, it is set as the active one
        if(tournamentList.size()==1) {
            activeTournament = tournamentList.get(0);
        }
    }


    public static void main(String args[]) {
        while(true){
            getMainMenu();
            //System.out.println(getInput(null));
        }

//        Scanner in = new Scanner(System.in);
//        String name = in.nextLine();
//        String role = in.nextLine();
////        String name = in.nextLine();
////        String name = in.nextLine();
//
//
//        Team team1 = new Team("Chelsea");
//        Player player1 = new Player(name,role);
////        Player player1 = new Player("Martin","V","Defence","20/2/1995");
////        Player player2 = new Player("Alex","S","Striker","20/2/1995");
//        team1.addPlayer(player1);
//        Player currPlayer = (Player) team1.getTeamPlayers().get(0);
//        System.out.println(currPlayer.getPlayerName());

// comment
    }}

