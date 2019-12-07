package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import uk.ac.glos.ct5025.s1804317.footballStats.Tournament;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyWindow extends JPanel implements ActionListener {
    private static int sizeX;
    private static int sizeY;

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

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, command);
    }
}
