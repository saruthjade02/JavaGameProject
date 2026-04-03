package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Locker extends objects.SuperObject {

    GamePanel gp;

    public OBJ_Locker(GamePanel gp) {

        this.gp = gp;

        name = "Locker";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/locker.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
