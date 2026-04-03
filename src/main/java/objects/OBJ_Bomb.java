package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Bomb extends objects.SuperObject {

    GamePanel gp;

    public OBJ_Bomb(GamePanel gp) {

        this.gp = gp;

        name = "Bomb";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/bomb.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }
}
