package com.techelevator.model;

import java.util.Objects;

public class Venues {
    private int venueId;
    private String venueName;

    public Venues() {
    }

    public Venues(int venueId, String venueName) {
        this.venueId = venueId;
        this.venueName = venueName;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venues venues = (Venues) o;
        return venueId == venues.venueId &&
                venueName == venues.venueName
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(venueId, venueName);
    }

    @Override
    public String toString() {
        return "Venues{" +
                "venueId=" + venueId +
                ", venueName=" + venueName +
                '}';
    }
}
