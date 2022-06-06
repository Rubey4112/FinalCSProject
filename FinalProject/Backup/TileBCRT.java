public class TileBCRT extends Tile {
    /**
     * Brick Corner Right Top
     */
    public TileBCRT() {
        super(Sprite.brickCornerRightTop,
                "BCRT",
                new String[][] { { "A", "BB", "BCRB", "BCLB" },
                        { "BSR", "BBLB" },
                        { "A", "BSL", "BCLT", "BCLB" },
                        { "BT", "BBLB" } },
                2,
                false,
                false);
    }

    public void collide(int x, int y, Player player) {
    }
}