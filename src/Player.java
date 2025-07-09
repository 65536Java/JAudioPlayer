import java.io.File;

public class Player {
    String fileName = "";
    public boolean isMidi;
    public boolean isAudio;
    MusicPlayer musicPlayer = new MusicPlayer();
    MidiPlayer midiPlayer = new MidiPlayer();
    public String init(String filePath) throws Exception {
        String fileExtension = getFileExtension(filePath);
        this.isMidi = "mid".equalsIgnoreCase(fileExtension) || "midi".equalsIgnoreCase(fileExtension);
        this.isAudio = "wav".equalsIgnoreCase(fileExtension) || "au".equalsIgnoreCase(fileExtension) || "aiff".equalsIgnoreCase(fileExtension);
        if(isMidi){
            midiPlayer.loadMidi(filePath);
        } else if (isAudio) {
            musicPlayer.loadMusic(filePath);
        } else {
            std.out.println("Unsupported file format. Only support wav, au, aiff and mid file.");
            return "Unsupported file format. Only support wav, au, aiff and mid file.";
        }
        fileName = new File(filePath).getName();
        std.out.println("Success");
        return "Success";
    }
    public String getFilename(){
        return fileName;
    }
    public boolean getSelected(){
        return isMidi ? midiPlayer.getSelected() : musicPlayer.getSelected();
    }
    public boolean play(){
        try {
            if (isMidi) midiPlayer.play();
            else musicPlayer.play();
        }catch (NullPointerException npe){
            return false;
        }
        return true;
    }
    public boolean pause(){
        try {
            if (isMidi) midiPlayer.pause();
            else musicPlayer.pause();
        }catch (NullPointerException npe){
            return false;
        }
        return true;
    }
    public boolean resume(){
        try {
            if (isMidi) midiPlayer.resume();
            else musicPlayer.resume();
        }catch (NullPointerException npr){
            return false;
        }
        return true;
    }
    public boolean end(){
        try {
            if (isMidi) {
                midiPlayer.stop();
                midiPlayer.close();
            } else {
                musicPlayer.stop();
            }
        }catch (NullPointerException npr){
            return false;
        }
        return true;
    }
    public boolean stop(){
        try{
            if (isMidi) midiPlayer.stop();
            else musicPlayer.stop();
        }catch (NullPointerException npr){
            return false;
        }
        return true;
    }
    public long getPlayedMS(){
        if (isMidi) {
            return midiPlayer.getCurrentTickPosition();
        } else {
            return musicPlayer.getCurrentPositionInMicroseconds();
        }
    }
    public void loop(boolean bl){
        if (isMidi) {
            midiPlayer.setLooping(bl);
        } else {
            musicPlayer.setLooping(bl);
        }
    }
    public void setPlayedMS(long m){
        if (isMidi) {
            midiPlayer.setTickPosition(m);
        } else {
            musicPlayer.setMicrosecondPosition(m);
        }
    }
    public long getTotalMS(){
        if (isMidi) {
            return midiPlayer.getTotalLengthInTicks();
        } else {
            return musicPlayer.getTotalLengthInMicroseconds();
        }
    }
    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return "";
        }
        return fileName.substring(dotIndex + 1);
    }
}