public class TileA extends Tile {
    /**
     * Air
     */
    public TileA() {
        super(Sprite.air,
                "A",
                new String[][] { { "A", "BB", "BCRB", "BCLB" },
                        { "A", "BT", "BCRT", "BCLT" },
                        { "A", "BSL", "BCLT", "BCLB" },
                        { "A", "BSR", "BCRT", "BCRB" } },
                2,
                false,
                false);
    }

    public void collide(int x, int y, Player player) {
    }
}