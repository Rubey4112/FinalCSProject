public class TileA extends Tile {
    
    public TileA(ScenePanel scene) {
        super(Sprite.air,
                "A",
                false,
                false,
                scene);
    }

    public void collide(int x, int y, Entity entity, int eX, int eY) {
    }
}