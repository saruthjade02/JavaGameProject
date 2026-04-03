package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import objects.OBJ_Boots;

import javax.imageio.ImageIO;
import javax.management.ObjectName;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import main.Mode;

public class Player extends entity.Entity {
    Mode mode;
    int flags = 0;
    int count = 0;
    int hitdamage = 0;
    public void setFlag(int s) { flags = s; }
    public int getFlag() { return flags; }

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int hasKey = 0;
    public int hasCoin = 0;
    public int needCoin = 20;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 6;
        solidArea.y = 14;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 28;
        solidArea.height = 28;

        setDefaultValues("Baby");
        getPlayerImage(0);
    }
    public void setDefaultValues(String mode) {
        switch(mode){
            case "Baby":
                maxLife = 20;
                hitdamage = 1;
                break;
            case "Easy":
                maxLife = 18;
                hitdamage = 2;
                break;
            case "Normal":
                maxLife = 14;
                hitdamage = 4;
                break;
            case "Hard":
                maxLife = 12;
                hitdamage = 6;
                break;
            case "Nightmare":
                maxLife = 8;
                hitdamage = 8;
                break;
        }
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
        hasKey = 0;
        hasCoin = 0;
        //Player Status
        life = maxLife;
    }
    public void getPlayerImage(int flags) {
        this.flags = flags;
        switch(flags) {
        case 0: stand = setup("player/player_stand");
                up1 = setup("player/player_up_1");
                up2 = setup("player/player_up_2");
                down1 = setup("player/player_down_1");
                down2 = setup("player/player_down_2");
                left1 = setup("player/player_left_1");
                left2 = setup("player/player_left_2");
                left3 = setup("player/player_left_3");
                right1 = setup("player/player_right_1");
                right2 = setup("player/player_right_2");
                right3 = setup("player/player_right_3"); break;
        case 1: stand = setup("player/Female_stand");
                up1 = setup("player/Female_up_1");
                up2 = setup("player/Female_up_2");
                down1 = setup("player/Female_down_1");
                down2 = setup("player/Female_down_2");
                left1 = setup("player/Female_left_1");
                left2 = setup("player/Female_left_2");
                left3 = setup("player/Female_left_3");
                right1 = setup("player/Female_right_1");
                right2 = setup("player/Female_right_2");
                right3 = setup("player/Female_right_3"); break;
        case 2: stand = setup("player/Cat_stand");
                up1 = setup("player/Cat_up_1");
                up2 = setup("player/Cat_up_2");
                down1 = setup("player/Cat_down_1");
                down2 = setup("player/Cat_down_2");
                left1 = setup("player/Cat_left_1");
                left2 = setup("player/Cat_left_2");
                left3 = setup("player/Cat_left_3");
                right1 = setup("player/Cat_right_1");
                right2 = setup("player/Cat_right_2");
                right3 = setup("player/Cat_right_3"); break;
        }
    }
    
    public void resetAnimation() {
        speed = 4;
        
        switch(this.flags) {
        case 0: stand = setup("player/player_stand");
                up1 = setup("player/player_up_1");
                up2 = setup("player/player_up_2");
                down1 = setup("player/player_down_1");
                down2 = setup("player/player_down_2");
                left1 = setup("player/player_left_1");
                left2 = setup("player/player_left_2");
                left3 = setup("player/player_left_3");
                right1 = setup("player/player_right_1");
                right2 = setup("player/player_right_2");
                right3 = setup("player/player_right_3"); break;
        case 1: stand = setup("player/Female_stand");
                up1 = setup("player/Female_up_1");
                up2 = setup("player/Female_up_2");
                down1 = setup("player/Female_down_1");
                down2 = setup("player/Female_down_2");
                left1 = setup("player/Female_left_1");
                left2 = setup("player/Female_left_2");
                left3 = setup("player/Female_left_3");
                right1 = setup("player/Female_right_1");
                right2 = setup("player/Female_right_2");
                right3 = setup("player/Female_right_3"); break;
        case 2: stand = setup("player/Cat_stand");
                up1 = setup("player/Cat_up_1");
                up2 = setup("player/Cat_up_2");
                down1 = setup("player/Cat_down_1");
                down2 = setup("player/Cat_down_2");
                left1 = setup("player/Cat_left_1");
                left2 = setup("player/Cat_left_2");
                left3 = setup("player/Cat_left_3");
                right1 = setup("player/Cat_right_1");
                right2 = setup("player/Cat_right_2");
                right3 = setup("player/Cat_right_3"); break;
        }
}
    
    public void setDefaultPosiotions()
    {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        direction = "down";
    }
    public void restoreLife()
    {
       life = maxLife;
    }
    public void update() {

        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {

            if(keyH.upPressed == true) {
                direction = "up";
            }
            else if(keyH.downPressed == true) {
                direction = "down";
            }
            else if(keyH.leftPressed == true) {
                direction = "left";
            }
            else if(keyH.rightPressed == true) {
                direction = "right";
            }

            //Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //Check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //If collision is false, player can move through
            if(collisionOn == false) {

                switch(direction) {
                    case "up":
                        worldY = worldY - speed;
                        break;
                    case "down":
                        worldY = worldY + speed;
                        break;
                    case "left":
                        worldX = worldX - speed;
                        break;
                    case "right":
                        worldX = worldX + speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 15) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        if(life <=0)
        {
            gp.gameState = gp.gameOverState;
        }
    }
    public void pickUpObject(int i) {

        if(i != 999) {

            String objectName = gp.obj[i].name;

            switch(objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You found a key!");
                    System.out.println("You got a key!");
                    break;
                case "Coin":
                    gp.playSE(8);
                    hasCoin++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a coin!");
                    System.out.println("You got a coin!");
                    break;
                case "Bank":
                    gp.playSE(8);
                    hasCoin += 2;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a bill!");
                    System.out.println("You got a bill!");
                    break;   
                case "Gold":
                    gp.playSE(8);
                    hasCoin += 3;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a gold!");
                    System.out.println("You got a gold!");
                    break;      
                case "Diamond":
                    gp.playSE(8);
                    hasCoin += 4;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a diamond!");
                    System.out.println("You got a diamond!");
                    break;      
                case "Door":
                    gp.playSE(2);
                    if(hasCoin >= needCoin) {
                        gp.obj[i] = null;
                        hasCoin -= needCoin;
                        gp.ui.showMessage("The door is unlocked!");
                        System.out.println("Coin left: " + hasKey);
                    }
                    else { gp.ui.showMessage("You need " + (needCoin - hasCoin) + " more Coin!"); }
                    break;
                case "Chest":
                    gp.playSE(4);
                    if(hasKey > 0) {
                        gp.playSE(5);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.gameFinish = true;
                        gp.stopMusic();
                        System.out.println("Key left: " + hasKey);
                    }
                    break;
                case "Boots":
                    gp.playSE(3);
                    speed += 2;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Speed up!");
                    switch(this.flags) {
                        case 0 :up1 = setup("player/player_run_up_1");
                                up2 = setup("player/player_run_up_2");
                                down1 = setup("player/player_run_down_1");
                                down2 = setup("player/player_run_down_2");
                                left1 = setup("player/player_run_left_1");
                                left2 = setup("player/player_run_left_2");
                                left3 = setup("player/player_run_left_3");
                                right1 = setup("player/player_run_right_1");
                                right2 = setup("player/player_run_right_2");
                                right3 = setup("player/player_run_right_3"); break;
                        case 1 :up1 = setup("player/Female_run_up_1");
                                up2 = setup("player/Female_run_up_2");
                                down1 = setup("player/Female_run_down_1");
                                down2 = setup("player/Female_run_down_2");
                                left1 = setup("player/Female_run_left_1");
                                left2 = setup("player/Female_run_left_2");
                                left3 = setup("player/Female_run_left_3");
                                right1 = setup("player/Female_run_right_1");
                                right2 = setup("player/Female_run_right_2");
                                right3 = setup("player/Female_run_right_3"); break;
                        case 2 :up1 = setup("player/Cat_run_up_1");
                                up2 = setup("player/Cat_run_up_2");
                                down1 = setup("player/Cat_run_down_1");
                                down2 = setup("player/Cat_run_down_2");
                                left1 = setup("player/Cat_run_left_1");
                                left2 = setup("player/Cat_run_left_2");
                                left3 = setup("player/Cat_run_left_3");
                                right1 = setup("player/Cat_run_right_1");
                                right2 = setup("player/Cat_run_right_2");
                                right3 = setup("player/Cat_run_right_3"); break;
                        
                    } break;
                case "Handcuff":
                    gp.playSE(6);
                    gp.obj[i] = null;
                    gp.ui.showMessage("You get caught!!");
                    life -= 1;
                    break;
                case "Bomb":
                    gp.playSE(7);
                    gp.obj[i] = null;
                    gp.ui.showMessage("Boommm!!");
                    life -= 1;
                    break;   
                case "Fire":
                    gp.playSE(9);
                    gp.ui.showMessage("Oh!! It's hot");
                    life -= 2;
                    break;
            }
        }
    }

    public void interactNPC(int i) {
        if(i != 999) {
            System.out.println("OUCH! You are hitting me");
            takeDamage();
        }
    }
    
    public void takeDamage(){
        if(count == 0){
            life -= hitdamage;        
            count = 60;
        }
        count--;
    }

    public void draw(Graphics2D g2) {

        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch(direction) {

            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
        }
       g2.drawImage(image, screenX, screenY, null);
    }
}
