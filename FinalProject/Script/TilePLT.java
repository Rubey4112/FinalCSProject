public class TilePLT extends Tile {
    
    public TilePLT(ScenePanel scene) {
        super(Sprite.portalLeftTop,
                "PLT",
                true,
                false,
                scene);
    }

    public void collide(int x, int y, Entity entity, int eX, int eY) {
        getScene().nextLevel();
    }
}