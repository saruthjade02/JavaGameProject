/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author acer
 */
public class Game {

    public Game() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game");

        main.GamePanel gamePanel = new main.GamePanel();
        UI ui = new UI(gamePanel);

        /////////////////////////////////////////////
        ////////////////////////////////
        //JSlider
        ////////////////////////////////////////////////
        JLayeredPane layeredPane = new JLayeredPane();

        layeredPane.setPreferredSize(new Dimension(gamePanel.screenWidth, gamePanel.screenHeight));
        gamePanel.setBounds(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        layeredPane.add(gamePanel, JLayeredPane.DEFAULT_LAYER);
        JSlider volumeSlider = new JSlider(0, 100, 70);

        volumeSlider.addChangeListener(e
                -> {
            if (!volumeSlider.getValueIsAdjusting()) {
                int volume = volumeSlider.getValue();
                float volumeLevel = volume / 100.0f;

                if (volumeLevel == 0.0f) {

                    gamePanel.setM = false;
                } else {
                    gamePanel.setM = true;
                }
                gamePanel.setVolume(volumeLevel);

            }
        });

        volumeSlider.setBounds(350, 170, 170, 25);
        volumeSlider.setVisible(false);

        layeredPane.add(volumeSlider, JLayeredPane.PALETTE_LAYER);
        gamePanel.addPauseStateListener(isPaused -> {
            volumeSlider.setVisible(isPaused);
        });

        window.add(layeredPane);
        /////////////////////////////////////////////
       JButton credit = new JButton("Credit");
credit.setVisible(true);
credit.setBounds(335, 500, 100, 30);
credit.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
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
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creditsFrame.dispose();
                gamePanel.requestFocusInWindow();  // ให้โฟกัสกลับไปที่ gamePanel
            }
        });
        creditsFrame.add(closeButton, BorderLayout.SOUTH);

        creditsFrame.setLocationRelativeTo(null);
        creditsFrame.setVisible(true);

        creditsFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                gamePanel.requestFocusInWindow(); 
            }
        });
    }
});

gamePanel.addTitleStateListener(isPaused -> {
    credit.setVisible(isPaused);
    if (isPaused) {
        gamePanel.setFocusable(false); 
    } else {
        gamePanel.setFocusable(true); 
        gamePanel.requestFocusInWindow();  
    }
});
        /////////////////////////////////////////////
        // Character skin
        JRadioButton radio1 = new JRadioButton("Boy Thief");
        JRadioButton radio2 = new JRadioButton("Girl Thief");
        JRadioButton radio3 = new JRadioButton("Kitty-Woman");
        ButtonGroup group1 = new ButtonGroup(); // group the radio button

        // Initial visibility based on title screen
        radio1.setVisible(true);
        radio2.setVisible(true);
        radio3.setVisible(true);
        radio1.setSelected(true);
        // Add a listener for START button or game state change
        gamePanel.addGameStateChangeListener(newState -> {
            if (newState == gamePanel.titleState) {
                radio1.setVisible(true);
                radio2.setVisible(true);
                radio3.setVisible(true);
            } else {
                radio1.setVisible(false);
                radio2.setVisible(false);
                radio3.setVisible(false);
            }

        });

        group1.add(radio1); // group to make only one option can be selected at a time.
        group1.add(radio2);
        group1.add(radio3);
        radio1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (radio1.isSelected()) { // in case Boy Thief got selected
                    System.out.println("Boy Thief selected");
                    gamePanel.getPlayer().getPlayerImage(0);
                    gamePanel.requestFocusInWindow();
                    System.out.printf("flag = %d", gamePanel.getPlayer().getFlag());
                }
            }
        });

        radio2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (radio2.isSelected()) { // in case Girl Thief got selected
                    System.out.println("Girl Thief selected");
                    gamePanel.getPlayer().getPlayerImage(1);
                    gamePanel.requestFocusInWindow();
                    System.out.printf("flag = %d", gamePanel.getPlayer().getFlag());
                }
            }
        });
        radio3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (radio3.isSelected()) { // in case Kitty-woman got selected
                    System.out.println("Kitty-Woman selected");
                    gamePanel.getPlayer().getPlayerImage(2);
                    gamePanel.requestFocusInWindow();
                    System.out.printf("flag = %d", gamePanel.getPlayer().getFlag());
                }
            }
        });
        radio1.setBounds(210, 310, 100, 30);
        radio2.setBounds(335, 310, 100, 30);
        radio3.setBounds(460, 310, 100, 30);

        gamePanel.addTitleStateListener(isPaused -> {

            radio1.setVisible(isPaused);
            radio2.setVisible(isPaused);
            radio3.setVisible(isPaused);
        });
        layeredPane.add(radio1, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(radio2, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(radio3, JLayeredPane.PALETTE_LAYER);

        /////////////////////////////////////////////
        //JRadioButton
        JRadioButton option1 = new JRadioButton("ON");
        JRadioButton option2 = new JRadioButton("OFF");

        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);

        option1.setBounds(350, 120, 75, 30);
        option2.setBounds(450, 120, 75, 30);

        option1.setVisible(false);
        option2.setVisible(false);

        option1.setSelected(true);

        gamePanel.setupVolumeControl(option1, option2, volumeSlider);

        layeredPane.add(option1, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(option2, JLayeredPane.PALETTE_LAYER);

        gamePanel.addPauseStateListener(isPaused -> {

            option1.setVisible(isPaused);
            option2.setVisible(isPaused);
        });

        ////////////////////////////////////////////
        //Back
        JButton back = new JButton("Click");

        back.setBounds(400, 215, 75, 30);
        back.setVisible(false);

        layeredPane.add(back, JLayeredPane.PALETTE_LAYER);

        back.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

                gamePanel.gameState = gamePanel.playState;
                gamePanel.isPaused = false;
                gamePanel.repaint();

            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });

        gamePanel.addPauseStateListener(isPaused -> {

            back.setVisible(isPaused);

        });
        /////////////////////////////////////////////////
        //Quit
        JButton quit = new JButton("Click");

        quit.setBounds(400, 310, 75, 30);
        quit.setVisible(false);

        layeredPane.add(quit, JLayeredPane.PALETTE_LAYER);

        quit.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });

        gamePanel.addPauseStateListener(isPaused -> {

            quit.setVisible(isPaused);

        });
        /////////////////////////////////////////////////
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
