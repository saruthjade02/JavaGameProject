package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Diamond extends objects.SuperObject {

    GamePanel gp;

    public OBJ_Diamond(GamePanel gp) {

        this.gp = gp;

        name = "Diamond";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/diamond.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }
}
