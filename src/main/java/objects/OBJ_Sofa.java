package objects;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Sofa extends objects.SuperObject {

    GamePanel gp;

    public OBJ_Sofa(GamePanel gp) {
        this.gp = gp;
        name = "Sofa";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/sofa.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}