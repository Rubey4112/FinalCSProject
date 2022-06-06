import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Room implements Size {
    private Tile[][] roomMap;
    private String roomName;
    private ScenePanel scenePanel;
    private boolean enemySpawn;

    public Room(String file, String name, ScenePanel scene) {
        roomName = name;
        roomMap = new Tile[16][16];
        scenePanel = scene;
        enemySpawn = true;
        loadMap(file);
    }

    public String getName() {
        return roomName;
    }

    public void disableEnemySpawn() {
        enemySpawn = false;
    }

    public boolean getEnemySpawn() {
        return enemySpawn;
    }

    public Tile[][] getRoom() {
        return roomMap;
    }

    private void loadMap(String file) {
        try {
            InputStream is = new FileInputStream(new File(file));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int r = 0; r < 16; r++) {
                String line = br.readLine();
                for (int c = 0; c < 16; c++) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[c]);
                    switch (num) {
                        case 0:
                            roomMap[r][c] = new TileA(scenePanel);
                            break;
                        case 1:
                            roomMap[r][c] = new TileFL(scenePanel);
                            break;
                        case 2:
                            roomMap[r][c] = new TileBW(scenePanel);
                            break;
                        case 3:
                            roomMap[r][c] = new TilePLT(scenePanel);
                            break;
                        case 4:
                            roomMap[r][c] = new TilePRT(scenePanel);
                            break;
                        case 5:
                            roomMap[r][c] = new TilePLB(scenePanel);
                            break;
                        case 6:
                            roomMap[r][c] = new TilePRB(scenePanel);
                            break;
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
