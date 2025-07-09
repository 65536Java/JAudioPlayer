import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {

    private Clip clip;
    private long microsecondPosition;

    public void loadMusic(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(filePath);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
    }
    public boolean getSelected(){
        try {
            return clip.isOpen();
        }catch (NullPointerException npe){
            return false;
        }
    }

    public void play() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }

    public void pause() {
        if (clip != null && clip.isRunning()) {
            microsecondPosition = clip.getMicrosecondPosition();
            clip.stop();
        }
    }

    public void resume() {
        if (clip != null && !clip.isRunning() && microsecondPosition > 0) {
            clip.setMicrosecondPosition(microsecondPosition);
            clip.start();
        }
    }

    public void setLooping(boolean loop) {
        if (clip != null) {
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.loop(0);
            }
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
            microsecondPosition = 0;
        }
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }

    public long getTotalLengthInMicroseconds() {
        return clip != null ? clip.getMicrosecondLength() : 0;
    }

    public long getCurrentPositionInMicroseconds() {
        return clip != null ? clip.getMicrosecondPosition() : 0;
    }

    public void setMicrosecondPosition(long position) {
        if (clip != null && position >= 0 && position <= clip.getMicrosecondLength()) {
            clip.setMicrosecondPosition(position);
            this.microsecondPosition = position;
        }
    }
}