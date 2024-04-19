package com.techelevator.model;

import java.util.Objects;

public class StartPoint {
    private int startPointId;
    private String nameStartPoint;
    private String latitudeSP;
    private String longitudeSP;

    private int itineraryId;


    public StartPoint() {
    }

    public StartPoint(int startPointId, String nameStartPoint, String latitudeSP, String longitudeSP) {
        this.startPointId = startPointId;
        this.nameStartPoint = nameStartPoint;
        this.latitudeSP = latitudeSP;
        this.longitudeSP = longitudeSP;
    }

    public int getStartPointId() {
        return startPointId;
    }

    public void setStartPointId(int startPointId) {
        this.startPointId = startPointId;
    }

    public String getNameStartPoint() {
        return nameStartPoint;
    }

    public void setNameStartPoint(String nameStartPoint) {
        this.nameStartPoint = nameStartPoint;
    }

    public String getLatitudeSP() {
        return latitudeSP;
    }

    public void setLatitudeSP(String latitudeSP) {
        this.latitudeSP = latitudeSP;
    }

    public String getLongitudeSP() {
        return longitudeSP;
    }

    public void setLongitudeSP(String longitudeSP) {
        this.longitudeSP = longitudeSP;
    }

    public int getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(int itineraryId) {
        this.itineraryId = itineraryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StartPoint startPoint = (StartPoint) o;
        return startPointId == startPoint.startPointId &&
                nameStartPoint == startPoint.nameStartPoint &&
                latitudeSP == startPoint.latitudeSP &&
                longitudeSP == startPoint.longitudeSP
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPointId, nameStartPoint, latitudeSP, longitudeSP);
    }

    @Override
    public String toString() {
        return "StartPoint{" +
                "startPointId=" + startPointId +
                ", nameStartPoint=" + nameStartPoint +
                ", latitudeSP=" + latitudeSP +
                ", longitudeSP=" + longitudeSP +
                '}';
    }
}
