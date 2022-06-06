public class TileBBLT extends Tile {
    /**
     * Brick Bend Left Top
     */
    public TileBBLT() {
        super(Sprite.brickBendLeftTop,
                "BBLT",
                new String[][] { { "FL" },
                        { "BSR", "BCRB", "BBLB" },
                        { "BB", "BCRB", "BBRT" },
                        { "FL" } },
                5,
                true,
                true);
    }

    public void collide(int x, int y, Player player) {
        int pX = player.getX();
        int pY = player.getY();
        boolean x_overlaps = (x < pX + SIZE) && (x + SIZE > pX);
        boolean y_overlaps = (y < pY + SIZE) && (y + SIZE > pY);
        if (x_overlaps && y_overlaps) {
            if (Math.abs(y - pY) <= Math.abs(x - pX)) {
                if (x - pX > 0) {
                    int distances = x - (pX + SIZE);
                    player.x += distances;
                }
            }
            if (Math.abs(x - pX) < Math.abs(y - pY)) {
                if (y - pY > 0) {
                    int distances = y - (pY + SIZE);
                    player.y += distances;
                }
            }
        }
    }
}