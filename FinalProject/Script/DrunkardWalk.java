import java.util.ArrayList;

public class DrunkardWalk implements Size {
    private int[] coord;
    private int[] startCoord;
    private int[] endCoords;
    private int[][] map;

    public DrunkardWalk() {
        map = new int[TILES][TILES];
        int bRand = (int) (Math.random() * TILES);
        int bStart = (bRand == TILES) ? TILES - 1 : bRand;
        coord = new int[] { TILES - 1, bStart };
        startCoord = new int[] { TILES - 1, bStart };
    }

    public int[] getStartingCoord() {
        return startCoord;
    }

    public int[][] getMap() {
        return map;
    }

    public void doMagic() {
        int i = 0;
        while(i<8) {
            i = iterate(i);
        }
        map[startCoord[0]][startCoord[1]] = 1;
        map[endCoords[0]][endCoords[1]] = 3;
    }

    private int iterate(int i) {
        if(map[coord[0]][coord[1]] == 0) {
            i++;
            endCoords = coord;
        }
        map[coord[0]][coord[1]] = 2;
        walk(coord);
        return i;
    }

    private void walk(int[] curCoords) {
        ArrayList<int[]> validCoords = new ArrayList<>();
        int r = curCoords[0];
        int c = curCoords[1];
        if (c > 0) {
            int[] westCoords = { r, c - 1 };
                validCoords.add(westCoords);
        }
        if (r > 0) {
            int[] northCoords = { r - 1, c };
            
                validCoords.add(northCoords);
            

        }
        if (r < TILES - 1) {
            int[] southCoords = { r + 1, c };
                validCoords.add(southCoords);
            

        }
        if (c < TILES - 1) {
            int[] eastCoords = { r, c + 1 };
                validCoords.add(eastCoords);
            
        }
        coord = validCoords.get((int) (Math.random() * validCoords.size()));
    }
}
