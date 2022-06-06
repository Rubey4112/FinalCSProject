import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Projectile implements Entity, Size {
    private double dx;
    private double dy;
    private int x;
    private int y;
    private int health;
    private BufferedImage sprite;

    public Projectile(BufferedImage sprt, double mX, double mY, int sX, int sY) {
        dx = mX - sX;
        dy = mY - sY;
        health = 60;
        sprite = sprt;
        x = sX;
        y = sY;
    }

    public boolean checkCollision(int bX, int bY) {
        return (x < bX + SIZE && x + SIZE > bX && y < bY + SIZE && y + SIZE > bY);
    }

    public int checkHealth() {
        if (health > 0)
            health--;
        return health;
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

    public void movement(int pX, int pY, double delta) {
        double isqrt = 1 / Math.sqrt(dx * dx + dy * dy);

        x += (int) (dx * isqrt * 250 * delta) - pX;
        y += (int) (dy * isqrt * 250 * delta) - pY;
    }

    public void attack() {

    }

    public void draw(Graphics sceneBuffer) {
        sceneBuffer.drawImage(sprite, x, y, SIZE, SIZE, null);
    }

}