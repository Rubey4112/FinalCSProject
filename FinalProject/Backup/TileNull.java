public class TileNull extends Tile {
    /**
     * Air
     */
    public TileNull() {
        super(Sprite.tileNull,
                null,
                null,
                0,
                false,
                false);
    }

    public void collide(int x, int y, Player player) {
    }
}