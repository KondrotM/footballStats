package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.Scanner;
class main
{

    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        String role = in.nextLine();
//        String name = in.nextLine();
//        String name = in.nextLine();


        Team team1 = new Team("Chelsea");
        Player player1 = new Player(name,role);
//        Player player1 = new Player("Martin","V","Defence","20/2/1995");
//        Player player2 = new Player("Alex","S","Striker","20/2/1995");
        team1.addPlayer(player1);
        Player currPlayer = (Player) team1.getTeamPlayers().get(0);
        System.out.println(currPlayer.getPlayerName());

// comment
    }}

