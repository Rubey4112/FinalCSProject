public class TileBB extends Tile {
    /**
     * Brick Bottom
     */
    public TileBB() {
        super(Sprite.brickBottom,
                "BB",
                new String[][] { { "FL" },
                        { "A", "BT", "BCRT", "BCLT" },
                        { "BB", "BBRT", "BCRB" },
                        { "BB", "BBLT", "BCLB" } },
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
            if (y - pY > 0) {
                int distances = y - (pY + SIZE);
                player.y += distances;
            }
        }
    }
}