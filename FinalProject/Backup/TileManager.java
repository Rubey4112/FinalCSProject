import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
//import java.util.Objects;

public class TileManager implements Size {
    @SuppressWarnings({ "unchecked" })
    private ArrayList<Tile>[][] possibleTiles = new ArrayList[TILES][TILES];

    private Tile[][] map;

    public TileManager() {
        for (int r = 0; r < TILES; r++) {
            for (int c = 0; c < TILES; c++) {
                possibleTiles[r][c] = new ArrayList<Tile>(
                        Arrays.asList(new TileA(), new TileBW(), new TileBB(), new TileBBLB(), new TileBBLT(),
                                new TileBBRB(), new TileBBRT(), new TileBCLB(), new TileBCLT(), new TileBCRB(),
                                new TileBCRT(), new TileBSL(), new TileBSR(), new TileBT(), new TileFL()));

            }
        }
        map = new Tile[TILES][TILES];
    }

    private boolean isCollapsed() {
        for (int r = 0; r < possibleTiles.length; r++) {
            for (int c = 0; c < possibleTiles[0].length; c++) {
                if (!possibleTiles[r][c].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[] getMinEntropyCoords() {
        ArrayList<Integer> r = new ArrayList<>();
        ArrayList<Integer> c = new ArrayList<>();
        LinkedList<Integer> size = new LinkedList<>();
        for (int j = 0; j < possibleTiles.length; j++) {
            for (int k = 0; k < possibleTiles[0].length; k++) {
                int tileSize = possibleTiles[j][k].size();
                size.add(tileSize);
            }
        }
        Collections.sort(size);
        int minIndex = (size.lastIndexOf(0) == -1) ? 0 : size.lastIndexOf(0) + 1;
        int minVal = size.get(minIndex);
        for (int j = 0; j < possibleTiles.length; j++) {
            for (int k = 0; k < possibleTiles[0].length; k++) {
                if (possibleTiles[j][k].size() == minVal) {
                    r.add(j);
                    c.add(k);
                }
            }
        }

        int val = (int) (Math.random() * r.size());

        int[] coords = { r.get(val), c.get(val) };
        return coords;
    }

    private int weightedRandom(ArrayList<Tile> tileList) {
        double totalWeight = 0.0;
        for (Tile tile : tileList) {
            totalWeight += tile.getWeight();
        }

        int idx = 0;
        for (double r = Math.random() * totalWeight; idx < tileList.size() - 1; ++idx) {
            r -= tileList.get(idx).getWeight();
            if (r <= 0.0)
                break;
        }
        return idx;
    }

    private void collapse(int r, int c) {
        // int bound = possibleTiles[r][c].size();
        int id = weightedRandom(possibleTiles[r][c]);
        map[r][c] = possibleTiles[r][c].get(id);
        possibleTiles[r][c].clear();
    }

    private ArrayList<String> getPossibleNeighbor(int r, int c, int d) {
        if (map[r][c] != null) {
            return new ArrayList<String>(Arrays.asList(map[r][c].getNeightbor()[d]));
        } else {
            ArrayList<Tile> currentTiles = possibleTiles[r][c];
            ArrayList<String[]> allPossibleNeightborsList = new ArrayList<>();
            currentTiles.forEach((tile) -> allPossibleNeightborsList.add(tile.getNeightbor()[d]));
            ArrayList<String> allPossibleNeightbors = new ArrayList<>();
            allPossibleNeightborsList.forEach((list) -> {
                for (String name : list) {
                    allPossibleNeightbors.add(name);
                }
            });
            return allPossibleNeightbors;
        }
    }

    private void propagate(int[] coords) {
        LinkedList<int[]> stack = new LinkedList<>();
        stack.add(coords);

        while (!stack.isEmpty()) {
            int[] curCoords = stack.removeFirst();
            int r = curCoords[0];
            int c = curCoords[1];
            // System.out.println(stack.size());

            if (c > 0) {
                int[] westCoords = { r, c - 1 };
                ArrayList<Tile> otherPossiblePrototype = possibleTiles[westCoords[0]][westCoords[1]];
                ArrayList<String> otherPrototypesName = new ArrayList<>();
                otherPossiblePrototype.forEach((tile) -> otherPrototypesName.add(tile.getName()));
                ArrayList<String> possibleNeightbors = getPossibleNeighbor(r, c, 3);
                if (!otherPrototypesName.isEmpty()) {
                    for (int i = otherPrototypesName.size() - 1; i >= 0; i--) {
                        String otherPrototype = otherPrototypesName.get(i);
                        if (!possibleNeightbors.contains(otherPrototype)) {
                            otherPossiblePrototype.remove(i);
                            if (!stack.contains(westCoords)) {
                                stack.add(westCoords);
                            }
                        }
                    }
                }
            }
            if (r > 0) {
                int[] northCoords = { r - 1, c };
                ArrayList<Tile> otherPossiblePrototype = possibleTiles[northCoords[0]][northCoords[1]];
                ArrayList<String> otherPrototypesName = new ArrayList<>();
                otherPossiblePrototype.forEach((tile) -> otherPrototypesName.add(tile.getName()));
                ArrayList<String> possibleNeightbors = getPossibleNeighbor(r, c, 0);

                // System.out.println(otherPossiblePrototype.isEmpty()+", " +
                // otherPossiblePrototype);
                if (!otherPrototypesName.isEmpty()) {
                    for (int i = otherPrototypesName.size() - 1; i >= 0; i--) {
                        String otherPrototype = otherPrototypesName.get(i);
                        if (!possibleNeightbors.contains(otherPrototype)) {
                            otherPossiblePrototype.remove(i);
                            if (!stack.contains(northCoords)) {
                                stack.add(northCoords);
                            }
                        }
                    }
                }
            }
            if (r < TILES - 1) {
                int[] southCoords = { r + 1, c };
                ArrayList<Tile> otherPossiblePrototype = possibleTiles[southCoords[0]][southCoords[1]];
                ArrayList<String> otherPrototypesName = new ArrayList<>();
                otherPossiblePrototype.forEach((tile) -> otherPrototypesName.add(tile.getName()));
                ArrayList<String> possibleNeightbors = getPossibleNeighbor(r, c, 1);
                if (!otherPrototypesName.isEmpty()) {
                    for (int i = otherPrototypesName.size() - 1; i >= 0; i--) {
                        String otherPrototype = otherPrototypesName.get(i);
                        if (!possibleNeightbors.contains(otherPrototype)) {
                            otherPossiblePrototype.remove(i);
                            if (!stack.contains(southCoords)) {
                                stack.add(southCoords);
                            }
                        }
                    }
                }
            }
            if (c < TILES - 1) {
                int[] eastCoords = { r, c + 1 };
                ArrayList<Tile> otherPossiblePrototype = possibleTiles[eastCoords[0]][eastCoords[1]];
                ArrayList<String> otherPrototypesName = new ArrayList<>();
                otherPossiblePrototype.forEach((tile) -> otherPrototypesName.add(tile.getName()));
                ArrayList<String> possibleNeightbors = getPossibleNeighbor(r, c, 2);
                if (!otherPrototypesName.isEmpty()) {
                    for (int i = otherPrototypesName.size() - 1; i >= 0; i--) {
                        String otherPrototype = otherPrototypesName.get(i);
                        if (!possibleNeightbors.contains(otherPrototype)) {
                            otherPossiblePrototype.remove(i);
                            if (!stack.contains(eastCoords)) {
                                stack.add(eastCoords);
                            }
                        }
                    }
                }
            }
        }
    }

    public void iterate() {
        int[] coords = getMinEntropyCoords();
        collapse(coords[0], coords[1]);
        propagate(coords);
    }

    private void doMagic() {
        while (!isCollapsed()) {
            iterate();
        }
    }

    public Tile[][] getMap() {
        doMagic();
        for (int r = 0; r < TILES; r++) {
            for (int c = 0; c < TILES; c++) {
                if (map[r][c] == null)
                    map[r][c] = new TileNull();
            }
        }
        return map;
    }
}
