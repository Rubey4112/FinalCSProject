import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player implements Entity, Size {
    private int x;
    private int y;
    private int width;
    private int height;
    private double playerDX;
    private double playerDY;
    private int health;
    private int speed;
    private BufferedImage sprite;
    private int playerVX;
    private int playerVY;
    private int iframe;
    private AudioManager audio;

    public Player(int pX, int pY, int hp, AudioManager audioManager) {
        sprite = Sprite.player;
        audio = audioManager;
        health = hp;
        width = SIZE;
        height = SIZE;
        speed = 180;
        playerDX = 0;
        playerDY = 0;
        playerVX = 0;
        playerVY = 0;
        x = pX;
        y = pY;
        iframe = 35;
    }

    public void movement(int pX, int pY, double delta) {
        double isqrt = 1 / Math.sqrt(playerDX * playerDX + playerDY * playerDY);
        playerVX = (int) (playerDX * isqrt * speed * delta);
        playerVY = (int) (playerDY * isqrt * speed * delta);
        x += playerVX;
        y += playerVY;
    }

    public int checkHealth() {
        iframe--;
        return health;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public boolean gotHit() {
        if (iframe <= 0) {
            audio.playPlayerHurt();
            health--;
            iframe = 25;
            return true;
        }
        return false;
    }

    public void addDX(int dx) {
        playerDX += dx;
    }

    public void addDY(int dy) {
        playerDY += dy;
    }

    public void setDX(int dx) {
        playerDX = dx;
    }

    public void setDY(int dy) {
        playerDY = dy;
    }

    public int getVX() {
        return playerVX;
    }

    public int getVY() {
        return playerVY;
    }

    public int getDX() {
        return (int) playerDX;
    }

    public int getDY() {
        return (int) playerDY;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void attack() {
    }

    public void draw(Graphics sceneBuffer) {
        sceneBuffer.drawImage(sprite, 128 - SIZE / 2, 128 - SIZE / 2, SIZE, SIZE, null);
    }
}
