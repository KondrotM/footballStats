package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import uk.ac.glos.ct5025.s1804317.footballStats.Tournament;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SelectTournamentWindow extends MyWindow implements ActionListener, ListSelectionListener {

    private JComponent selectTournamentWindow;
    private JList tournamentList;

    // Static variables are referenced when creating new tournament
    private static JLabel currTournament = new JLabel("ACTIVE TOURNAMENT: NONE");
    private static DefaultListModel tournamentModel = new DefaultListModel();


    public SelectTournamentWindow(JPanel panel, CardLayoutWindow clw) {
        // Panel and Window inherited
        super(panel, clw);
        contentPane = panel;
        setOpaque(true);

        selectTournamentWindow = factorySelectTournamentWindow();
        add(selectTournamentWindow);
    }

    public static JLabel getCurrTournament(){
        return currTournament;
    }

    public static DefaultListModel getTournamentModel(){
        return tournamentModel;
    }



    public JComponent factorySelectTournamentWindow(){
        JLabel menuLabel = new JLabel("SELECT TOURNAMENT");

        // Initialises tournamentList with tournamentModel
        tournamentList = new JList(tournamentModel);

        // Makes tournamentList scrollable
        JComponent tournamentScrollList = factoryList(tournamentList);

        // Creates buttons within panes
        JComponent buttonBack = factoryButtonPane("..","NAV_MAIN");
        JComponent buttonSelectTournament = factoryButtonPane("Select","ACT_SELECT_TOURNAMENT");
        JComponent buttonExportTournament = factoryButtonPane("Export Tournament","ACT_EXPORT_TOURNAMENT");
        JComponent buttonImportTournament = factoryButtonPane("Import Tournament", "ACT_IMPORT_TOURNAMENT");


        // Creates GridBagLayout panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Sets the padding
        gbc.insets = new Insets(3,3,3,3);

        // Adds components
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(menuLabel,gbc);

        // Increments Y grid
        gbc.gridy++;
        panel.add(buttonBack,gbc);

        gbc.gridy++;
        panel.add(currTournament,gbc);

        gbc.gridy++;
        panel.add(tournamentScrollList,gbc);

        gbc.gridy++;
        panel.add(buttonSelectTournament,gbc);

        gbc.gridy++;
        panel.add(buttonExportTournament,gbc);

        gbc.gridy++;
        panel.add(buttonImportTournament,gbc);

        return panel;
    }

    //    @Override is not allowed when implementing interface method
    public void valueChanged(ListSelectionEvent listSelectionEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.contains("NAV_")){
            // Navigates to requested page
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, command);
        } else if (command.equals("ACT_SELECT_TOURNAMENT")){
            // Calls static function to set the selected tournament as active
            Tournament.selectTournament(tournamentList.getSelectedIndex());
        } else if (command.equals("ACT_EXPORT_TOURNAMENT")){
            Tournament tournament = Tournament.getTournamentList().get(tournamentList.getSelectedIndex());
            Tournament.exportTournament(tournament);
        } else if (command.equals("ACT_IMPORT_TOURNAMENT")){

            // Initialises a new file chooser
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
            jfc.setDialogTitle("CHOOSE TOURNAMENT");

            // Only lets the user select XML files
            jfc.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files","xml");
            jfc.addChoosableFileFilter(filter);

            int returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
               File selectedFile = jfc.getSelectedFile();
               Tournament.importTournament(selectedFile);
            }
        }

    }
}
