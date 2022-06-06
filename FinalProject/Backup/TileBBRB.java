public class TileBBRB extends Tile {
    /**
     * Brick Bend Right Bottom
     */
    public TileBBRB() {
        super(Sprite.brickBendRightBottom,
                "BBRB",
                new String[][] { { "BSL", "BCLT", "BBRT" },
                        { "BW" },
                        { "FL", "BW" },
                        { "BT", "BCLT", "BBLB" } },
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
            if (x - pX < 0) {
                int distances = x + SIZE - pX;
                player.x += distances;
            }
        }
    }
}