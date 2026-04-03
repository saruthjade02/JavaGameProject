package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import main.Mode;

public class NPC_Baby extends entity.Entity {
    int rangeX = 1, rangeY = 1;
    public NPC_Baby(GamePanel gp) {
        super(gp);
        rangeX = gp.tileSize*3;
        rangeY = gp.tileSize*3;
        direction = "down";
        SetValue("Normal");
        getPlayerImage();
    }

    public void getPlayerImage() {

        up1 = setup("npc/baby_up_1");
        up2 = setup("npc/baby_up_2");
        down1 = setup("npc/baby_down_1");
        down2 = setup("npc/baby_down_2");
        left1 = setup("npc/baby_left_1");
        left2 = setup("npc/baby_left_2");
        right1 = setup("npc/baby_right_1");
        right2 = setup("npc/baby_right_2");
    }

    public void SetValue(String mode){
        System.out.println(mode);
        switch(mode){
            case "Baby" -> speed = 1;
            case "Esay" -> speed = 2;
            case "Normal" -> speed = 3;
            case "Hard" -> speed = 4;
            case "Nightmare" -> speed = 5;
        }
    }
    
    public void setAction() {

        
        //State Interact
        CheckRange();
        //State Attack 
        
    }
    
    private void IdleState(){
        //State Idlde
        actionLockCounter++;

        if (actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100) + 1; // Pick a number from 1 - 100

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
    
    public void CheckRange(){
//        System.out.println("PLayer X : "+ gp.getPlayer().worldX + "Y : "+gp.getPlayer().worldY);
//        System.out.println("Enemy X : "+ this.worldX + "Y : "+this.worldY);
        int distanceX = gp.getPlayer().worldX - this.worldX;
        int distanceY = gp.getPlayer().worldY - this.worldY;
//        System.out.println("Distance X : "+ distanceX + "Y : "+distanceY);
        if(Math.abs(distanceY) < rangeY && Math.abs(distanceX) < rangeX){
//            System.out.println("Check X" + Math.abs(distanceX) + "Check Y " + Math.abs(distanceY));
            if(Math.abs(distanceY) > Math.abs(distanceX)){
                if(distanceY < 0){
                    direction = "up";  
                }else if(distanceY >= 0){
                   direction = "down";    
                }
            }else{
                if(distanceX < 0){
                    direction = "left"; 
                }else if(distanceX >= 0){
                    direction = "right"; 
                }
            }
        }else{
            IdleState();
        }
        
            
//            if(distanceY > 0){
//            }
//            else{
//            }
//        if(){
//            if(distanceX > 0){
//            System.out.println();
//                direction = "right";
//            }
//            else{
//                direction = "left";
//            }
//        }
    }
}
