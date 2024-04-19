package com.techelevator.dao;

import com.techelevator.model.DayTime;
import com.techelevator.model.Landmark;
import java.util.List;

public interface LandmarkDao {
    /**
     *  Interface method declarations
     */

    Landmark getLandmarkById(int id);

    List<Landmark> getLandmarks();

    Landmark getLandmarkByName(String landmarkName);

    int createLandmark(Landmark landmark);

    Landmark updateLandmark(Landmark landmark);

    List<DayTime> getDayTimesByLandmarkId (int id);

    void addItinLandmarkCrosswalk(int inputItinId, List<Integer> inputLMIds);

    int updateItinLandmarkCrosswalk(int inputItinId, List<Integer> inputLMIds);

    int deleteItinLandmarkCrosswalkRecords(int itineraryId);

    int updateLandmarkLikes(int landmarkId, int likeCounter);

    int updateLandmarkDislikes(int landmarkId, int dislikeCounter);

}
