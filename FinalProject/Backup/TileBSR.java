public class TileBSR extends Tile {
    /**
     * Brick Side Right
     */
    public TileBSR() {
        super(Sprite.brickSideRight,
                "BSR",
                new String[][] { { "BSR", "BBLT", "BCRT" },
                        { "BSR", "BBLB", "BCRB" },
                        { "A", "BSL", "BCLT", "BCLB" },
                        { "BW", "FL" } },
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
            if (x - pX > 0) {
                int distances = x - (pX + SIZE);
                player.x += distances;
            }
        }
    }
}