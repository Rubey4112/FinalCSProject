public class TilePRT extends Tile {

    public TilePRT(ScenePanel scene) {
        super(Sprite.portalRightTop,
                "PRT",
                true,
                false,
                scene);
    }

    public void collide(int x, int y, Entity entity, int eX, int eY) {
        getScene().nextLevel();
    }
}