package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Coin extends objects.SuperObject {

    GamePanel gp;

    public OBJ_Coin(GamePanel gp) {

        this.gp = gp;

        name = "Coin";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/coin.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }
}
