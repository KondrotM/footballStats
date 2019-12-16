package uk.ac.glos.ct5025.s1804317.footballStats.UI.Browse;

import uk.ac.glos.ct5025.s1804317.footballStats.StaticGame;
import uk.ac.glos.ct5025.s1804317.footballStats.Tournament;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.CardLayoutWindow;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.MyWindow;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.PlayGameWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseGamesWindow extends MyWindow implements ActionListener, ListSelectionListener {

    private JComponent browseGamesWindow;
    private JList gamesList;

    public BrowseGamesWindow(JPanel panel, CardLayoutWindow clw) {
        super(panel, clw);
        contentPane = panel;

        browseGamesWindow = factoryBrowseGamesWindow();
        add(browseGamesWindow);
    }

    public JComponent factoryBrowseGamesWindow() {
        JLabel menuLabel = new JLabel("SELECT GAME");

        gamesList = new JList(Tournament.getActiveTournament().getTournamentGamesModel());

        JComponent gamesScrollList = factoryList(gamesList);

        JComponent buttonBack = factoryButtonPane("..", "NAV_BACK");
        JComponent buttonSelectGame = factoryButtonPane("Select", "ACT_SELECT_GAME");
        JComponent buttonPlayGame = factoryButtonPane("PLAY GAME","MKE_PLAY_GAME");

        // Creates GridBagLayout panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Sets the padding
        gbc.insets = new Insets(3, 3, 3, 3);

        // Adds components
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(menuLabel, gbc);

        // Increments Y grid
        gbc.gridy++;
        panel.add(buttonBack, gbc);

        gbc.gridy++;
        panel.add(gamesScrollList, gbc);

        gbc.gridy++;
        panel.add(buttonSelectGame, gbc);

        gbc.gridy++;
        panel.add(buttonPlayGame,gbc);

        return panel;
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        if (command.equals("NAV_BACK")) {
            contentPane.remove(contentPane.getComponents().length-1);
            cardLayout.show(contentPane, "NAV_BROWSE");
        } else if (command.equals("ACT_SELECT_GAME")) {
            StaticGame game = Tournament.getActiveTournament().getGame(gamesList.getSelectedIndex());
            displayGameFrame(game);
        } else if (command.equals("MKE_PLAY_GAME")){
            StaticGame game = Tournament.getActiveTournament().getGame(gamesList.getSelectedIndex());
            PlayGameWindow playGameWindow = new PlayGameWindow(contentPane,CardLayoutWindow.cardLayoutWindow,game,"PLAYBACK");
            playGameWindow.displayGameWindow();
        }
    }
}
