import java.awt.*;

public class Rocket extends Rectangle {
    private Color Color;
    private int dy;

    // constructors
    public Rocket(int pX, int pY) {
        width = 10;
        height = 10;
        x = pX - width / 2;
        y = pY + height / 2;

        Color = new Color(10, 10, 10);
        dy = -10;
    }

    // instance methods
    public void move() {
        if (y > -10) {
            y += dy;
        }
    }

    public boolean offScreen() {
        return y <= -10;
    }

    public void draw(Graphics sceneBuffer) {
        sceneBuffer.setColor(Color);
        sceneBuffer.fillRect(x, y, width, height);
    }
}
