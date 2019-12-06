package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JPanel implements ActionListener {
    private static int sizeX;
    private static int sizeY;

    protected JPanel contentPane;

    public MyWindow(JPanel panel, CardLayoutWindow clw) {
        contentPane = panel;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        sizeX = (int) Math.round(screenSize.width * .75);
        sizeY = (int) Math.round(screenSize.height * .75);
    }

    public static int getSizeX() {
        return sizeX;
    }

    public static int getSizeY() {
        return sizeY;
    }

    protected JComponent createButtonPane(String title, String command) {
        JButton button = new JButton(title);
        button.setPreferredSize(new Dimension(150, 25));
        button.setActionCommand(command);
        button.addActionListener(this);

        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pane.add(button);

        return pane;
    }

    protected void changeWindow(MyWindow window) {


    }






    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
