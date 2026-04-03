package main;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import objects.OBJ_Heart;
import objects.OBJ_Key;
import objects.OBJ_Coin;
import objects.SuperObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import main.NameInsert;
import main.KeyHandler;

public class UI {
    private main.GamePanel gp;
    private Graphics2D g2;
    private Font arial_40, arial_100B;
    private BufferedImage keyImage;
    private BufferedImage coinImage;
    private BufferedImage full_heart, half_heart, heartless;
    
    public boolean messageOn = false;
    public String message = "";
    public int commandNum = 0;
    
    int messageCounter = 0;
    public boolean gameFinish = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    int subState = 0;
    int shapeSize = 50;
    private long winTimeScreen = 0; 
    private boolean winTimeStart = false; 

    private JLayeredPane layeredPane;
    private CharacterSelection characterSelection;
    
    public JLayeredPane getLayeredPane() {return layeredPane;}
    
    public UI(main.GamePanel gp) {
        this.gp = gp;
//        
//        layeredPane = new JLayeredPane();
//        layeredPane.setPreferredSize(new Dimension(gp.screenWidth,gp.screenHeight));
//        gp.setBounds(0, 0, gp.screenWidth, gp.screenHeight);
//        layeredPane.add(gp, JLayeredPane.DEFAULT_LAYER);
//        
//        characterSelection = new CharacterSelection(gp);
//        layeredPane.add(characterSelection.getRadioButtons(), JLayeredPane.PALETTE_LAYER);
//        
        arial_40 = new Font("Serif", Font.PLAIN, 40);
        arial_100B = new Font("Serif", Font.BOLD, 100);

        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
        
        OBJ_Coin coin = new OBJ_Coin(gp);
        coinImage = coin.image;
        
        SuperObject heart = new OBJ_Heart(gp);
        full_heart = heart.image;
        half_heart = heart.image2;
        heartless = heart.image3;

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    //Game UI Manager
    public void draw(Graphics2D g2) {
        if (gp.gameState == gp.gameOverState) {
            playTime = 0;
        }
        if (gameFinish) {
            //Game Win 
            if(!winTimeStart) {
            winTimeScreen = System.currentTimeMillis();
            winTimeStart = true;
            }
            
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found a treasure chest!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 2);
            g2.drawString(text, x, y);
            
            text = "Good Job! " + gp.nname;
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize );
            g2.drawString(text, x, y);

            text = "Your time is: " + dFormat.format(playTime);
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 4);
            g2.drawString(text, x, y);

            g2.setFont(arial_100B);
            g2.setColor(Color.GREEN);
            text = "You Win!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 2);
            g2.drawString(text, x, y);
            
            if (System.currentTimeMillis() - winTimeScreen >= 8000) { // 5 seconds delay
            System.exit(0); // Close the game
        }
   
        } else if (gp.gameState != 0) {
            
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 65);
            
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(coinImage, gp.tileSize * 3, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasCoin, 200, 65);

            // Playtime
            if (!gp.isPaused) {
                playTime += (double) 1 / 60;
            }
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, 65);

            // Message
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(25F));
                g2.drawString(message, 330, 260);

                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }

        this.g2 = g2;
        // Title state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if (gp.gameState == gp.playState) {
            // Do playState stuff
            drawPlayerLife();
            drawBackground();
        }
        if (gp.gameState == gp.pauseState) {
            drawOptionsScreen();
        }
        if (gp.gameState == gp.gameOverState) {
            drawgameOverScreen();
        }
    }

    //Gaem
    public void drawgameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;

        Font font1 = new Font("Serif", Font.BOLD, 110);
        g2.setFont(font1);
        text = "Game Over";
        g2.setColor(Color.BLACK);
        x = getXforCenterText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);

        Font font2 = new Font("Serif", Font.BOLD, 50);
        g2.setFont(font2);
        text = "Retry";
        x = getXforCenterText(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x - 4, y - 4);
        if (commandNum == 0) {
            g2.drawString(">", x - 40, y - 4);
        }
        Font font3 = new Font("Serif", Font.BOLD, 50);
        g2.setFont(font3);
        text = "Quit";
        x = getXforCenterText(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x - 4, y - 4);
        if (commandNum == 1) {
            g2.drawString(">", x - 40, y - 4);
        }

    }
    public void drawBackground() {
        gp.setBackground(new Color(0x15, 0x94, 0x14));
    }
    public void drawPlayerLife() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize * 2;
        int i = 0;

        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heartless, x, y, null);
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize / 2;
        y = gp.tileSize * 2;
        i = 0;

        int life = gp.player.life;
        
        while (i < gp.player.maxLife / 2) {
            if (life >= 2) {
                g2.drawImage(full_heart, x, y, null);
                life -= 2;
            } else if (life == 1) {
                g2.drawImage(half_heart, x, y, null);
                life -= 1;
            }
            i++;
            x += gp.tileSize;
        }

    }
    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 120F));
        String text = "PAUSE";
        int x = getXforCenterText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }
    public int getXforCenterText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    
    //Option
    public void drawOptionsScreen() {
        if (gp.gameState == gp.pauseState) {
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(32F));

            int frameX = gp.tileSize * 4;
            int frameY = gp.tileSize;
            int frameWidth = gp.tileSize * 8;
            int frameHeight = gp.tileSize * 7;
            drawSubWindow(frameX, frameY, frameWidth, frameHeight);

            switch (subState) {
                case 0:
                    options_top(frameX, frameY);
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
        }
    }
    public void options_top(int frameX, int frameY) {
        int textX;
        int textY;

        String text = "Options";
        textX = getXforCenterText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;

        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
//        music();
        textY += gp.tileSize * 2;
        g2.drawString("Back", textX, textY);

        textY += frameY + gp.tileSize;
        g2.drawString("Quit", textX, textY);
    }
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
    
    //Title
    public void drawTitleScreen() {
        // Title name
        Font customFont = new Font("Serif", Font.PLAIN, 32);
        customFont = customFont.deriveFont(80f);
        g2.setFont(customFont);
        String text = "ThiefVenture";
        int x = getXforCenterText(text);
        int y = gp.tileSize * 3;
        gp.setBackground(new Color(68, 75, 130));
        // Main color
        g2.setColor(Color.gray);
        g2.drawString(text, x, y);
        // Shadow
        g2.setColor(Color.white);
        g2.drawString(text, x + 5, y + 5);
        // Image of selected skin
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.stand, x - 30, y - 60, gp.tileSize * 3, gp.tileSize * 3, null);
        // Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "START";
        x = getXforCenterText(text);
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
            if (gp.keyH.enterPressed) { // Detect when "START" is selected
                if (gp.loginState == 0) {
                    NameInsert login = new NameInsert(gp);
                    login.drawLoginScreen();
                    gp.keyH.enterPressed = false; // Reset enter key
                }
                else if (gp.loginState == 1) {
                    gp.gameState = gp.playState; // Change game state to play
                    gp.keyH.enterPressed = false; // Reset enter key
                }
        }
    }
        text = "MODE";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
            if (gp.isEnterPressed()) {
                // Handle MODE selection (example: switch to a mode configuration screen)
                System.out.println("MODE selected");
            }
        }
        
        text = "QUIT";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) { // QUIT is selected
            g2.drawString(">", x - gp.tileSize, y);
            if (gp.isEnterPressed()) {
                System.exit(0); // Exit the game
            }
        }
        
//        text = "CREDIT";
//        x = getXforCenterText(text);
//        y += gp.tileSize;
//        g2.drawString(text, x, y);
//        if (commandNum == 3) {
//            g2.drawString(">", x - gp.tileSize, y);
//        }
    }
}
