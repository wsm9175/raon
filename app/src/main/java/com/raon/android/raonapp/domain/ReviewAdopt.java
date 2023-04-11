package com.raon.android.raonapp.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class ReviewAdopt implements Serializable {
    private long createAt;
    private List<String> imagePath;
    private String text;
    private String title;
    private String writerName;
    private String writerId;

    private HashMap<String, Comment> comments;

    public ReviewAdopt(){}

    public ReviewAdopt(long createAt, List<String> imagePath, String text, String title, String writerName, String writerId, HashMap<String, Comment> comments) {
        this.createAt = createAt;
        this.imagePath = imagePath;
        this.text = text;
        this.title = title;
        this.writerName = writerName;
        this.writerId = writerId;
        this.comments = comments;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public List<String> getImagePath() {
        return imagePath;
    }

    public void setImagePath(List<String> imagePath) {
        this.imagePath = imagePath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public HashMap<String, Comment> getComments() {
        return comments;
    }

    public void setComments(HashMap<String, Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "ReviewAdopt{" +
                "createAt=" + createAt +
                ", imagePath=" + imagePath +
                ", text='" + text + '\'' +
                ", title='" + title + '\'' +
                ", writerName='" + writerName + '\'' +
                ", writerId='" + writerId + '\'' +
                ", comments=" + comments +
                '}';
    }
}
