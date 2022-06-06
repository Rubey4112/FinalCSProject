import java.awt.image.BufferedImage;

public class EnemyWarrior extends Enemy {
    private Player player;
    private int attackCooldown;

    public EnemyWarrior(BufferedImage sprt, int x, int y, Player p) {
        super(sprt, x, y);
        attackCooldown = 70;
        player = p;
    }

    public void movement(int pX, int pY, double delta) {
        setX(getX() - pX);
        setY(getY() - pY);
        int dx = (getX() == 120) ? 0 : (getX() > 120) ? -1 : 1;
        int dy = (getY() == 120) ? 0 : (getY() > 120) ? -1 : 1;
        int speed = 90;
        double isqrt = 1 / Math.sqrt(dx * dx + dy * dy);
        int enemyVX = (int) (dx * isqrt * speed * delta);
        int enemyVY = (int) (dy * isqrt * speed * delta);
        setX(getX() + enemyVX);
        setY(getY() + enemyVY);
    }

    public void attack() {
        if(checkCollision(120, 120)) {
            if (attackCooldown > 0) {
                attackCooldown--;
                return;
            }
            player.gotHit();
            attackCooldown = 85;
            return;
        }
        attackCooldown = 70;
    }
}