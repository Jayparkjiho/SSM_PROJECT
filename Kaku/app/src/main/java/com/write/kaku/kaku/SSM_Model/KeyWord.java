package com.write.kaku.kaku.SSM_Model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class KeyWord {

    public String keyWord;
    public String openedAt;

    public  KeyWord(){

    }

    public KeyWord(String keyWord, String openedAt) {
        this.keyWord = keyWord;
        this.openedAt = openedAt;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("openedAt", openedAt);
        result.put("keyWord", keyWord);

        return result;
    }
    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getOpenedAt() {
        return openedAt;
    }

    public void setOpenedAt(String openedAt) {
        this.openedAt = openedAt;
    }
}
