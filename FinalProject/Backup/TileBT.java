public class TileBT extends Tile {
    /**
     * Brick Top
     */
    public TileBT() {
        super(Sprite.brickTop,
                "BT",
                new String[][] { { "A", "BB", "BCRB", "BCLB" }, 
                { "BW" },
                { "BT", "BBRB", "BCRT" }, 
                { "BT", "BBLB", "BCLT" } },
                5,
                false,
                false);
    }

    public void collide(int x, int y, Player player) {}
}