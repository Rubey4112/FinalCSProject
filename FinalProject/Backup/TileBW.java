public class TileBW extends Tile {
    /**
     * Brick Wall
     */
    public TileBW() {
        super(Sprite.brickWall,
                "BW",
                new String[][] { { "BT", "BBRB", "BBLB" },
                        { "FL" },
                        { "BW", "FL", "BSR", "BBLT", "BBLB" },
                        { "BW", "FL", "BSL", "BBRT", "BBRB" } },
                6,
                true,
                true);
    }

    public void collide(int x, int y, Player player) {
        int pX = player.getX();
        int pY = player.getY();
        boolean x_overlaps = (x < pX + SIZE) && (x + SIZE > pX);
        boolean y_overlaps = (y < pY + SIZE) && (y + SIZE > pY);
        // Magic
        if (x_overlaps && y_overlaps) {
            if (Math.abs(y - pY) < Math.abs(x - pX)) {
                if (x - pX > 0) {
                    int distances = x - (pX + SIZE);
                    player.x += distances;
                }
                if (x - pX < 0) {
                    int distances = x + SIZE - pX;
                    player.x += distances;
                }
            }
            if (Math.abs(x - pX) <= Math.abs(y - pY)) {
                if (y - pY < 0) {
                    int distances = y + SIZE - pY;
                    player.y += distances;
                }
            }
        }
    }
}
