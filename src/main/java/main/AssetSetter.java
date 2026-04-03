package main;

import entity.NPC_Baby;
import objects.*;

public class AssetSetter {

    main.GamePanel gp;

    public AssetSetter(main.GamePanel gp) {

        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new objects.OBJ_Key(gp);
        gp.obj[0].worldX = 43 * gp.tileSize;
        gp.obj[0].worldY = 27 * gp.tileSize;

        //world02
        gp.obj[2] = new objects.OBJ_Door(gp);
        gp.obj[2].worldX = 24 * gp.tileSize;
        gp.obj[2].worldY = 7 * gp.tileSize;

        //world02
        gp.obj[3] = new objects.OBJ_Chest(gp);
        gp.obj[3].worldX = 24 * gp.tileSize;
        gp.obj[3].worldY = 2 * gp.tileSize;

        gp.obj[4] = new objects.OBJ_Sofa(gp);
        gp.obj[4].worldX = 17 * gp.tileSize;
        gp.obj[4].worldY = 7 * gp.tileSize;

        gp.obj[5] = new objects.OBJ_Boots(gp);
        gp.obj[5].worldX = 35 * gp.tileSize;
        gp.obj[5].worldY = 27 * gp.tileSize;

        gp.obj[6] = new objects.OBJ_Fire(gp);
        gp.obj[6].worldX = 7 * gp.tileSize;
        gp.obj[6].worldY = 20 * gp.tileSize;

        gp.obj[7] = new objects.OBJ_Fire(gp);
        gp.obj[7].worldX = 6 * gp.tileSize;
        gp.obj[7].worldY = 20 * gp.tileSize;

        gp.obj[8] = new objects.OBJ_Fire(gp);
        gp.obj[8].worldX = 5 * gp.tileSize;
        gp.obj[8].worldY = 23 * gp.tileSize;

        gp.obj[9] = new objects.OBJ_Fire(gp);
        gp.obj[9].worldX = 4 * gp.tileSize;
        gp.obj[9].worldY = 25 * gp.tileSize;

        gp.obj[10] = new objects.OBJ_Fire(gp);
        gp.obj[10].worldX = 2 * gp.tileSize;
        gp.obj[10].worldY = 26 * gp.tileSize;

        gp.obj[11] = new objects.OBJ_Bomb(gp);
        gp.obj[11].worldX = 18 * gp.tileSize;
        gp.obj[11].worldY = 24 * gp.tileSize;

        gp.obj[12] = new objects.OBJ_Bomb(gp);
        gp.obj[12].worldX = 26 * gp.tileSize;
        gp.obj[12].worldY = 16 * gp.tileSize;

        gp.obj[13] = new objects.OBJ_Bomb(gp);
        gp.obj[13].worldX = 29 * gp.tileSize;
        gp.obj[13].worldY = 31 * gp.tileSize;

        gp.obj[14] = new objects.OBJ_Bomb(gp);
        gp.obj[14].worldX = 38 * gp.tileSize;
        gp.obj[14].worldY = 38 * gp.tileSize;

        gp.obj[14] = new objects.OBJ_Bomb(gp);
        gp.obj[14].worldX = 15 * gp.tileSize;
        gp.obj[14].worldY = 34 * gp.tileSize;

        gp.obj[15] = new objects.OBJ_Handcuff(gp);
        gp.obj[15].worldX = 31 * gp.tileSize;
        gp.obj[15].worldY = 11 * gp.tileSize;

        gp.obj[16] = new objects.OBJ_Handcuff(gp);
        gp.obj[16].worldX = 18 * gp.tileSize;
        gp.obj[16].worldY = 14 * gp.tileSize;

        gp.obj[15] = new objects.OBJ_Handcuff(gp);
        gp.obj[15].worldX = 43 * gp.tileSize;
        gp.obj[15].worldY = 26 * gp.tileSize;

        gp.obj[15] = new objects.OBJ_Handcuff(gp);
        gp.obj[15].worldX = 38 * gp.tileSize;
        gp.obj[15].worldY = 14 * gp.tileSize;
        
        gp.obj[16] = new objects.OBJ_Coin(gp);
        gp.obj[16].worldX = 23 * gp.tileSize;
        gp.obj[16].worldY = 17 * gp.tileSize;
        
        gp.obj[17] = new objects.OBJ_Diamond(gp);
        gp.obj[17].worldX = 32 * gp.tileSize;
        gp.obj[17].worldY = 11 * gp.tileSize;
        
        gp.obj[18] = new objects.OBJ_Diamond(gp);
        gp.obj[18].worldX = 31 * gp.tileSize;
        gp.obj[18].worldY = 40 * gp.tileSize;
        
        gp.obj[19] = new objects.OBJ_Diamond(gp);
        gp.obj[19].worldX = 12 * gp.tileSize;
        gp.obj[19].worldY = 27 * gp.tileSize;
        
        gp.obj[20] = new objects.OBJ_Door(gp);
        gp.obj[20].worldX = 37 * gp.tileSize;
        gp.obj[20].worldY = 27 * gp.tileSize;
        
        gp.obj[21] = new objects.OBJ_Diamond(gp);
        gp.obj[21].worldX = 17 * gp.tileSize;
        gp.obj[21].worldY = 34 * gp.tileSize;
        
        gp.obj[22] = new objects.OBJ_Bank(gp);
        gp.obj[22].worldX = 16 * gp.tileSize;
        gp.obj[22].worldY = 42 * gp.tileSize;
        
        gp.obj[23] = new objects.OBJ_Bank(gp);
        gp.obj[23].worldX = 32 * gp.tileSize;
        gp.obj[23].worldY = 24 * gp.tileSize;
        
        gp.obj[24] = new objects.OBJ_Coin(gp);
        gp.obj[24].worldX = 31 * gp.tileSize;
        gp.obj[24].worldY = 18 * gp.tileSize;
        
        gp.obj[25] = new objects.OBJ_Coin(gp);
        gp.obj[25].worldX = 12 * gp.tileSize;
        gp.obj[25].worldY = 10 * gp.tileSize;
        
        gp.obj[26] = new objects.OBJ_Coin(gp);
        gp.obj[26].worldX = 17 * gp.tileSize;
        gp.obj[26].worldY = 13 * gp.tileSize;
        
        gp.obj[27] = new objects.OBJ_Bank(gp);
        gp.obj[27].worldX = 28 * gp.tileSize;
        gp.obj[27].worldY = 21 * gp.tileSize;
        
        gp.obj[28] = new objects.OBJ_Diamond(gp);
        gp.obj[28].worldX = 11 * gp.tileSize;
        gp.obj[28].worldY = 19 * gp.tileSize;
        
        gp.obj[29] = new objects.OBJ_Bank(gp);
        gp.obj[29].worldX = 41 * gp.tileSize;
        gp.obj[29].worldY = 41 * gp.tileSize;
        
        gp.obj[30] = new objects.OBJ_Bank(gp);
        gp.obj[30].worldX = 11 * gp.tileSize;
        gp.obj[30].worldY = 10 * gp.tileSize;
        
        gp.obj[31] = new objects.OBJ_Gold(gp);
        gp.obj[31].worldX = 38 * gp.tileSize;
        gp.obj[31].worldY = 40 * gp.tileSize;
        
        gp.obj[32] = new objects.OBJ_Gold(gp);
        gp.obj[32].worldX = 39 * gp.tileSize;
        gp.obj[32].worldY = 12 * gp.tileSize;
        
        gp.obj[33] = new objects.OBJ_Gold(gp);
        gp.obj[33].worldX = 36 * gp.tileSize;
        gp.obj[33].worldY = 33 * gp.tileSize;
        
        gp.obj[34] = new objects.OBJ_Gold(gp);
        gp.obj[34].worldX = 27 * gp.tileSize;
        gp.obj[34].worldY = 8 * gp.tileSize;
        
        gp.obj[35] = new objects.OBJ_Bomb(gp);
        gp.obj[35].worldX = 17 * gp.tileSize;
        gp.obj[35].worldY = 12 * gp.tileSize;
        
        gp.obj[36] = new objects.OBJ_Bomb(gp);
        gp.obj[36].worldX = 19 * gp.tileSize;
        gp.obj[36].worldY = 17 * gp.tileSize;
        
        gp.obj[37] = new objects.OBJ_Handcuff(gp);
        gp.obj[37].worldX = 24 * gp.tileSize;
        gp.obj[37].worldY = 38 * gp.tileSize;
        
        gp.obj[38] = new objects.OBJ_Handcuff(gp);
        gp.obj[38].worldX = 17 * gp.tileSize;
        gp.obj[38].worldY = 41 * gp.tileSize;
        
        gp.obj[39] = new objects.OBJ_Bomb(gp);
        gp.obj[39].worldX = 30 * gp.tileSize;
        gp.obj[39].worldY = 40 * gp.tileSize;
        
        gp.obj[40] = new objects.OBJ_Bomb(gp);
        gp.obj[40].worldX = 39 * gp.tileSize;
        gp.obj[40].worldY = 39 * gp.tileSize;
    }

    public void setNPC() {

        gp.npc[0] = new NPC_Baby(gp);
        gp.npc[0].worldX = 22 * gp.tileSize;
        gp.npc[0].worldY = 42 * gp.tileSize;
        
        gp.npc[1] = new NPC_Baby(gp);
        gp.npc[1].worldX = 41 * gp.tileSize;
        gp.npc[1].worldY = 27 * gp.tileSize;
    }
}
