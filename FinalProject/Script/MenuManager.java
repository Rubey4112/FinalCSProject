import java.awt.CardLayout;

import javax.swing.JPanel;

public class MenuManager extends JPanel {
    private GUIPanel game;
    private StartScene start;
    private GameOverScene gameOver;
    private CardLayout cl;
    private AudioManager audio;

    public MenuManager() {
        audio = new AudioManager();
        setLayout(new CardLayout());
        start = new StartScene(this, audio);
        gameOver = new GameOverScene(this, audio);
        game = new GUIPanel(this, audio);
        add(start, "Start Card");
        add(gameOver, "Game Over Card");
        add(game, "Game Card");
        cl = (CardLayout)(getLayout());        
    }

    public void gameScene() {
        cl.show(this, "Game Card");
        game.start();
    }

    public void gameOver(int floorVal) {
        game.stop();
        gameOver.setFloor(floorVal);
        cl.show(this, "Game Over Card");
    }
}
