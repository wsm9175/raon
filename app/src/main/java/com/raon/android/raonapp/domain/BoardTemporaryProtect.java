package com.raon.android.raonapp.domain;

import java.io.Serializable;

public class BoardTemporaryProtect implements Serializable {
    private String id;
    private long createAt;
    private double lat;
    private String location;
    private double lon;
    private String name;

    public BoardTemporaryProtect() {}

    public BoardTemporaryProtect(String id, long createAt, double lat, String location, double lon, String name) {
        this.id = id;
        this.createAt = createAt;
        this.lat = lat;
        this.location = location;
        this.lon = lon;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BoardTemporaryProtect{" +
                "id='" + id + '\'' +
                ", createAt=" + createAt +
                ", lat=" + lat +
                ", location='" + location + '\'' +
                ", lon=" + lon +
                ", name='" + name + '\'' +
                '}';
    }
}
