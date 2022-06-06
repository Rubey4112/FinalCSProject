public class TilePLB extends Tile {
    
    public TilePLB(ScenePanel scene) {
        super(Sprite.portalLeftBottom,
                "PLB",
                true,
                false,
                scene);
    }

    public void collide(int x, int y, Entity entity, int eX, int eY) {
        getScene().nextLevel();
    }
}