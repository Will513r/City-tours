package com.techelevator.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Itinerary {
    private int itineraryId;
    private String itineraryName;
    private int userId;
    private StartPoint startPoint;
    private String itineraryDate;
    private List<Landmark> landmarks;

    public Itinerary() {
    }

    public Itinerary(int itineraryId, String itineraryName, int userId, StartPoint startPoint, String itineraryDate, List<Landmark> landmarks) {
        this.itineraryId = itineraryId;
        this.itineraryName = itineraryName;
        this.userId = userId;
        this.startPoint = startPoint;
        this.itineraryDate = itineraryDate;
        //this.landmarks = landmarks;
    }

    public int getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(int itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getItineraryName() {
        return itineraryName;
    }

    public void setItineraryName(String itineraryName) {
        this.itineraryName = itineraryName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public StartPoint getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(StartPoint startPoint) {
        this.startPoint = startPoint;
    }

    public String getItineraryDate() {
        return itineraryDate;
    }

    public void setItineraryDate(String itineraryDate) {
        this.itineraryDate = itineraryDate;
    }

    public List<Landmark> getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(List<Landmark> landmarks) {
        this.landmarks = landmarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Itinerary itinerary = (Itinerary) o;
        return itineraryId == itinerary.itineraryId &&
                itineraryName == itinerary.itineraryName &&
                userId == itinerary.userId &&
                Objects.equals(startPoint, itinerary.startPoint) &&
                itineraryDate == itinerary.itineraryDate &&
                Objects.equals(landmarks, itinerary.landmarks)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itineraryId, itineraryName, userId, startPoint, itineraryDate, landmarks);
    }

    @Override
    public String toString() {
        return "Itinerary{" +
                "itineraryId=" + itineraryId +
                ", itineraryName=" + itineraryName +
                ", userId=" + userId +
                ", startPoint=" + startPoint +
                ", itineraryDate=" + itineraryDate +
                ", landmarks=" + landmarks +
                '}';
    }
}
