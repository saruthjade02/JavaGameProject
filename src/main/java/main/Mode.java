package main;

import entity.Entity;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
        
public class Mode extends JFrame implements ActionListener{
    private JComboBox<String> cbox;
    private String mode = "Baby";
    public String getMode(){return mode;}
    private GamePanel gp;
    public Mode(GamePanel gp) {
        this.gp = gp;
        String[] mode = { "Baby", "Easy", "Normal", "Hard", "Nightmare" };
        // Game 5 Min
        
        JFrame NewFrame = new JFrame();
        
        NewFrame.setTitle("Mode Game");
        NewFrame.setBounds(200, 200, 150, 100);
        NewFrame.setResizable(false);
        NewFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        
        NewFrame.setLayout(new BorderLayout());

       
        cbox = new JComboBox<>(mode);
        cbox.addActionListener(this);
        
        cbox.setPreferredSize(new Dimension(150, 100));


        NewFrame.add(cbox, BorderLayout.CENTER);
  
        NewFrame.pack();  
        NewFrame.setVisible(true);
        cbox.addActionListener(e -> {
            NewFrame.dispose();
        });
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == cbox){
            this.mode = cbox.getSelectedItem().toString();
            System.out.println(mode);
            gp.player.setDefaultValues(mode);
//            for (Entity npc : gp.npc) {
//                npc.SetValue(mode);
//            }
        }
    }
}
