package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CharacterSelection implements ActionListener{
    private JPanel radioPanel;
    private JRadioButton radio1, radio2, radio3;
    private GamePanel gp;

    public CharacterSelection(GamePanel gp) {
        this.gp = gp;
        radioPanel = new JPanel();
        radioPanel.setLayout(null);

        radio1 = new JRadioButton("Boy Thief");
        radio2 = new JRadioButton("Girl Thief");
        radio3 = new JRadioButton("Kitty-Woman");

        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        group.add(radio3);

        radio1.setBounds(210, 310, 100, 30);
        radio2.setBounds(335, 310, 100, 30);
        radio3.setBounds(460, 310, 100, 30);

        radioPanel.add(radio1);
        radioPanel.add(radio2);
        radioPanel.add(radio3);

        gp.addGameStateChangeListener(newState -> {
            boolean isTitle = newState == gp.titleState;
            radio1.setVisible(isTitle);
            radio2.setVisible(isTitle);
            radio3.setVisible(isTitle);
        });
    }

    public JPanel getRadioButtons() {
        return radioPanel;
    }
    
    public void actionPerformed(ActionEvent e){
        if (radio1.isSelected()) { // in case Boy Thief got selected
            System.out.println("Boy Thief selected");
            gp.getPlayer().getPlayerImage(0);
            gp.requestFocusInWindow();
            System.out.printf("flag = %d", gp.getPlayer().getFlag());
        }
        if (radio2.isSelected()) { // in case Girl Thief got selected
            System.out.println("Girl Thief selected");
            gp.getPlayer().getPlayerImage(1);
            gp.requestFocusInWindow();
            System.out.printf("flag = %d", gp.getPlayer().getFlag());
        }
         if (radio3.isSelected()) { // in case Kitty-woman got selected
            System.out.println("Kitty-Woman selected");
            gp.getPlayer().getPlayerImage(2);
            gp.requestFocusInWindow();
            System.out.printf("flag = %d", gp.getPlayer().getFlag());
        }
    }
}