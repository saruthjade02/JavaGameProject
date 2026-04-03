package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Handcuff extends objects.SuperObject {

    GamePanel gp;

    public OBJ_Handcuff(GamePanel gp) {

        this.gp = gp;

        name = "Handcuff";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/handcuff.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }
}
