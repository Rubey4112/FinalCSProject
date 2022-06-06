public class TileBCRB extends Tile {
    /**
     * Brick Corner Right Bottom
     */
    public TileBCRB() {
        super(Sprite.brickCornerRightBottom,
                "BCRB",
                new String[][] { { "BSR", "BBLT" },
                        { "A", "BT", "BCRT", "BCLT" },
                        { "A", "BSL", "BCLT", "BCLB" },
                        { "BB", "BBLT" } },
                2,
                false,
                false);
    }

    public void collide(int x, int y, Player player) {
    }
}