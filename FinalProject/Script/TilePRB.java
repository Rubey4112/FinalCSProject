public class TilePRB extends Tile {
    
    public TilePRB(ScenePanel scene) {
        super(Sprite.portalRightBottom,
                "PRB",
                true,
                false,
                scene);
    }

    public void collide(int x, int y, Entity entity, int eX, int eY) {
        getScene().nextLevel();
    }
}