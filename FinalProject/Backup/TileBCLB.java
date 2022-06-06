public class TileBCLB extends Tile {
    /**
     * Brick Corner Left Bottom
     */
    public TileBCLB() {
        super(Sprite.brickCornerLeftBottom,
                "BCLB",
                new String[][] { { "BSL", "BBRT" },
                        { "A", "BT", "BCRT", "BCLT" },
                        { "BB", "BBRT" },
                        { "A", "BSR", "BCRT", "BCRB" } },
                2,
                false,
                false);
    }

    public void collide(int x, int y, Player player) {
    }
}