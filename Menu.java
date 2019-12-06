package uk.ac.glos.ct5025.s1804317.footballStats;

import javax.swing.*;
import java.awt.*;

public class Menu extends JDialog {

    public JPanel layoutMainMenu(){
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(5,1));
        btnPanel.add(new JLabel("Current Tournament:"));
        btnPanel.add(new JButton("Select Tournament"));
        btnPanel.add(new JButton("Create.."));
        btnPanel.add(new JButton("Browse.."));
        btnPanel.add(new JButton("Start Game"));
        return btnPanel;
    }

    public GridBagLayout tournnamentUI(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5,5,5,5);

        gbc.gridx = 0; gbc.gridy = 0;
        this.add(new JLabel("Current Tournament"),gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        this.add(new JButton("Select Tournamnet"), gbc);

        return new GridBagLayout();
    }

    public void loadGUI(){
        // declared GridBagLayout and its constraints
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // fills both the x and y dimensinons
        gbc.fill = GridBagConstraints.BOTH;
        // insets decide size of margins
        gbc.insets = new Insets(5,5,5,5);
        // weighing decides allocation of extra space
        gbc.weightx = 2; gbc.weighty = 1;

        // selects into which cell components go
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth=GridBagConstraints.RELATIVE;
        this.add(new JLabel("Current Tournament:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.gridwidth=GridBagConstraints.REMAINDER;
        this.add(new JButton("Select Tournament"),gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        this.add(new JButton("Create.."), gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        this.add(new JButton("Browse.."), gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        this.add(new JButton("Start Game"), gbc);

        // java - get screen size using the Toolkit class
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setSize((int) (screenSize.width*.75), (int) (screenSize.height*.75));
        this.setLocation(screenSize.width/8, (screenSize.height/8));
        this.setVisible(true);

    }

}
