package com.raon.android.raonapp.domain;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class BoardAdopt implements Serializable {
    private String id;
    private long createAt;
    private String imagePath;
    private double lat;
    private String location;
    private double lon;
    private String rescueSite;
    private String sex;
    private String variety;
    private String writer;
    private HashMap<String, Comment> comments;

    public BoardAdopt() {}

    public BoardAdopt(String id, long createAt, String imagePath, double lat, String location, double lon, String rescueSite, String sex, String variety, String writer) {
        this.id = id;
        this.createAt = createAt;
        this.imagePath = imagePath;
        this.lat = lat;
        this.location = location;
        this.lon = lon;
        this.rescueSite = rescueSite;
        this.sex = sex;
        this.variety = variety;
        this.writer = writer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getRescueSite() {
        return rescueSite;
    }

    public void setRescueSite(String rescueSite) {
        this.rescueSite = rescueSite;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public HashMap<String, Comment> getComments() {
        return comments;
    }

    public void setComments(HashMap<String, Comment> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public String toString() {
        return "BoardAdopt{" +
                "id='" + id + '\'' +
                ", createAt=" + createAt +
                ", imagePath='" + imagePath + '\'' +
                ", lat=" + lat +
                ", location='" + location + '\'' +
                ", lon='" + lon + '\'' +
                ", rescueSite='" + rescueSite + '\'' +
                ", sex='" + sex + '\'' +
                ", variety='" + variety + '\'' +
                ", writer='" + writer + '\'' +
                '}';
    }
}
