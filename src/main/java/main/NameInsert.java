package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class NameInsert extends JDialog implements KeyListener {
    main.GamePanel gp;
    
    public NameInsert(main.GamePanel gp) {
        this.gp = gp;
    }
    
    Graphics2D g2;
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public void drawLoginScreen() {
        JDialog dialog = new JDialog();
        
        dialog.setTitle("Input your name here");
        dialog.setBounds(570, 380, 300, 100);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JTextField textField = new JTextField(15);
        dialog.setLayout(new BorderLayout());
        dialog.add(textField, BorderLayout.CENTER);
        dialog.setVisible(true);

        textField.addActionListener((ActionEvent e) -> {
            String inputText = textField.getText();
            if ((inputText != null) && (inputText.length() > 0)) {
                System.out.println("Welcome " + inputText + " Nice to meet you");
                gp.setName(inputText);
                gp.loginState = 1;
                gp.gameState = gp.playState;
                gp.keyH.enterPressed = false;
                dialog.dispose();
            }
            else {
                System.out.println("Name cannot be empty!");
                dialog.setTitle("Input your name again !!");
            }
        });
    }
}