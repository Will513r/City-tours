package com.techelevator.dao;

import com.techelevator.model.StartPoint;

public interface StartPointDao {
    /**
     *  Interface method declarations
     */

    StartPoint getStartPointByItineraryId(int id);

    int updateStartPoint(StartPoint inputStartPoint);

    StartPoint getStartPointByStartPointId(int id);

    StartPoint createStartPoint(StartPoint inputStartPoint);

    int deleteStartPoint(int itineraryId);

    int deleteStartPointByStartPointId(int startPointId);
}
