package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import uk.ac.glos.ct5025.s1804317.footballStats.Team;
import uk.ac.glos.ct5025.s1804317.footballStats.Tournament;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MyTableModel extends AbstractTableModel {

    // Creates a String Array of column names
    private String[] columnNames = {"Team Name",
            "GP","W","L","D","GF","GA","GD","Pts"};

    // Creates a multidimensional array, each containing a team's data.
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

    //    @Override is not allowed when implementing interface method
    public int getRowCount() {
        return data().size();
    }

    //    @Override is not allowed when implementing interface method
    public int getColumnCount() {
        return columnNames.length;
    }

    // Gets value at specified cell co-ordinates
    //    @Override is not allowed when implementing interface method
    public Object getValueAt(int row, int col) {
        // Gets column
        ArrayList colData = data().get(row);
        // Gets data
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

    // Denies cells being editable
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
