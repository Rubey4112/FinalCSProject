import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Tile implements Size {
    private boolean collision;
    private boolean solid;
    private BufferedImage sprite;
    private String tileName;
    private ScenePanel scene;
    private int x;
    private int y;

    /**
     * 
     * @param sprite
     * @param tileName
     * @param neighborList
     * @param weight
     * @param collision
     * @param solid
     */
    public Tile(BufferedImage sprite, String tileName, boolean collision, boolean solid, ScenePanel scene) {
        this.sprite = sprite;
        this.tileName = tileName;
        this.collision = collision;
        this.solid = solid;
        this.scene = scene;
    }

    public abstract void collide(int x, int y, Entity entity, int eX, int eY); 

    public String getName() {
        return tileName;
    }

    public boolean getCollision() {
        return collision;
    }

    public boolean getSolid() {
        return solid;
    }

    public ScenePanel getScene() {
        return scene;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics sceneBuffer, int x, int y) {
        this.x = x;
        this.y = y;
        sceneBuffer.drawImage(sprite, x, y, SIZE, SIZE, null);
    }
}
