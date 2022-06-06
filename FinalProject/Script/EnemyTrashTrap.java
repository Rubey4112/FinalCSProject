import java.awt.image.BufferedImage;

public class EnemyTrashTrap extends Enemy {
    private Player player;

    public EnemyTrashTrap(BufferedImage sprt, int x, int y, Player p) {
        super(sprt, x, y);
        player = p;
    }

    public void movement(int pX, int pY, double delta) {
        setX(getX() - pX);
        setY(getY() - pY);
    }

    public void attack() {
        if (checkCollision(120, 120))
            player.gotHit();
    }
}