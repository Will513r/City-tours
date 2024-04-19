package com.techelevator.model;

import java.util.Objects;

public class DayTime {
    private int dayTimeId;
    private int landmarkId;
    private String dayName;
    private String openTime;
    private String closeTime;

    public DayTime() {
    }

    public DayTime(int dayTimeId, int landmarkId, String dayName, String openTime, String closeTime) {
        this.dayTimeId = dayTimeId;
        this.landmarkId = landmarkId;
        this.dayName = dayName;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public int getDayTimeId() {
        return dayTimeId;
    }

    public void setDayTimeId(int dayTimeId) {
        this.dayTimeId = dayTimeId;
    }

    public int getLandmarkId() {
        return landmarkId;
    }

    public void setLandmarkId(int landmarkId) {
        this.landmarkId = landmarkId;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayTime dayTime = (DayTime) o;
        return dayTimeId == dayTime.dayTimeId &&
                landmarkId == dayTime.landmarkId &&
                dayName == dayTime.dayName &&
                openTime == dayTime.openTime &&
                closeTime == dayTime.closeTime
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayTimeId, landmarkId, dayName, openTime, closeTime);
    }

    @Override
    public String toString() {
        return "DayTime{" +
                "dayTimeId=" + dayTimeId +
                ", landmarkId=" + landmarkId +
                ", dayName=" + dayName +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                '}';
    }
}
