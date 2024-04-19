package com.techelevator.model;

import java.util.List;
import java.util.Objects;

public class Landmark {
    private int landmarkId;
    private String landmarkName;
    private String description;
    private String venueTypeName;
    private List<DayTime> dayTimes;
    private String longitudeLM;
    private String latitudeLM;
    private List<ImageItem> imageURLs;
    private String address;
    private int landmarkLike;
    private int landmarkDislike;

    public Landmark() {
    }

    //We do not know if we need this, temporary fix
    public Landmark(int landmarkId, String landmarkName, String description, String venueTypeName, String longitudeLM, String latitudeLM, String address, int landmarkLike, int landmarkDislike) {
        this.landmarkId = landmarkId;
        this.landmarkName = landmarkName;
        this.description = description;
        this.venueTypeName = venueTypeName;
        this.longitudeLM = longitudeLM;
        this.latitudeLM = latitudeLM;
        this.address = address;
        this.landmarkLike = landmarkLike;
        this.landmarkDislike = landmarkDislike;
    }

    public Landmark(int landmarkId, String landmarkName, String description, String venueTypeName, List<DayTime> dayTimes, String longitudeLM, String latitudeLM, List<ImageItem> imageURLs, String address, int landmarkLike, int landmarkDislike) {
        this.landmarkId = landmarkId;
        this.landmarkName = landmarkName;
        this.description = description;
        this.venueTypeName = venueTypeName;
        this.dayTimes = dayTimes;
        this.longitudeLM = longitudeLM;
        this.latitudeLM = latitudeLM;
        this.imageURLs = imageURLs;
        this.address = address;
        this.landmarkLike = landmarkLike;
        this.landmarkDislike = landmarkDislike;
    }

    public int getLandmarkId() {
        return landmarkId;
    }

    public void setLandmarkId(int landmarkId) {
        this.landmarkId = landmarkId;
    }

    public String getLandmarkName() {
        return landmarkName;
    }

    public void setLandmarkName(String landmarkName) {
        this.landmarkName = landmarkName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVenueTypeName() {
        return venueTypeName;
    }

    public void setVenueTypeName(String venueTypeName) {
        this.venueTypeName = venueTypeName;
    }

    public List<DayTime> getDayTimes() {
        return dayTimes;
    }

    public void setDayTimes(List<DayTime> dayTimes) {
        this.dayTimes = dayTimes;
    }

    public String getLongitudeLM() {
        return longitudeLM;
    }

    public void setLongitudeLM(String longitudeLM) {
        this.longitudeLM = longitudeLM;
    }

    public String getLatitudeLM() {
        return latitudeLM;
    }

    public void setLatitudeLM(String latitudeLM) {
        this.latitudeLM = latitudeLM;
    }

    public List<ImageItem> getImageURLs() {
        return imageURLs;
    }

    public void setImageURLs(List<ImageItem> imageURLs) {
        this.imageURLs = imageURLs;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLandmarkLike() {
        return landmarkLike;
    }

    public void setLandmarkLike(int landmarkLike) {
        this.landmarkLike = landmarkLike;
    }

    public int getLandmarkDislike() {
        return landmarkDislike;
    }

    public void setLandmarkDislike(int landmarkDislike) {
        this.landmarkDislike = landmarkDislike;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Landmark landmark = (Landmark) o;
        return landmarkId == landmark.landmarkId &&
                landmarkName == landmark.landmarkName &&
                description == landmark.description &&
                venueTypeName == landmark.venueTypeName &&
                Objects.equals(dayTimes, landmark.dayTimes) &&
                longitudeLM == landmark.longitudeLM &&
                latitudeLM == landmark.latitudeLM &&
                imageURLs == landmark.imageURLs &&
                landmarkLike == landmark.landmarkLike &&
                landmarkDislike == landmark.landmarkDislike
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(landmarkId, landmarkName, description, venueTypeName, dayTimes, longitudeLM, latitudeLM, imageURLs, landmarkLike, landmarkDislike);
    }

    @Override
    public String toString() {
        return "Landmark{" +
                "landmarkId=" + landmarkId +
                ", landmarkName=" + landmarkName +
                ", description=" + description +
                ", venueTypeName=" + venueTypeName +
                ", dayTimes=" + dayTimes +
                ", longitudeLM=" + longitudeLM +
                ", latitudeLM=" + latitudeLM +
                ", imageURLs=" + imageURLs +
                ", landmarkLike=" + landmarkLike +
                ", landmarkDislike" + landmarkDislike +
                '}';
    }
}
