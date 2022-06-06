public class RoomManager implements Size {
    private DrunkardWalk walker;
    private int[][] layout;
    private Room[][] map;
    private ScenePanel scenePanel;

    public RoomManager(ScenePanel scene) {
        walker = new DrunkardWalk();
        walker.doMagic();
        layout = walker.getMap();
        map = new Room[TILES + 2][TILES + 2];
        scenePanel = scene;
        convert();
    }

    public int[] getStartingCoord() {
        return walker.getStartingCoord();
    }

    public Room[][] getMap() {
        return map;
    }

    private void convert() {
        for (int r = 0; r < TILES+2; r++) {
            for (int c = 0; c < TILES+2; c++) {
                if (r == 0 || c == 0 || r == 6 || c == 6)
                    map[r][c] = new Room("./Maps/roomEmpty.txt", "Empty", scenePanel);
                else {
                    int num = layout[r-1][c-1];
                    switch (num) {
                        case 0:
                            map[r][c] = new Room("./Maps/roomEmpty.txt", "Empty", scenePanel);
                            break;
                        case 1:
                            map[r][c] = new Room("./Maps/room01.txt", "Start", scenePanel);
                            break;
                        case 2:
                            map[r][c] = new Room("./Maps/room01.txt", "Normal", scenePanel);
                            break;
                        case 3:
                            map[r][c] = new Room("./Maps/roomEnd.txt", "End", scenePanel);
                            break;
                    }
                }
            }
        }
    }
}
