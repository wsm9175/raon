package com.raon.android.raonapp.domain;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BoardAdopt {
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
