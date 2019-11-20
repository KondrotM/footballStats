package uk.ac.glos.ct5025.s1804317.footballStats;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame{

    public UI(String title){ super(title); }

    public void loadGUI(){
        // java - get screen size using the Toolkit class
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


        this.setSize((int) (screenSize.width*.75), (int) (screenSize.height*.75));
        this.setLocation(screenSize.width/2, (screenSize.height/2));
        this.setVisible(true);
    }

    public static void main(String args[]){
        UI main = new UI("Football Stats");
        Menu menu = new Menu();
        menu.loadGUI();
    }

}
