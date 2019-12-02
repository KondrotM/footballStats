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

        String choice = getInput(null);
        switch (choice) {
            case "1":
                Tournament.createTournament();
                break;
            case "2":
                Team.createTeam();
                break;
            case "3":
                Player.createPlayer();
                break;
            case "4":
                Tournament.selectTournament();
                break;
            case "5":
                Tournament.activeTournament.browse();
                break;
            case "6":
                Game.getGame();
                break;
        }
    }

    public static void main(String args[]) {
        while(true){
            getMainMenu();
        }

    }}

