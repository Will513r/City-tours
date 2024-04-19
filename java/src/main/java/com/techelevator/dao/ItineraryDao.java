package com.techelevator.dao;

import com.techelevator.model.Itinerary;
import java.util.List;

public interface ItineraryDao {
    /**
     *  Interface method declarations
     */
    Itinerary getItineraryById(int itineraryId);

    List<Itinerary> getItinSummaryListByUserId(int id);

    int updateItinerary(Itinerary inputItinerary);

    int deleteItinerary(int itineraryId);

    Itinerary updateItinWithStartPointId(int inputItinId);

    Itinerary createItinerary(Itinerary inputItinerary);

    String getMapBoxAPIToken();

    String constructMBoxAPIURL(int localItineraryId);
}
