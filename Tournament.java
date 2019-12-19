package uk.ac.glos.ct5025.s1804317.footballStats;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.SelectTournamentWindow;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Tournament {

    // list of tournaments declared and initialised. all tournaments, teams and players are declared in regards to the tournament list
    private final static ArrayList<Tournament> tournamentList = new ArrayList<Tournament>();

    // actively selected tournament
    private static Tournament activeTournament;

    private String tournamentName;
    // saves all teams within tournament
    private ArrayList<Team> tournamentTeams;
    // saves all games within tournament
    private ArrayList<StaticGame> tournamentGameList;
    // xml file path
    private final static String xmlFilePath = "./src/main/java/uk/ac/glos/ct5025/s1804317/footballStats/Tournaments/";

    // displays teams within tournament
    private DefaultListModel tournamentTeamsModel = new DefaultListModel();

    // displays games within tournament
    private DefaultListModel tournamentGamesModel = new DefaultListModel();


    public Tournament(String tempName){
        tournamentName = tempName;
        tournamentTeams = new ArrayList();
        tournamentGameList = new ArrayList();
    }

    // getters

    public static Tournament getActiveTournament(){
        return activeTournament;
    }

    private ArrayList<StaticGame> getTournamentGameList(){
        return tournamentGameList;
    }

    public StaticGame getGame(int i) {
        StaticGame game = tournamentGameList.get(i);
        return game;
    }

    public String getTournamentName(){
        try{
            return tournamentName;
        } catch (NullPointerException e) {
            return "No tournament selected";
        }
    }

    public DefaultListModel getTournamentGamesModel(){
        return tournamentGamesModel;
    }

    public DefaultListModel getTournamentTeamsModel(){
        return tournamentTeamsModel;
    }

    public static ArrayList<Tournament> getTournamentList(){
        return tournamentList;
    }

    public ArrayList<Team> getTournamentTeams(){
        return tournamentTeams;
    }

    // gets team
    public Team getTeam(int teamNo){
        Team currTeam = tournamentTeams.get(teamNo);
        return currTeam;
    }

    // gets team index in regards to the teamList
    private int getTeamIndex(Team team){
        ArrayList<Team> teamsList = tournamentTeams;
        int index = teamsList.indexOf(team);
        return index;
    }


    public void addTeam(Team team){
        // adds team to tournament
        tournamentTeams.add(team);
        // adds team to display
        tournamentTeamsModel.add(tournamentTeamsModel.size(), team.getName());
    }

    public void addGame(StaticGame game) {
        // adds game to tournament
        tournamentGameList.add(game);
        // adds game to display
        tournamentGamesModel.add(tournamentGamesModel.size(), game.getTitle());
    }

    // creates new tournament and appends it to tournament list
    public static void createTournament(String tournamentName){
        Tournament tournament = null;
        // checks if name is not empty
        if (tournamentName != ""){
            // creates new tournament
            tournament = new Tournament(tournamentName);
            // appends
            addTournament(tournament);
        }
    }

    private static void addTournament(Tournament tournament){
        // gets tournament list model and appends new tournament to it
        DefaultListModel model = SelectTournamentWindow.getTournamentModel();
        model.add(model.getSize(), tournament.getTournamentName());

        // adds tournament to tournament list
        tournamentList.add(tournament);
        // if there is only one tournament, it is set as the active one
        if(tournamentList.size()==1) {
            selectTournament(0);
        }
    }

    // selects active tournament
    public static void selectTournament(int tournamentNumber){
        activeTournament = tournamentList.get(tournamentNumber);
        // updates UI text
        SelectTournamentWindow.getCurrTournament().setText("ACTIVE TOURNAMENT: " + activeTournament.getTournamentName());
        SelectTournamentWindow.getCurrTournament().repaint();
        SelectTournamentWindow.getCurrTournament().revalidate();

    }

    public static void importTournament(File file){
        try {
            // creates a new instance of documentBuilder, the thing which builds xml elements
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            // Normalises document so it's easier for the computer to read
            document.getDocumentElement().normalize();

            // new tournament from xml name
            Tournament tournament = new Tournament(document.getDocumentElement().getAttribute("name"));

            // adds tournament to list
            addTournament(tournament);

            // gets teams element
            Element teamsList = (Element) document.getElementsByTagName("teams").item(0);
            // gets a NodeList of all the teams
            NodeList teamsNodeList = teamsList.getElementsByTagName("team");

            for(int i = 0; i < teamsNodeList.getLength(); i++){
                // turns current team node into element which can be manipulated better
                Element teamElement = (Element) teamsNodeList.item(i);

                // new team from team name
                Team team = new Team(teamElement.getElementsByTagName("name").item(0).getTextContent());

                // sets team attributes from xml
                team.setGamesWon(Integer.parseInt(teamElement.getElementsByTagName("gameswon").item(0).getTextContent()));
                team.setGamesLost(Integer.parseInt(teamElement.getElementsByTagName("gameslost").item(0).getTextContent()));
                team.setGamesDrawn(Integer.parseInt(teamElement.getElementsByTagName("gamesdrawn").item(0).getTextContent()));
                team.setGoalsFor(Integer.parseInt(teamElement.getElementsByTagName("goalsfor").item(0).getTextContent()));
                team.setGoalsAgainst(Integer.parseInt(teamElement.getElementsByTagName("goalsagainst").item(0).getTextContent()));
                team.setPoints(Integer.parseInt(teamElement.getElementsByTagName("teampoints").item(0).getTextContent()));

                // gets players element
                Element playersList = (Element) teamElement.getElementsByTagName("players").item(0);
                // gets a nodelist of all the players
                NodeList playersNodeList = playersList.getElementsByTagName("player");
                for(int j = 0; j < playersNodeList.getLength(); j++){
                    Element playerElement = (Element) playersNodeList.item(j);

                    Player player = new Player(playerElement.getElementsByTagName("name").item(0).getTextContent(),"",team);
                    player.setGamesWon(Integer.parseInt(playerElement.getElementsByTagName("gameswon").item(0).getTextContent()));
                    player.setGamesLost(Integer.parseInt(playerElement.getElementsByTagName("gameslost").item(0).getTextContent()));
                    player.setGamesDrawn(Integer.parseInt(playerElement.getElementsByTagName("gamesdrawn").item(0).getTextContent()));
                    player.setGoalsFor(Integer.parseInt(playerElement.getElementsByTagName("goalsfor").item(0).getTextContent()));

                    // adds player to team
                    team.addPlayer(player);
                }

                // adds team to tournament
                tournament.addTeam(team);

            }

            // gets games
            Element gamesList = (Element) document.getElementsByTagName("games").item(0);
            NodeList gameNodeList = gamesList.getElementsByTagName("game");

            for(int i = 0; i < gameNodeList.getLength(); i++) {
                Element gameElement = (Element) gameNodeList.item(i);

                // gets date, time, home team possession
                String gameDate = gameElement.getElementsByTagName("date").item(0).getTextContent();
                String gameTime = gameElement.getElementsByTagName("time").item(0).getTextContent();
                float gamePossession = Float.parseFloat(gameElement.getElementsByTagName("possession").item(0).getTextContent());

                // gets timeline
                Element timeLineElement = (Element) gameElement.getElementsByTagName("timeline").item(0);

                NodeList entryNodeList = timeLineElement.getElementsByTagName("entry");

                // creates new timeline
                ArrayList<Entry> timeline = new ArrayList<Entry>();

                // adds entries to timeline
                for(int j = 0; j < entryNodeList.getLength(); j++){
                    Element entryElement = (Element) entryNodeList.item(j);

                    String entryTime = entryElement.getElementsByTagName("time").item(0).getTextContent();
                    String entryAction = entryElement.getElementsByTagName("action").item(0).getTextContent();
                    String entryOutput = entryElement.getElementsByTagName("output").item(0).getTextContent();
                    String[] entryData = new String[]{
                            entryElement.getElementsByTagName("data").item(0).getTextContent(),"0"
                    };


                    Entry entry = new Entry(entryTime,entryAction,entryData,entryOutput);

                    timeline.add(entry);
                }

                // gets home team attributes
                Element homeTeamElement = (Element) gameElement.getElementsByTagName("hometeam").item(0);

                String homeTeamName = homeTeamElement.getElementsByTagName("name").item(0).getTextContent();
                int homeTeamIndex = Integer.parseInt(homeTeamElement.getAttribute("id"));
                int homeTeamGoals = Integer.parseInt(homeTeamElement.getElementsByTagName("goals").item(0).getTextContent());

                Element homeTeamPlayersElement = (Element) gameElement.getElementsByTagName("players").item(0);
                NodeList homeTeamPlayersNodeList = homeTeamPlayersElement.getElementsByTagName("player");

                ArrayList<String[]> homePlayerData = new ArrayList<String[]>();

                for(int j = 0; j < homeTeamPlayersNodeList.getLength(); j++ ){
                    Element playerElement = (Element) homeTeamPlayersNodeList.item(j);


                    System.out.println(playerElement.getElementsByTagName("name").item(0).getTextContent());

                    String[] playerData = new String[]{
                            playerElement.getElementsByTagName("name").item(0).getTextContent(),
                            playerElement.getElementsByTagName("goals").item(0).getTextContent()
                    };

                    homePlayerData.add(playerData);
                }

                // gets away team attributes
                Element awayTeamElement = (Element) gameElement.getElementsByTagName("awayteam").item(0);

                int awayTeamIndex = Integer.parseInt(awayTeamElement.getAttribute("id"));
                String awayTeamName = awayTeamElement.getElementsByTagName("name").item(0).getTextContent();
                int awayTeamGoals = Integer.parseInt(awayTeamElement.getElementsByTagName("goals").item(0).getTextContent());


                Element awayTeamPlayersElement = (Element) awayTeamElement.getElementsByTagName("players").item(0);
                NodeList awayTeamPlayersNodeList = awayTeamPlayersElement.getElementsByTagName("player");

                ArrayList<String[]> awayPlayerData = new ArrayList<String[]>();

                for(int j = 0; j < awayTeamPlayersNodeList.getLength(); j++ ){
                    Element playerElement = (Element) awayTeamPlayersNodeList.item(j);

                    String[] playerData = new String[]{
                            playerElement.getElementsByTagName("name").item(0).getTextContent(),
                            playerElement.getElementsByTagName("goals").item(0).getTextContent()
                    };

                    awayPlayerData.add(playerData);
                }

                // initialises static game from all the imported data
                StaticGame game = new StaticGame(homeTeamName,awayTeamName,homeTeamGoals,awayTeamGoals,gameDate,
                        gameTime,homePlayerData,awayPlayerData,gamePossession,homeTeamIndex,awayTeamIndex,timeline);

                // appends game to tournament
                tournament.addGame(game);

            }

            // catches errors
        } catch (ParserConfigurationException e) {
            e.printStackTrace();

        } catch (SAXException e) {
            e.printStackTrace();
            // Cannot parse file
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void exportTournament(Tournament tournament){
        String path = xmlFilePath + tournament.getTournamentName() + ".xml";
        try {
            // creates document which is then used for element manipulation
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // creates root element
            Element root = document.createElement("tournament");
            // appends tournament to root
            document.appendChild(root);
            // create's the tournament's attribute: name
            root.setAttribute("name",tournament.getTournamentName());

            // appends teams to tournament
            Element teams = document.createElement("teams");
            teams.setAttribute("id","teams");
            root.appendChild(teams);

            Element team;
            int teamID = 0;

            // Constructs XML element for each team with corresponding data and appends it to the teams element
            for (Team currTeam : tournament.getTournamentTeams()){
                team = document.createElement("team");
                teams.appendChild(team);

                team.setAttribute("id",Integer.toString(teamID));

                Element name = document.createElement("name");
                name.appendChild(document.createTextNode(currTeam.getName()));
                team.appendChild(name);

                Element gamesWon = document.createElement("gameswon");
                gamesWon.appendChild(document.createTextNode(Integer.toString(currTeam.getGamesWon())));
                team.appendChild(gamesWon);

                Element gamesLost = document.createElement("gameslost");
                gamesLost.appendChild(document.createTextNode(Integer.toString(currTeam.getGamesLost())));
                team.appendChild(gamesLost);

                Element gamesDrawn = document.createElement("gamesdrawn");
                gamesDrawn.appendChild(document.createTextNode(Integer.toString(currTeam.getGamesLost())));
                team.appendChild(gamesDrawn);

                Element goalsFor = document.createElement("goalsfor");
                goalsFor.appendChild(document.createTextNode(Integer.toString(currTeam.getGoalsFor())));
                team.appendChild(goalsFor);

                Element goalsAgainst = document.createElement("goalsagainst");
                goalsAgainst.appendChild(document.createTextNode(Integer.toString(currTeam.getGoalsAgainst())));
                team.appendChild(goalsAgainst);

                Element teamPoints = document.createElement("teampoints");
                teamPoints.appendChild(document.createTextNode(Integer.toString(currTeam.getPoints())));
                team.appendChild(teamPoints);

                Element teamPlayers = document.createElement("players");
                team.appendChild(teamPlayers);

                int playerID = 0;
                Element player;

                // Constructs XML format for each player within a team with corresponding data and appends to current team
                for (Player currPlayer : currTeam.getTeamPlayers()){
                    player = document.createElement("player");
                    teamPlayers.appendChild(player);

                    player.setAttribute("id",Integer.toString(playerID));

                    name = document.createElement("name");
                    name.appendChild(document.createTextNode(currPlayer.getName()));
                    player.appendChild(name);

                    gamesWon = document.createElement("gameswon");
                    gamesWon.appendChild(document.createTextNode(Integer.toString(currPlayer.getGamesWon())));
                    player.appendChild(gamesWon);

                    gamesLost = document.createElement("gameslost");
                    gamesLost.appendChild(document.createTextNode(Integer.toString(currPlayer.getGamesLost())));
                    player.appendChild(gamesLost);

                    gamesDrawn = document.createElement("gamesdrawn");
                    gamesDrawn.appendChild(document.createTextNode(Integer.toString(currPlayer.getGamesLost())));
                    player.appendChild(gamesDrawn);

                    goalsFor = document.createElement("goalsfor");
                    goalsFor.appendChild(document.createTextNode(Integer.toString(currPlayer.getGoalsFor())));
                    player.appendChild(goalsFor);

                    playerID++;
                }

                teamID++;
            }

            // creates list of played games
            Element games = document.createElement("games");
            root.appendChild(games);

            Element game;
            int gameID = 0;

            // Constructs XML format for each game within a tournament with corresponding data and appends to
            // current tournament

            for (StaticGame currGame : tournament.getTournamentGameList()){
                game = document.createElement("game");
                games.appendChild(game);

                game.setAttribute("id",Integer.toString(gameID));

                Element name = document.createElement("title");
                name.appendChild(document.createTextNode(currGame.getTitle()));
                game.appendChild(name);

                Element gameDate = document.createElement("date");
                gameDate.appendChild(document.createTextNode(currGame.getGameDate()));
                game.appendChild(gameDate);

                Element gameTime = document.createElement("time");
                gameTime.appendChild(document.createTextNode(currGame.getGameTime()));
                game.appendChild(gameTime);

                Element gamePossession = document.createElement("possession");
                gamePossession.appendChild(document.createTextNode(Float.toString(currGame.getPossession())));
                game.appendChild(gamePossession);

                Element homeTeam = document.createElement("hometeam");
                homeTeam.setAttribute("id",Integer.toString(tournament.getTeamIndex(currGame.getHomeTeamObject())));
                game.appendChild(homeTeam);

                Element homeTeamName = document.createElement("name");
                homeTeamName.appendChild(document.createTextNode(currGame.getHomeTeam()));
                homeTeam.appendChild(homeTeamName);

                Element awayTeam = document.createElement("awayteam");
                awayTeam.setAttribute("id",Integer.toString(tournament.getTeamIndex(currGame.getAwayTeamObject())));
                game.appendChild(awayTeam);


                Element awayTeamName = document.createElement("name");
                awayTeamName.appendChild(document.createTextNode(currGame.getAwayTeam()));
                awayTeam.appendChild(awayTeamName);

                Element homeTeamGoals = document.createElement("goals");
                homeTeamGoals.appendChild(document.createTextNode(Integer.toString(currGame.getHomeTeamGoals())));
                homeTeam.appendChild(homeTeamGoals);

                Element awayTeamGoals = document.createElement("goals");
                awayTeamGoals.appendChild(document.createTextNode(Integer.toString(currGame.getAwayTeamGoals())));
                awayTeam.appendChild(awayTeamGoals);

                Element homeTeamPlayers = document.createElement("players");
                homeTeam.appendChild(homeTeamPlayers);

                Element awayTeamPlayers = document.createElement("players");
                awayTeam.appendChild(awayTeamPlayers);

                Element timeline = document.createElement("timeline");
                game.appendChild(timeline);

                int entryID = 0;
                // Constructs XML format for each entry within the game's timeline with corresponding data and appends
                // to current game
                for (Entry arrayEntry : currGame.getTimeline()) {
                    Element entry = document.createElement("entry");
                    entry.setAttribute("id",Integer.toString(entryID));
                    timeline.appendChild(entry);

                    Element entryTime = document.createElement("time");
                    entryTime.appendChild(document.createTextNode(arrayEntry.getTime()));
                    entry.appendChild(entryTime);

                    Element entryAction = document.createElement("action");
                    entryAction.appendChild(document.createTextNode(arrayEntry.getAction()));
                    entry.appendChild(entryAction);

                    if(arrayEntry.getData().equals("SCR_GOAL")){
                        String[] data = arrayEntry.getData();

                        Element entryData = document.createElement("data");
                        entryData.appendChild(document.createTextNode(data[0]+data[1]));
                        entry.appendChild(entryData);
                    } else {
                        Element entryData = document.createElement("data");
                        entryData.appendChild(document.createTextNode(arrayEntry.getData()[0]));
                        entry.appendChild(entryData);
                    }
                    Element entryOutput = document.createElement("output");
                    entryOutput.appendChild(document.createTextNode(arrayEntry.getOutput()));
                    entry.appendChild(entryOutput);

                    entryID++;
                }


                int playerID = 0;
                // appends home players within the game
                for (String[] playerTuple : currGame.getHomePlayerData()){
                    System.out.println("I exported here");
                    Element player = document.createElement("player");
                    player.setAttribute("id",Integer.toString(playerID));
                    homeTeamPlayers.appendChild(player);

                    Element playerName = document.createElement("name");
                    playerName.appendChild(document.createTextNode(playerTuple[0]));
                    player.appendChild(playerName);

                    Element playerGoals = document.createElement("goals");
                    playerGoals.appendChild(document.createTextNode(playerTuple[1]));
                    player.appendChild(playerGoals);
                    playerID++;
                }

                playerID = 0;
                // appends away players within the game
                for (String[] playerTuple : currGame.getAwayPlayerData()){
                    Element player = document.createElement("player");
                    player.setAttribute("id",Integer.toString(playerID));
                    awayTeamPlayers.appendChild(player);

                    Element playerName = document.createElement("name");
                    playerName.appendChild(document.createTextNode(playerTuple[0]));
                    player.appendChild(playerName);

                    Element playerGoals = document.createElement("goals");
                    playerGoals.appendChild(document.createTextNode(playerTuple[1]));
                    player.appendChild(playerGoals);
                    playerID++;
                }


                gameID++;
            }

            // creates transformer to create corresponding xml code from document instance
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(path));

            transformer.transform(domSource,streamResult);

            // catch errors
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
