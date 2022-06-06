import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Tile implements Size {
    private boolean collision;
    private boolean solid;
    private BufferedImage sprite;

    /**
     * north = 0;
     * south = 1;
     * east = 2;
     * west = 3;
     */
    private String[][] neighborList;
    private int weight;
    private String tileName;

    /**
     * 
     * @param sprite
     * @param tileName
     * @param neighborList
     * @param weight
     * @param collision
     * @param solid
     */
    public Tile(BufferedImage sprite, String tileName, String[][] neighborList, int weight, boolean collision, boolean solid) {
        this.sprite = sprite;
        this.tileName = tileName;
        this.neighborList = neighborList;
        this.weight = weight;
        this.collision = collision;
        this.solid = solid;
    }

    public abstract void collide(int x, int y, Player player); 

    public String getName() {
        return tileName;
    }

    public String[][] getNeightbor() {
        return neighborList;
    }

    public int getWeight() {
        return weight;
    }

    public boolean getCollision() {
        return collision;
    }

    public boolean getSolid() {
        return solid;
    }

    public void draw(Graphics sceneBuffer, int x, int y) {
        sceneBuffer.drawImage(sprite, x * SIZE, y * SIZE, SIZE, SIZE, null);
    }
}
