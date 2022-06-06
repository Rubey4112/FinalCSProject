public class TileFL extends Tile {
    /**
     * Floor
     */
    public TileFL() {
        super(Sprite.brickFloor,
                "FL",
                new String[][] { { "FL", "BW" }, 
                { "FL", "BB", "BBLT", "BBRT" },
                { "FL", "BW", "BSR", "BBLT", "BBLB" }, 
                { "FL", "BW", "BSL", "BBRT", "BBRB" } },
                120,
                false,
                false);
    }
    public void collide(int x, int y, Player player) {}
}