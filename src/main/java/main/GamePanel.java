package main;

import main.Sound;
import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.awt.event.*;
import entity.Entity;
import entity.Player;
import objects.SuperObject;
import src.tile.TileManager;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {
    
    String nname;
    public void setName ( String nname ) {this.nname = nname;}
    
    // SCREEN SETTING
    final int originalTileSize = 16;  //16x16 tile (default size of player character)
    final int scale = 3;
    boolean isPaused = false;
    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //575 pixels
    static boolean setM = true;
    static boolean check;
    //World Setting
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public long startTime;
    
// FPS
    int FPS = 60;

    //System
    TileManager tileM = new TileManager(this);
    main.KeyHandler keyH = new main.KeyHandler(this);
    main.Sound music = new main.Sound();
    main.Sound se = new main.Sound();
    Thread gameThread;
    public main.CollisionChecker cChecker = new main.CollisionChecker(this);
    public main.AssetSetter aSetter = new main.AssetSetter(this);
    public main.UI ui = new main.UI(this);
    public Mode modes;

    //Entity and Objects
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[50];
    public Entity npc[] = new Entity[10];
    
    public Player getPlayer() { return player;}
    public UI getGameUI() { return ui;}

    //Game State
    public int gameState;
    public static float vol;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int optionState = 3;
    public final int gameOverState = 4;
    public int loginState = 0;

    //////////////////////////////////////////////////
    public void setVolume(float volume) {
        if (volume == 0.0f) {
            setM = false;
            music.stop();
            se.stop();
            vol = volume;
        } else {
            setM = true;
            music.setVolume(volume);
            se.setVolume(volume);
            vol = volume;
            music.playLoop();

        }
    }

    public void stopAllSounds() {
        setM = false;
        music.stopAllSounds();
        se.stopAllSounds();

    }

    public void setupVolumeControl(JRadioButton option1, JRadioButton option2,JSlider slider) {

        option1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setM = true;
                setVolume(100);
                slider.setValue(100);
                music.play();
                music.playLoop();
                check = true;
            }
        });

        option2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setM = false;
                check = false;
                setVolume(0);
                slider.setValue(0);
                stopAllSounds();

            }
        });
    }
    //////////////////////////////////////////////////

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(0x15, 0x94, 0x14));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void retry() {
        player.setDefaultPosiotions();
        player.restoreLife();
        player.setDefaultValues("Baby");
        player.resetAnimation();
        aSetter.setNPC();
        aSetter.setObject();
    }

    public void restart() {
        player.setDefaultValues("Baby");
        player.setDefaultPosiotions();
        player.restoreLife();
        player.resetAnimation();
        aSetter.setObject();
    }

    public void setupGame() {

        aSetter.setObject();
        aSetter.setNPC();
        playMusic(0);
        gameState = titleState;
    }
    
    
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    double delta = 0;
    long timer = 0;

    public void run() {

        double drawInterval = 1000000000 / FPS;

        long lastTime = System.nanoTime();
        long currentTime;

        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////
    private List<PauseStateListener> pauseStateListeners = new ArrayList<>();

    public interface PauseStateListener {

        void onPauseStateChanged(boolean isPaused);
    }
    private List<TitleStateListener> titleStateListeners = new ArrayList<>();

    public interface TitleStateListener {

        void TitleStateChanged(boolean isPaused);
    }
////////////////////////////////////////////////////////////////////////////////
    public void update() {

        if (gameState == playState) {
            //Player
            player.update();

            //NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState) {
            notifyPauseStateListeners(true);
        } else {
            notifyPauseStateListeners(false);
        }
        
        if (gameState == titleState) {
            notifyTitleStateListeners(true);
        } else {
            notifyTitleStateListeners(false);
        }

    }

    private void notifyPauseStateListeners(boolean isPaused) {
        for (PauseStateListener listener : pauseStateListeners) {
            listener.onPauseStateChanged(isPaused);
        }
    }
    private void notifyTitleStateListeners(boolean isPaused) {
        for (TitleStateListener listener : titleStateListeners) {
            listener.TitleStateChanged(isPaused);
        }
    }
    /////////////////////////////////////////////////////
    
    private List<GameStateChangeListener> gameStateChangeListeners = new ArrayList<>();

    public interface GameStateChangeListener {
        void onGameStateChanged(int newState);
    }

    public void addGameStateChangeListener(GameStateChangeListener listener) {
        gameStateChangeListeners.add(listener);
    }

    public void setGameState(int newState) {
        if (gameState != newState) {
            gameState = newState;
            notifyGameStateChangeListeners(newState);
        }
    }

    private void notifyGameStateChangeListeners(int newState) {
        for (GameStateChangeListener listener : gameStateChangeListeners) {
            listener.onGameStateChanged(newState);
        }
    }

    public boolean isEnterPressed() {
        if (keyH.enterPressed) {
            keyH.enterPressed = false; // Reset the key state
            return true;
        }
        return false;
    }

    public void addPauseStateListener(PauseStateListener listener) {
        pauseStateListeners.add(listener);
    }
    public void addTitleStateListener(TitleStateListener listener) {
        titleStateListeners.add(listener);
    }

/////////////////////////////////////////////////////
    
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //Debug
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }
        if (gameState == pauseState) {
            ui.draw(g2);
            update();
        }
        //title screen
        if (gameState == titleState) {
            ui.draw(g2);
            update();
        } else {
            //Tile
            tileM.draw(g2);

            //Object
            for (int i = 0; i < obj.length; i++) {

                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }

            //NPC
            for (int i = 0; i < npc.length; i++) {

                if (npc[i] != null) {
                    npc[i].draw(g2, this);
                }
            }
            //Player
            player.draw(g2);

            //UI
            ui.draw(g2);
        }

        //Debug
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }
        
        g2.dispose();
    }

    public void playMusic(int i) {
        if (setM) {
            music.setFile(i);
            music.play();
            music.playLoop();
        }
    }

    public void stopMusic() {music.stop();}

    public void playSE(int i) {
        if (setM) {
            se.setFile(i);
            se.play();
        }
    }

    public void pauseStates() {gameState = pauseState;}
}
