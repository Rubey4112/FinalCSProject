import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
    public static BufferedImage brickWall;
    public static BufferedImage brickFloor;
    public static BufferedImage portalLeftTop;
    public static BufferedImage portalRightTop;
    public static BufferedImage portalLeftBottom;
    public static BufferedImage portalRightBottom;
    public static BufferedImage air;
    public static BufferedImage tileNull;


    public static BufferedImage collision;

    public static BufferedImage cursor;
    public static BufferedImage player;
    public static BufferedImage enemy;
    public static BufferedImage mouse;
    public static BufferedImage arrow;
    public static BufferedImage enemyArcher;
    public static BufferedImage enemyWarrior;
    public static BufferedImage enemyTrashTrap;
    public static BufferedImage bullet;
    
    public static void image() {
        try {
            tileNull = ImageIO.read(new File("./Sprites/Tiles/null.png").getAbsoluteFile());
            brickWall = ImageIO.read(new File("./Sprites/Tiles/Brick.png").getAbsoluteFile());
            brickFloor = ImageIO.read(new File("./Sprites/Tiles/Floor.png").getAbsoluteFile());
            portalLeftTop = ImageIO.read(new File("./Sprites/Tiles/PortalLeftTop.png").getAbsoluteFile());
            portalRightTop = ImageIO.read(new File("./Sprites/Tiles/PortalRightTop.png").getAbsoluteFile());
            portalLeftBottom = ImageIO.read(new File("./Sprites/Tiles/PortalLeftBottom.png").getAbsoluteFile());
            portalRightBottom = ImageIO.read(new File("./Sprites/Tiles/PortalRightBottom.png").getAbsoluteFile());
            air = ImageIO.read(new File("./Sprites/Tiles/Air.png").getAbsoluteFile());

            cursor = ImageIO.read(new File("./Sprites/Misc/Cursor.png").getAbsoluteFile());
            collision = ImageIO.read(new File("./Sprites/Misc/Collision.png").getAbsoluteFile());
            player = ImageIO.read(new File("./Sprites/Player/Player.png").getAbsoluteFile());
            enemy = ImageIO.read(new File("./Sprites/Player/DebugPlayer.png").getAbsoluteFile());
            enemyArcher = ImageIO.read(new File("./Sprites/Player/EnemyArcher.png").getAbsoluteFile());
            enemyWarrior = ImageIO.read(new File("./Sprites/Player/EnemyWarrior.png").getAbsoluteFile());
            enemyTrashTrap = ImageIO.read(new File("./Sprites/Player/EnemyTrashTrap.png").getAbsoluteFile());
            mouse = ImageIO.read(new File("./Sprites/Misc/MouseSelect.png").getAbsoluteFile());
            arrow = ImageIO.read(new File("./Sprites/Misc/Arrow.png").getAbsoluteFile());
            bullet = ImageIO.read(new File("./Sprites/Misc/Shooty.png").getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
