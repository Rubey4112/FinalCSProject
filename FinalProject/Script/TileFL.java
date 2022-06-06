public class TileFL extends Tile {
    
    public TileFL(ScenePanel scene) {
        super(Sprite.brickFloor,
                "FL",
                false,
                false,
                scene);
    }
    public void collide(int x, int y, Entity entity, int eX, int eY) {
        
    }
}