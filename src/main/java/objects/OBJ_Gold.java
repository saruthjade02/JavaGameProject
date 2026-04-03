package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Gold extends objects.SuperObject {

    GamePanel gp;

    public OBJ_Gold(GamePanel gp) {

        this.gp = gp;

        name = "Gold";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/gold.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }
}
