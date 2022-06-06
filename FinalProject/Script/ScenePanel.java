import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

public class ScenePanel extends JPanel implements Size {
    private static final int FRAME = 256;

    private MenuManager menu;

    private BufferedImage sceneImage;
    private Graphics sceneBuffer;

    private Player player;

    private RoomManager roomManager;
    private Room[][] rooms;
    private Tile[][] map;

    private Enemy[] enemiesArray;
    private String[] enemiesType = new String[] { "Warrior", "Archer", "TrashTrap" };

    private Timer t;

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    private Instant startTime;
    private Instant endTime;

    private Projectile[] bulletsArray;

    private HUD hud;
    private int floor;
    private AudioManager audio;

    public ScenePanel(MenuManager panel, HUD statsHud, AudioManager audioManager) {
        Sprite.image();
        audio = audioManager;
        menu = panel;
        hud = statsHud;

        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Sprite.cursor, new Point(0, 0), "Cursor"));

        sceneImage = new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
        sceneBuffer = sceneImage.createGraphics();

        Mouse mia = new Mouse();

        addKeyListener(new Key());
        addMouseListener(mia);
        setFocusable(true);

        t = new Timer(16, new AnimationListener());

        startTime = Instant.now();
        endTime = Instant.now();
    }

    public void playerCollision() {
        int leftTile = (player.getX() + 120) / SIZE;
        int rightTile = (player.getX() + player.getWidth() + 119) / SIZE;
        int topTile = (player.getY() + 120) / SIZE;
        int bottomTile = (player.getY() + player.getHeight() + 119) / SIZE;
        for (int r = topTile; r <= bottomTile; r++) {
            for (int c = leftTile; c <= rightTile; c++) {
                if (map[r][c].getCollision())
                    map[r][c].collide(c * SIZE, r * SIZE, player, player.getX() + 120, player.getY() + 120);
            }
        }
    }

    public void enemiesCollision(Enemy enemy) {
        int leftTile = (player.getX() + enemy.getX()) / SIZE;
        int rightTile = (player.getX() + player.getWidth() + enemy.getX() - 1) / SIZE;
        int topTile = (player.getY() + enemy.getY()) / SIZE;
        int bottomTile = (player.getY() + player.getHeight() + enemy.getY() - 1) / SIZE;
        for (int r = topTile; r <= bottomTile; r++) {
            for (int c = leftTile; c <= rightTile; c++) {
                if (map[r][c].getCollision())
                    map[r][c].collide(map[r][c].getX(), map[r][c].getY(), enemy, enemy.getX(), enemy.getY());
            }
        }
    }

    public boolean bulletsCycle(Projectile bullet, double deltaTime) {
        if (bullet == null)
            return false;
        if (bullet.checkHealth() < 1)
            return true;
        int leftTile = (player.getX() + bullet.getX()) / SIZE;
        int rightTile = (player.getX() + player.getWidth() + bullet.getX() - 1) / SIZE;
        int topTile = (player.getY() + bullet.getY()) / SIZE;
        int bottomTile = (player.getY() + player.getHeight() + bullet.getY() - 1) / SIZE;
        bullet.movement((int) player.getVX(), (int) player.getVY(), deltaTime);
        bullet.draw(sceneBuffer);
        for (int r = topTile; r <= bottomTile; r++) {
            for (int c = leftTile; c <= rightTile; c++) {
                if (map[r][c].getSolid())
                    return true;
            }
        }
        return false;
    }

    public void drawTile() {
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                Tile tile = map[r][c];
                int x = (c * SIZE) - player.getX();
                int y = (r * SIZE) - player.getY();
                tile.draw(sceneBuffer, x, y);
            }
        }
    }

    public void paintComponent(Graphics g) {
        int scaleVal = getWidth() > getHeight()
                ? getHeight()
                : getWidth();
        g.drawImage(sceneImage, (getWidth() / 2) - (scaleVal / 2), 0, scaleVal, scaleVal, null);
        // g.drawImage(sceneImage, 0, 0, scaleVal, scaleVal, null);
    }

    public void begin() {
        t.start();
    }

    public void end() {
        t.stop();
    }

    private void enemiesCycle(Double deltaTime, int aX, int aY, int bX, int bY) {
        for (int i = 0; i < enemiesArray.length; i++) {
            if (enemiesArray[i] != null) {
                if(enemiesArray[i].getClass() == EnemyTrashTrap.class) {
                    if(enemiesArray[i].checkCollision(120, 120)) {
                        enemiesArray[i].attack();
                        enemiesArray[i] = new EnemyWarrior(Sprite.enemyWarrior, enemiesArray[i].getX(), enemiesArray[i].getY(), player);
                    }
                }
                for (int j = 0; j < bulletsArray.length; j++) {
                    if (bulletsArray[j] != null
                            && enemiesArray[i].checkCollision(bulletsArray[j].getX(), bulletsArray[j].getY())) {
                        if (enemiesArray[i].gotHit()) {
                            audio.playEnemyHurt();
                            bulletsArray[j] = null;
                        }
                    }
                }
                if (enemiesArray[i].checkHealth() > 0) {
                    enemiesArray[i].movement(player.getVX() + (aX - bX), player.getVY() + (aY - bY),
                            deltaTime);
                    enemiesCollision(enemiesArray[i]);
                    enemiesArray[i].attack();
                    enemiesArray[i].draw(sceneBuffer);
                } else
                    enemiesArray[i] = null;
            }
        }
    }

    private void animate() {
        startTime = endTime;
        endTime = Instant.now();
        double deltaTime = ((double) Duration.between(startTime, endTime).toMillis()) / 1000;

        drawTile();
        for (int i = 0; i < bulletsArray.length; i++) {
            if (bulletsCycle(bulletsArray[i], deltaTime))
                bulletsArray[i] = null;
        }

        enemiesSpawn();

        player.movement(0, 0, deltaTime);
        int bX = player.getX();
        int bY = player.getY();
        playerCollision();
        int aX = player.getX();
        int aY = player.getY();
        player.draw(sceneBuffer);
        
        enemiesCycle(deltaTime, aX, aY, bX, bY);
        int playerHealth = player.checkHealth();
        hud.setHealth(playerHealth);
        if (playerHealth <= 0) {
            audio.playDeath();
            menu.gameOver(floor);
        }

        repaint();
    }

    private class AnimationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) // Gets called over and over by the Timer
        {
            animate(); // ...hence animation!
        }
    }

    private class Mouse extends MouseInputAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int scaleVal = getWidth() > getHeight()
                    ? getHeight()
                    : getWidth();
            int mouseX = map(e.getX(), (getWidth() - scaleVal) / 2, getWidth() - (getWidth() - scaleVal) / 2, 0, FRAME);
            int mouseY = map(e.getY(), 0, scaleVal, 0, FRAME);
            for (int i = 0; i < bulletsArray.length; i++) {
                if (bulletsArray[i] == null) {
                    bulletsArray[i] = new Projectile(Sprite.bullet, mouseX, mouseY, 120, 120);
                    break;
                }
            }
        }

        private int map(int x, int in_min, int in_max, int out_min, int out_max) {
            return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
        }
    }

    private class Key extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W && !up) {
                player.addDY(-1);
                up = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_S && !down) {
                player.addDY(1);
                down = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_D && !right) {
                player.addDX(1);
                right = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_A && !left) {
                player.addDX(-1);
                left = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) // Note keyReleased is called when... a key is released!
        {
            // check for up arrow
            if (e.getKeyCode() == KeyEvent.VK_W) {
                player.addDY(1);
                up = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                player.addDY(-1);
                down = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                player.addDX(-1);
                right = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                player.addDX(1);
                left = false;
            }
        }
    }

    public void nextLevel() {
        audio.playNextLevel();
        floor++;
        hud.setFloor(floor);
        player.setHealth(5);
        bulletsArray = new Projectile[4];
        enemiesArray = new Enemy[enemiesArray.length + 1];
        roomManager = new RoomManager(this);
        spawnPlayer();
        rooms = roomManager.getMap();
        convertMap();
        drawTile();

        player.draw(sceneBuffer);
        repaint();
    }

    public void reset() {
        floor = 1;
        hud.setFloor(floor);
        up = down = left = right = false;
        bulletsArray = new Projectile[4];
        enemiesArray = new Enemy[5];
        roomManager = new RoomManager(this);
        player = new Player((roomManager.getStartingCoord()[1] + 1) * 16 * SIZE,
                (roomManager.getStartingCoord()[0] + 1) * 16 * SIZE, 5, audio);
        rooms = roomManager.getMap();
        convertMap();
        drawTile();

        player.draw(sceneBuffer);
        repaint();
    }

    public void convertMap() {
        map = new Tile[112][112];
        for (int r = 0; r < rooms.length; r++) {
            for (int c = 0; c < rooms[0].length; c++) {
                Room room = rooms[r][c];
                Tile[][] tiles = room.getRoom();
                for (int r2 = 0; r2 < tiles.length; r2++) {
                    for (int c2 = 0; c2 < tiles[0].length; c2++) {
                        map[r2 + (r * 16)][c2 + (c * 16)] = tiles[r2][c2];
                    }
                }
            }
        }
    }

    public void spawnPlayer() {
        player.setX((roomManager.getStartingCoord()[1] + 1) * 16 * SIZE);
        player.setY((roomManager.getStartingCoord()[0] + 1) * 16 * SIZE);
    }

    public ArrayList<Integer>[] validArea() {
        @SuppressWarnings({ "unchecked" })
        ArrayList<Integer>[] validTiles = new ArrayList[2];
        for (int r = 0; r < TILES; r++) {
            for (int c = 0; c < TILES; c++) {
                if (!map[r][c].getSolid()) {
                    validTiles[0].add(c);
                    validTiles[1].add(r);
                }
            }
        }
        return validTiles;
    }

    public void randomEnemySpawn(Room room) {
        Tile[][] roomMap = room.getRoom();
        for (int i = 0; i < enemiesArray.length; i++) {
            int enemyTypeInd = (int) (Math.random() * enemiesType.length);
            int spotX = ((int) (Math.random() * 14) + 1);
            int spotY = ((int) (Math.random() * 14) + 1);
            if (enemiesType[enemyTypeInd].equals("Warrior")) {
                enemiesArray[i] = new EnemyWarrior(Sprite.enemyWarrior, roomMap[spotY][spotX].getX(),
                        roomMap[spotY][spotX].getY(), player);
            } else if (enemiesType[enemyTypeInd].equals("Archer")) {
                enemiesArray[i] = new EnemyArcher(Sprite.enemyArcher, roomMap[spotY][spotX].getX(),
                        roomMap[spotY][spotX].getY(), player, sceneBuffer, map);
            } else {
                enemiesArray[i] = new EnemyTrashTrap(Sprite.enemyTrashTrap, roomMap[spotY][spotX].getX(),
                        roomMap[spotY][spotX].getY(), player);
            }
        }
    }

    public void unlocklockRoom(int roomX, int roomY) {
        for (int r = 6; r < 10; r++) {
            map[roomY * 16 + r][roomX * 16 - 1] = new TileFL(this);
            map[roomY * 16 + r][roomX * 16 + 16] = new TileFL(this);
        }
        for (int c = 6; c < 10; c++) {
            map[roomY * 16 - 1][roomX * 16 + c] = new TileFL(this);
            map[roomY * 16 + 16][roomX * 16 + c] = new TileFL(this);
        }
    }

    public void lockRoom(int roomX, int roomY) {
        for (int r = 6; r < 10; r++) {
            map[roomY * 16 + r][roomX * 16 - 1] = new TileBW(this);
            map[roomY * 16 + r][roomX * 16 + 16] = new TileBW(this);
        }
        for (int c = 6; c < 10; c++) {
            map[roomY * 16 - 1][roomX * 16 + c] = new TileBW(this);
            map[roomY * 16 + 16][roomX * 16 + c] = new TileBW(this);
        }
    }

    public void enemiesSpawn() {
        int roomX = (player.getX() + 128) / 256;
        int roomY = (player.getY() + 128) / 256;
        Room currentRoom = rooms[roomY][roomX];
        if (currentRoom.getName().equals("Normal") && currentRoom.getEnemySpawn()) {
            lockRoom(roomX, roomY);
            randomEnemySpawn(currentRoom);
            currentRoom.disableEnemySpawn();
        } else if (currentRoom.getName().equals("Normal")) {
            boolean noEnemies = true;
            for (int i = 0; i < enemiesArray.length; i++) {
                if (enemiesArray[i] != null)
                    noEnemies = false;
            }
            if (noEnemies)
                unlocklockRoom(roomX, roomY);
        }
    }
}