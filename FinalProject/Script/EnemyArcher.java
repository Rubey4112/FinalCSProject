import java.awt.image.BufferedImage;
import java.awt.Graphics;;

public class EnemyArcher extends Enemy {
    private Projectile[] arrowsArray;
    private Player player;
    private Graphics sceneBuffer;
    private int attackCooldown;
    private Tile[][] map;

    public EnemyArcher(BufferedImage sprt, int x, int y, Player p, Graphics scene, Tile[][] map) {
        super(sprt, x, y);
        player = p;
        sceneBuffer = scene;
        arrowsArray = new Projectile[3];
        attackCooldown = (int) (Math.random() * 100) + 50;
        this.map = map;
    }

    public boolean arrowCycle(Projectile arrow, double delta) {
        if (arrow == null)
            return false;
        if(arrow.checkCollision(120, 120)) {
            if(player.gotHit())
                return true;
        }
        int leftTile = (player.getX() + arrow.getX()) / SIZE;
        int rightTile = (player.getX() + player.getWidth() + arrow.getX() - 1) / SIZE;
        int topTile = (player.getY() + arrow.getY()) / SIZE;
        int bottomTile = (player.getY() + player.getHeight() + arrow.getY() - 1) / SIZE;
        arrow.movement((int) player.getVX(), (int) player.getVY(), delta);
        arrow.draw(sceneBuffer);
        for (int r = topTile; r <= bottomTile; r++) {
            for (int c = leftTile; c <= rightTile; c++) {
                if (map[r][c].getSolid())
                    return true;
            }
        }
        return false;
    }

    public void movement(int pX, int pY, double delta) {
        setX(getX() - pX);
        setY(getY() - pY);
        for (int i = 0; i < arrowsArray.length; i++) {
            if (arrowCycle(arrowsArray[i], delta)) {
                arrowsArray[i] = null;
            }
        }
    }

    public void attack() {
        if (attackCooldown > 0) {
            attackCooldown--;
            return;
        }
        for (int i = 0; i < arrowsArray.length; i++) {
            if (arrowsArray[i] == null) {
                arrowsArray[i] = new Projectile(Sprite.arrow, 120, 120, getX(), getY());
                break;
            }
        }
        attackCooldown = 90;
    }
}