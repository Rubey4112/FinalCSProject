public class TileBW extends Tile {
    
    public TileBW(ScenePanel scene) {
        super(Sprite.brickWall,
                "BW",
                true,
                true,
                scene);
    }

    public void collide(int x, int y, Entity entity, int eX, int eY) {
        boolean x_overlaps = (x < eX + SIZE) && (x + SIZE > eX);
        boolean y_overlaps = (y < eY + SIZE) && (y + SIZE > eY);
        // Magic
        if (x_overlaps && y_overlaps) {
            if (Math.abs(y - eY) <= Math.abs(x - eX)) {
                if (x - eX >= 0) {
                    int distances = x - (eX + SIZE);
                    entity.setX(entity.getX() + distances);
                }
                if (x - eX < 0) {
                    int distances = x + SIZE - eX;
                    entity.setX(entity.getX() + distances);
                }
            }
            if (Math.abs(x - eX) < Math.abs(y - eY)) {
                if (y - eY >= 0) {
                    int distances = y - (eY + SIZE);
                    entity.setY(entity.getY() + distances);
                }
                if (y - eY < 0) {
                    int distances = y + SIZE - eY;
                    entity.setY(entity.getY() + distances);
                }
            }
        }
    }
}
