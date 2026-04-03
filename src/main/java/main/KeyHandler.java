package main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class KeyHandler implements KeyListener {

    main.GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed = false;
    Mode pframe;
    NameInsert qframe;

    //Debug
    boolean checkDrawTime = false;
    public KeyHandler(main.GamePanel gp) {this.gp = gp;}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {gp.ui.commandNum = 3;}
            }
            
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {gp.ui.commandNum = 0;}
            }
            
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
                if (gp.ui.commandNum == 1) {pframe = new Mode(gp);}
                if (gp.ui.commandNum == 2) {System.exit(0);}
                
            }
        }
        
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {upPressed = true;}
            if (code == KeyEvent.VK_S) {downPressed = true;}
            if (code == KeyEvent.VK_A) {leftPressed = true;}
            if (code == KeyEvent.VK_D) {rightPressed = true;}
        }

       if (code == KeyEvent.VK_ESCAPE)
       {
           if(gp.gameState == gp.playState){
               gp.gameState = gp.pauseState;
               gp.isPaused = true;
            }
           else if(gp.gameState == gp.pauseState){
               gp.gameState = gp.playState;
               gp.isPaused = false;
           }
       }
       
    // Debug
    if (code == KeyEvent.VK_T) {
        checkDrawTime = !checkDrawTime;  // toggle checkDrawTime
    }
        if (gp.gameState == gp.gameOverState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.retry();
                    gp.startTime = System.nanoTime();              
                }
                if (gp.ui.commandNum == 1) {
                    gp.gameState = gp.titleState;
                    gp.restart();
                    gp.startTime = System.nanoTime();
                }
            }
        }
}

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {upPressed = false;}
        if (code == KeyEvent.VK_S) {downPressed = false;}
        if (code == KeyEvent.VK_A) {leftPressed = false;}
        if (code == KeyEvent.VK_D) {rightPressed = false;}
    }
    
    // Fix Here //////////////////////////////////////////////////////////////////////////////////////
    // Create new Class Here
    private void showCreditsFrame() {
    JFrame creditsFrame = new JFrame("Credits");
    creditsFrame.setSize(400, 300);
    creditsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
    creditsFrame.setLayout(new BorderLayout());

   
    JPanel creditsPanel = new JPanel();
    creditsPanel.setLayout(new GridLayout(5, 1, 10, 10));
    creditsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    JLabel titleLabel = new JLabel("Credits", JLabel.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
    creditsPanel.add(titleLabel);

    JLabel name1 = new JLabel("Punyabhorn Komenratanakul 6613121", JLabel.CENTER);
    JLabel name2 = new JLabel("Natthapan ToonKong 6613181", JLabel.CENTER);
    JLabel name3 = new JLabel("Khunanont Tiyanunt 6613249", JLabel.CENTER);
    JLabel name4 = new JLabel("Saruth Latthitham 6613272", JLabel.CENTER);

   
    creditsPanel.add(name1);
    creditsPanel.add(name2);
    creditsPanel.add(name3);
    creditsPanel.add(name4);

    creditsFrame.add(creditsPanel, BorderLayout.CENTER);

    
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(e -> creditsFrame.dispose());
    creditsFrame.add(closeButton, BorderLayout.SOUTH);

    creditsFrame.setLocationRelativeTo(null);
    creditsFrame.setVisible(true);

    }
    // to Here //////////////////////////////////////////////////////////////////////////////////////
}
