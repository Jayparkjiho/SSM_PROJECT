package com.write.kaku.kaku.SSM_Model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Post {

    public String uid;
    public String content;
    public String createdAt;
    public String openedAt;
    public String updateAt;
    public String keyWord;
    public String user_uid;
    public int bookmarkCount = 0;

    public Post(){

    }

    public Post(String uid, String content, String createdAt, String openedAt, String updateAt, String keyWord, String user_uid, int bookmarkCount) {
        this.uid = uid;
        this.content = content;
        this.createdAt = createdAt;
        this.openedAt = openedAt;
        this.updateAt = updateAt;
        this.keyWord = keyWord;
        this.user_uid = user_uid;
        this.bookmarkCount = bookmarkCount;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("content", content);
        result.put("createdAt", createdAt);
        result.put("openedAt", openedAt);
        result.put("updateAt", updateAt);
        result.put("keyWord", keyWord);
        result.put("user_uid", user_uid);
        result.put("bookmarkCount", bookmarkCount);

        return result;
    }


}
