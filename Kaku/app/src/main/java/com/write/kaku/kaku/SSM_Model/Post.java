package com.write.kaku.kaku.SSM_Model;

import java.util.Date;

public class Post {
    public int alignment;
    public int bookmarkCount;
    public String content;
    public Date createdAt;
    public Date openedAt;
    public Date updateAt;
    public String keyWord;
    public User user;

    public Post(){}

    public Post(int alignment, int bookmarkCount, String content, Date createdAt, Date openedAt, Date updateAt, String keyWord, User user) {
        this.alignment = alignment;
        this.bookmarkCount = bookmarkCount;
        this.content = content;
        this.createdAt = createdAt;
        this.openedAt = openedAt;
        this.updateAt = updateAt;
        this.keyWord = keyWord;
        this.user = user;
    }

    public int getAlignment() {
        return alignment;
    }

    public void setAlignment(int alignment) {
        this.alignment = alignment;
    }

    public int getBookmarkCount() {
        return bookmarkCount;
    }

    public void setBookmarkCount(int bookmarkCount) {
        this.bookmarkCount = bookmarkCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getOpenedAt() {
        return openedAt;
    }

    public void setOpenedAt(Date openedAt) {
        this.openedAt = openedAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
