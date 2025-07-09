import java.io.File;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class MidiPlayer {

    private Sequencer sequencer; // 用於播放 MIDI 序列

    // 載入並準備播放 MIDI 檔案
    public boolean loadMidi(String filePath) throws Exception {
        File midiFile = new File(filePath);
        if (!midiFile.exists()) {
            return false;
        }

        // 獲取 Sequencer 實例
        sequencer = MidiSystem.getSequencer();
        sequencer.open(); // 打開 Sequencer

        // 從檔案中獲取 MIDI 序列
        Sequence sequence = MidiSystem.getSequence(midiFile);
        sequencer.setSequence(sequence); // 設定要播放的序列

        return false;
    }
    public boolean getSelected(){
        try {
            return sequencer.isOpen();
        }catch (NullPointerException npe){
            return false;
        }
    }
    // 播放 MIDI
    public boolean play() {
        if (sequencer != null && sequencer.isOpen()) {
            if (!sequencer.isRunning()) {
                sequencer.start();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    // 暫停 MIDI
    public boolean pause() {
        if (sequencer != null && sequencer.isOpen() && sequencer.isRunning()) {
            sequencer.stop(); // 停止播放 (即暫停)
            return true;
        }
        return false;
    }

    // 恢復播放 MIDI
    public boolean resume() {
        if (sequencer != null && sequencer.isOpen() && !sequencer.isRunning()) {
            // 從停止的地方繼續播放
            sequencer.start();
            return true;
        }
        return false;
    }
    public void setLooping(boolean loop) {
        if (sequencer != null && sequencer.isOpen()) {
            if (loop) {
                sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            } else {
                sequencer.setLoopCount(0);
            }
        }
    }
    public boolean stop() {
        if (sequencer != null && sequencer.isOpen()) {
            sequencer.stop();
            sequencer.setTickPosition(0); // 重置到開頭
            return true;
        }
        return false;
    }

    // 關閉 Sequencer
    public boolean close() {
        if (sequencer != null && sequencer.isOpen()) {
            sequencer.close();
            return true;
        }
        return false;
    }

    // 檢查 MIDI 是否正在播放
    public boolean isPlaying() {
        return sequencer != null && sequencer.isOpen() && sequencer.isRunning();
    }

    // 獲取 MIDI 的總時長 (以 tick 為單位)
    public long getTotalLengthInTicks() {
        return sequencer != null && sequencer.getSequence() != null ? sequencer.getSequence().getTickLength() : 0;
    }

    // 獲取當前播放位置 (以 tick 為單位)
    public long getCurrentTickPosition() {
        return sequencer != null && sequencer.isOpen() ? sequencer.getTickPosition() : 0;
    }

    // 設定播放位置 (以 tick 為單位)
    public boolean setTickPosition(long tick) {
        if (sequencer != null && sequencer.isOpen() && tick >= 0 && tick <= getTotalLengthInTicks()) {
            sequencer.setTickPosition(tick);
            return true;
        }
        return false;
    }
}