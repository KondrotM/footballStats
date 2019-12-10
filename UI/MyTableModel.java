package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import uk.ac.glos.ct5025.s1804317.footballStats.Team;
import uk.ac.glos.ct5025.s1804317.footballStats.Tournament;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MyTableModel extends AbstractTableModel {
//    private String[] columnNames = {"First Name",
//            "Last Name",
//            "Sport",
//            "# of Years",
//            "Vegetarian"};
    private String[] columnNames = {"Team Name",
            "GP","W","L","D","GF","GA","GD","Pts"};

    private ArrayList<ArrayList> data(){
        ArrayList<ArrayList> data = new ArrayList<ArrayList>();
        for (int i = 0; i < Tournament.activeTournament.getTournamentTeams().size(); i++){
            Team currTeam = Tournament.activeTournament.getTeam(i);
            ArrayList teamData = new ArrayList();
            teamData.add(currTeam.getName());
            teamData.add(currTeam.getGamesPlayed());
            teamData.add(currTeam.getGamesWon());
            teamData.add(currTeam.getGamesLost());
            teamData.add(currTeam.getGamesDrawn());
            teamData.add(currTeam.getGoalsFor());
            teamData.add(currTeam.getGoalsAgainst());
            teamData.add(currTeam.getGoalsDifference());
            teamData.add(currTeam.getPoints());
            data.add(teamData);
        }
        return data;
    }

//    private Object[][] data = {
//            {"Kathy", "Smith",
//                    "Snowboarding", new Integer(5), new Boolean(false)},
//            {"John", "Doe",
//                    "Rowing", new Integer(3), new Boolean(true)},
//            {"Sue", "Black",
//                    "Knitting", new Integer(2), new Boolean(false)},
//            {"Jane", "White",
//                    "Speed reading", new Integer(20), new Boolean(true)},
//            {"Joe", "Brown",
//                    "Pool", new Integer(10), new Boolean(false)}
//    };

    @Override
    public int getRowCount() {
        return data().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        ArrayList colData = data().get(row);
        Object data = colData.get(col);
        return data;
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return false;
    }
}
