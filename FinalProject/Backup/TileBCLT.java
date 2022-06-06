public class TileBCLT extends Tile {
    /**
     * Brick Corner Left Top
     */
    public TileBCLT() {
        super(Sprite.brickCornerLeftTop,
                "BCLT",
                new String[][] { { "A", "BB", "BCRB", "BCLB" },
                        { "BSL", "BBRB" },
                        { "BT", "BBRB" },
                        { "A", "BSR", "BCRT", "BCRB" } },
                2,
                false,
                false);
    }

    public void collide(int x, int y, Player player) {
    }
}