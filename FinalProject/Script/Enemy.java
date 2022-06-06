import java.awt.image.BufferedImage;
import java.awt.Graphics;

public abstract class Enemy implements Entity, Size {
    private int x;
    private int y;
    private int health;
    private BufferedImage sprite;
    private int iframe;

    public Enemy(BufferedImage sprt, int ex, int ey) {
        x = ex;
        y = ey;
        sprite = sprt;
        health = 3;
        iframe = 15;
    }

    public int checkHealth() {
        iframe--;
        return health;
    }

    public boolean checkCollision(int bX, int bY) {
        return (x < bX + SIZE && x + SIZE > bX && y < bY + SIZE && y + SIZE > bY);
    }

    public boolean gotHit() {
        if (iframe <= 0) {
            health--;
            iframe = 15;
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(Graphics sceneBuffer) {
        sceneBuffer.drawImage(sprite, x, y, SIZE, SIZE, null);
    }
}