import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioManager {
    private Clip clipClick;
    private Clip clipDeath;
    private Clip clipEnemyHurt;
    private Clip clipNextLevel;
    private Clip clipPlayerHurt;

    private AudioInputStream audioClick;
    private AudioInputStream audioDeath;
    private AudioInputStream audioEnemyHurt;
    private AudioInputStream audioNextLevel;
    private AudioInputStream audioPlayerHurt;

    public AudioManager() {
        try {
            audioClick = AudioSystem.getAudioInputStream(new File("./SoundFX/click.wav").getAbsoluteFile());
            audioDeath = AudioSystem.getAudioInputStream(new File("./SoundFX/death.wav").getAbsoluteFile());
            audioEnemyHurt = AudioSystem.getAudioInputStream(new File("./SoundFX/enemyHurt.wav").getAbsoluteFile());
            audioNextLevel = AudioSystem.getAudioInputStream(new File("./SoundFX/nextLevel.wav").getAbsoluteFile());
            audioPlayerHurt = AudioSystem.getAudioInputStream(new File("./SoundFX/playerHurt.wav").getAbsoluteFile());

            clipClick = AudioSystem.getClip();
            clipDeath = AudioSystem.getClip();
            clipEnemyHurt = AudioSystem.getClip();
            clipNextLevel = AudioSystem.getClip();
            clipPlayerHurt = AudioSystem.getClip();
            clipClick.open(audioClick);
            clipDeath.open(audioDeath);
            clipEnemyHurt.open(audioEnemyHurt);
            clipNextLevel.open(audioNextLevel);
            clipPlayerHurt.open(audioPlayerHurt);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException a) {
        }
    }

    public void playClick() {
        clipClick.start();
        clipClick.addLineListener(e -> {
            if (e.getType() == LineEvent.Type.STOP) {
                clipClick.stop();
                clipClick.setMicrosecondPosition(0);
            }
        });
    }

    public void playDeath() {
        clipDeath.start();
        clipDeath.addLineListener(e -> {
            if (e.getType() == LineEvent.Type.STOP) {
                clipDeath.stop();
                clipDeath.setMicrosecondPosition(0);
            }
        });
    }

    public void playEnemyHurt() {
        clipEnemyHurt.start();
        clipEnemyHurt.addLineListener(e -> {
            if (e.getType() == LineEvent.Type.STOP) {
                clipEnemyHurt.stop();
                clipEnemyHurt.setMicrosecondPosition(0);
            }
        });
    }

    public void playNextLevel() {
        clipNextLevel.start();
        clipNextLevel.addLineListener(e -> {
            if (e.getType() == LineEvent.Type.STOP) {
                clipNextLevel.stop();
                clipNextLevel.setMicrosecondPosition(0);
            }
        });
    }

    public void playPlayerHurt() {
        clipPlayerHurt.start();
        clipPlayerHurt.addLineListener(e -> {
            if (e.getType() == LineEvent.Type.STOP) {
                clipPlayerHurt.stop();
                clipPlayerHurt.setMicrosecondPosition(0);
            }
        });
    }
}
