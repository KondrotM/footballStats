package uk.ac.glos.ct5025.s1804317.footballStats;

// ArrayList necessary for declaring arrays
import java.util.ArrayList;
// Scanner necessary for getting user input
import java.util.Scanner;

// main class to allow user interface interaction
class main {

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
        System.out.println("5. Browse..");
        System.out.println("6. Start Game");
        System.out.println("7. Browse Games");

        String choice = getInput(null);
        if (choice.equals("1")){
            Tournament.createTournament();
        } else if (choice.equals("2")) {
            Team.createTeam();
        } else if (choice.equals("3")) {
            Player.createPlayer();
        } else if (choice.equals("4")) {
            Tournament.selectTournament();
        } else if (choice.equals("5")) {
            Tournament.activeTournament.browse();
        } else if (choice.equals("6")) {
            Tournament.activeTournament.getGame();
        } else if (choice.equals("7")) {
            Tournament.activeTournament.browseGames();
        }
    }

    public static void main(String args[]) {
        while(true){
            getMainMenu();
        }

    }}

