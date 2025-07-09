package tools;

import enums.Languages;
public class Lang {
    public final String[] langl = new String[]{
            "English",
            "繁體中文"
    };
    public final String[][] strs = new String[][]{
            new String[]{
                    "Play",
                    "Stop",
                    "Pause",
                    "Failed to init player.",
                    "Failed to play music."
            },
            new String[]{
                    "播放",
                    "停止",
                    "暫停",
                    "無法初始化播放器","無法播放音樂"
            }
    };
    public String translate(String english, Languages lang){
        try {
            return strs[Ary.srhInd(langl, lang)][Ary.srhInd(strs[0], english)];
        }catch (Exception e){
            return english;
        }
    }
}
