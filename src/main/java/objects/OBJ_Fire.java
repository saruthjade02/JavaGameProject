package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Fire extends objects.SuperObject {

    GamePanel gp;

    public OBJ_Fire(GamePanel gp) {

        this.gp = gp;

        name = "Fire";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/fire_left.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
