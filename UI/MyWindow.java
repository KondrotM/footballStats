package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import uk.ac.glos.ct5025.s1804317.footballStats.Item;
import uk.ac.glos.ct5025.s1804317.footballStats.StaticGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JPanel implements ActionListener {
    private static int sizeX;
    private static int sizeY;
    private static JLabel errorMessage = new JLabel();

    // Last location to allow new windows not to overlap
    private Point lastLocation = null;

    protected RepaintManager repaintManager;
    protected JPanel contentPane;

    public MyWindow(JPanel panel, CardLayoutWindow clw) {
        contentPane = panel;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        sizeX = (int) Math.round(screenSize.width * .75);
        sizeY = (int) Math.round(screenSize.height * .75);
    }

    public JPanel getContentPane(){
        //contentpane is the panel with cardLayout
        return contentPane;
    }

    public static int getSizeX() {
        return sizeX;
    }

    public static JLabel getErrorMessage(){
        return errorMessage;
    }

    public static int getSizeY() {
        return sizeY;
    }

    protected JComponent factoryList(JList list){
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(150,200));
        scrollPane.setViewportView(list);
        return scrollPane;
    }

    protected JTextField factoryTextField(String title){
        JTextField textField = new JTextField();
        new TextPrompt(title, textField, TextPrompt.Show.FOCUS_LOST);
        textField.setPreferredSize(new Dimension(150,25));
        textField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        return textField;
    }

    protected JComponent factoryButtonPane(String title, String command) {
        JButton button = new JButton(title);
        button.setPreferredSize(new Dimension(150, 25));
        button.setActionCommand(command);
        button.addActionListener(this);

        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pane.add(button);

        return pane;
    }

    public void displayGameFrame (StaticGame game){
        JFrame frame = new JFrame(game.getTitle());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Set window location.
        if (lastLocation != null) {
            //Move the window over and down 40 pixels.
            lastLocation.translate(40, 40);
            if ((lastLocation.x > sizeX) || (lastLocation.y > sizeY)) {
                lastLocation.setLocation(0, 0);
            }
            frame.setLocation(lastLocation);
        } else {
            lastLocation = frame.getLocation();
        }

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(3,3,3,3);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        gbc.gridx = 2; gbc.gridy = 0;
        panel.add(new JLabel(game.getHomeTeamGoals() + "  " + game.getHomeTeam() + " vs " + game.getAwayTeam() + "  " + game.getAwayTeamGoals()),gbc);
        gbc.gridx = 0;

        ////////// Add Home Players

        gbc.gridy++;
        panel.add(new JLabel(game.getHomeTeam()),gbc);

        gbc.gridy++;
        panel.add(new JLabel("Player name"),gbc);

        gbc.gridx++;
        panel.add(new JLabel("Goals"),gbc);

        gbc.gridy--;
        panel.add(new JLabel(String.valueOf(Math.round(game.getPossession()*100))+ "%"),gbc);
        gbc.gridy++;

        gbc.gridx--;

        gbc.gridy++;

        for (int i=0; i < game.getHomePlayerData().size(); i++){
            String[] player = game.getHomePlayerData().get(i);
            panel.add(new JLabel(player[0]),gbc);
            gbc.gridx++;
            panel.add(new JLabel(player[1]),gbc);
            gbc.gridx--;
            gbc.gridy++;
        }

        ////////// Add Away Players

        gbc.gridy = 1;

        gbc.gridx = 5;

        panel.add(new JLabel(game.getAwayTeam()),gbc);

        gbc.gridy++;
        panel.add(new JLabel("Player Name"),gbc);

        gbc.gridx--;
        panel.add(new JLabel("Goals"),gbc);

        gbc.gridy--;
        panel.add(new JLabel(String.valueOf(Math.round((1 - game.getPossession())*100)) + "%"),gbc);
        gbc.gridy++;

        gbc.gridy++;


        for (int i=0; i < game.getAwayPlayerData().size(); i++){
            String[] player = game.getAwayPlayerData().get(i);
            panel.add(new JLabel(player[1]),gbc);
            gbc.gridx++;
            panel.add(new JLabel(player[0]),gbc);
            gbc.gridx--;
            gbc.gridy++;
        }

        ////////// Add Game Time and Details
        gbc.gridy = 1; gbc.gridx = 2;

        panel.add(new JLabel(game.getGameDate(),SwingConstants.CENTER),gbc);
        gbc.gridy++;
        panel.add(new JLabel(game.getGameTime(),SwingConstants.CENTER),gbc);

        ////////// Add Contents

        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.anchor = GridBagConstraints.FIRST_LINE_START;

        frame.add(panel,gbc2);


        frame.pack();
//        frame.setSize(new Dimension(350,200));
        frame.setVisible(true);
    }

    public void displayFrame (Item item){
        JFrame frame = new JFrame(item.getName());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Set window location.
        if (lastLocation != null) {
            //Move the window over and down 40 pixels.
            lastLocation.translate(40, 40);
            if ((lastLocation.x > sizeX) || (lastLocation.y > sizeY)) {
                lastLocation.setLocation(0, 0);
            }
            frame.setLocation(lastLocation);
        } else {
            lastLocation = frame.getLocation();
        }

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(3,3,3,3);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel(item.getName()),gbc);

        gbc.gridx++;
        panel.add(new JLabel("Games Won: " + item.getGamesWon()),gbc);

        gbc.gridy++;
        panel.add(new JLabel("Games Lost: " + item.getGamesLost()),gbc);

        gbc.gridy++;
        panel.add(new JLabel("Games Drawn: " + item.getGamesDrawn()),gbc);

        gbc.gridy++;
        panel.add(new JLabel("Goals Scored: " + item.getGoalsFor()),gbc);


        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.anchor = GridBagConstraints.FIRST_LINE_START;

        frame.add(panel,gbc2);


        frame.pack();
//        frame.setSize(new Dimension(350,200));
        frame.setVisible(true);

    }

    //    @Override is not allowed when implementing interface method
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, command);
    }
}
