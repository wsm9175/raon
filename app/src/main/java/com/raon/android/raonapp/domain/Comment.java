package com.raon.android.raonapp.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Comment implements Serializable {
    private String id;
    private String commentGroup;
    private String content;
    private long createAt;
    private boolean root;
    private String writerId;
    private String writerName;

    private HashMap<String, Comment> comments;

    public Comment(){}

    public Comment(String id, String commentGroup, String content, long createAt, boolean root, String writerId, String writerName, HashMap<String, Comment> comments) {
        this.id = id;
        this.commentGroup = commentGroup;
        this.content = content;
        this.createAt = createAt;
        this.root = root;
        this.writerId = writerId;
        this.writerName = writerName;
        this.comments = comments;
    }

    public HashMap<String, Comment> getComments() {
        return comments;
    }

    public void setComments(HashMap<String, Comment> comments) {
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentGroup() {
        return commentGroup;
    }

    public void setCommentGroup(String commentGroup) {
        this.commentGroup = commentGroup;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }
}
