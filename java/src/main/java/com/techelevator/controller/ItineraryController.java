package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ItineraryController {
    /**
     *  Declarations of DAO variables
     */
    private LandmarkDao landmarkDao;
    private ItineraryDao itineraryDao;
    private StartPointDao startPointDao;
    private ImageItemDao imageItemDao;
    private VenuesDao venuesDao;


    /**
     * Class constructor specifying number of objects to create.
     *
     * @param landmarkDao       The local Landmark Dao object
     * @param itineraryDao      The local Itinerary Dao object
     * @param startPointDao     The local Start Point Dao object
     * @param venuesDao         The local Venues Dao object
     * @param imageItemDao      The local Image Item Dao object
     */
    public ItineraryController(LandmarkDao landmarkDao, ItineraryDao itineraryDao, StartPointDao startPointDao, VenuesDao venuesDao, ImageItemDao imageItemDao) {
        this.landmarkDao = landmarkDao;
        this.itineraryDao = itineraryDao;
        this.startPointDao = startPointDao;
        this.venuesDao = venuesDao;
        this.imageItemDao = imageItemDao;
    }


    //Landmarks section
    /**
     * Endpoint for Getting a List of all Landmarks
     *
     * @return      List of All Landmarks
     */
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/landmarks", method = RequestMethod.GET)
    public List<Landmark> getLandmarks() {
        try {
            return landmarkDao.getLandmarks();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint for Getting a single Landmark
     *
     * @param id        Integer value of the
     * @return          An individual Landmark
     */
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/landmarks/{id}", method = RequestMethod.GET)
    public Landmark getLandmarkById(@PathVariable int id) {
        try {
            return landmarkDao.getLandmarkById(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Add List of Landmark IDs to an Itinerary
     *
     * @param inputLMints       The list of Landmark IDs, in order
     * @param itinID            The Itinerary ID
     */
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/itinerary/{itinID}/landmark", method = RequestMethod.POST)
    public void addItinLandmarkCrosswalk(@RequestBody List<Integer> inputLMints, @PathVariable int itinID ) {
        try {
            landmarkDao.addItinLandmarkCrosswalk(itinID, inputLMints);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update List of Landmark IDs for an Itinerary
     *
     * @param inputLMints       The list of Landmark IDs, in order
     * @param inputItinId       The Itinerary ID
     * @return      The number of rows affected in the Itinerary-Landmark crosswalk table
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/updateitinerarylandmarks/{inputItinId}", method = RequestMethod.PUT)
    public int updateItinLandmarkCrosswalk(@RequestBody List<Integer> inputLMints, @PathVariable int inputItinId) {

        Itinerary localItin;
        try {
            localItin = itineraryDao.getItineraryById(inputItinId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (localItin != null) {
            try {
                return landmarkDao.updateItinLandmarkCrosswalk(inputItinId, inputLMints);
            } catch (DaoException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates the Like count for the Landmark ID, by the count value provided.
     *
     * @param inputLandmarkId       The Landmark ID
     * @param inputCount            The value to increment the Like count by
     * @return                      The new Like count total
     */
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/landmark/{inputLandmarkId}/like/{inputCount}", method = RequestMethod.PUT)
    public int updateLandmarkLikes(@PathVariable int inputLandmarkId, @PathVariable int inputCount) {
        Landmark localLM;

        try {
            localLM = landmarkDao.getLandmarkById(inputLandmarkId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (localLM != null) {
            try {
                return landmarkDao.updateLandmarkLikes(inputLandmarkId, inputCount);
            } catch (DaoException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates the Dislike count for the Landmark ID, by the count value provided.
     *
     * @param inputLandmarkId       The Landmark ID
     * @param inputCount            The value to increment the Dislike count by
     * @return                      The new Dislike count total
     */
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/landmark/{inputLandmarkId}/dislike/{inputCount}", method = RequestMethod.PUT)
    public int updateLandmarkDislikes(@PathVariable int inputLandmarkId, @PathVariable int inputCount) {

        Landmark localLM;
        try {
            localLM = landmarkDao.getLandmarkById(inputLandmarkId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (localLM != null) {
            try {
                return landmarkDao.updateLandmarkDislikes(inputLandmarkId, inputCount);
            } catch (DaoException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


    //Daytimes section
    /**
     * Gets the list of Days and Times for a Landmark
     *
     * @param id        The Landmark ID
     * @return          The list of Daytime objects for that Landmark ID
     */
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/landmarks/{id}/daytimes", method = RequestMethod.GET)
    public List<DayTime> getDayTimesByLandmarkId(@PathVariable int id) {
        try {
            return landmarkDao.getDayTimesByLandmarkId(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Itinerary Section
    /**
     * Get the Itinerary by its Id
     *
     * @param id    The Itinerary Id
     * @return      The Itinerary object for the provided Id
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/itinerary/{id}", method = RequestMethod.GET)
    public Itinerary getItineraryById(@PathVariable int id) {
        try {
            return itineraryDao.getItineraryById(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get a summary list of Itinerary Ids and Itinerary Names for the currently logged in user
     *
     * @param id    The User Id of the logged in person
     * @return      The summary List of Itineraries for the provided User Id
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/itineraries/{id}", method = RequestMethod.GET)
    public List<Itinerary> getItinSummaryListByUserId(@PathVariable int id) {
        try {
            return itineraryDao.getItinSummaryListByUserId(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates the base Itinerary Object
     *
     * @param inputItinerary    In-bound Itinerary Object (JSON - see API doc) with minimal required fields populated
     * @return                  The newly created Itinerary object, including the Itinerary Id
     */
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/itinerary", method = RequestMethod.POST)
    public Itinerary createItinerary(@RequestBody Itinerary inputItinerary) {
        try {
            return itineraryDao.createItinerary(inputItinerary);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates the Start Point object for the Itinerary Id
     *
     * @param inputStartPoint   In-bound Start Point Object (JSON - see API doc) with minimal required fields populated
     * @return                  The newly created Start Point object, including the Start Point Id
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/itinerary/startpoint", method = RequestMethod.POST)
    public StartPoint createStartPointByItineraryId(@RequestBody StartPoint inputStartPoint) {
        try {
            return startPointDao.createStartPoint(inputStartPoint);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Adds the Start Point to the Itinerary, by passing the Itinerary ID, then it grabs the Start Point ID
     * and populates the Start Point ID in the Itinerary table
     *
     * @param inputItinId       The Itinerary Id
     * @return                  The Itinerary Object, with the Start Point Id field populated
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/itinerary/{inputItinId}/update", method = RequestMethod.PUT)
    public Itinerary updateItinWithStartPointId(@PathVariable int inputItinId) {

        Itinerary localItin;
        try {
            localItin = itineraryDao.getItineraryById(inputItinId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (localItin != null) {
            try {
                return itineraryDao.updateItinWithStartPointId(inputItinId);
            } catch (DaoException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates the existing Itinerary record with the in-bound Itinerary Object with the required fields populated
     * with the changes
     *
     * @param inputItinerary        In-bound Itinerary Object (JSON - see API doc) with minimal required fields populated
     * @return                      The number of rows affected (should be 1)
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/itinerary/updateobject", method = RequestMethod.PUT)
    public int updateItineraryByItinerary(@RequestBody Itinerary inputItinerary) {

        Itinerary localItin;
        try {
            localItin = itineraryDao.getItineraryById(inputItinerary.getItineraryId());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (localItin != null) { //localItin.getItineraryId() > 0
            try {
                return itineraryDao.updateItinerary(inputItinerary);
            } catch (DaoException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * "Deletes" (via updates to the deleted flag and deleted date) the Itinerary record
     *
     * @param inputItinId       The Itinerary Id to be "deleted"
     * @return                  The number of rows affected (should be 1)
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/itinerary/{inputItinId}/delete", method = RequestMethod.PUT)
    public int deleteItineraryByItineraryId(@PathVariable int inputItinId) {

        Itinerary localItin;
        try {
            localItin = itineraryDao.getItineraryById(inputItinId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (localItin != null) {
            try {
                return itineraryDao.deleteItinerary(inputItinId);
            } catch (DaoException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * "Deletes" (via updates to the deleted flag and deleted date) the Itinerary-Landmark Crosswalk records
     *
     * @param inputItinId       The Itinerary Id used to "delete" the crosswalk records
     * @return                  The number of rows affected
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/itinerary/{inputItinId}/landmark/delete", method = RequestMethod.PUT)
    public int deleteItinLandmarkCrosswalkRecordsByItineraryId(@PathVariable int inputItinId) {

        Itinerary localItin;
        try {
            localItin = itineraryDao.getItineraryById(inputItinId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (localItin != null) {
            try {
                return landmarkDao.deleteItinLandmarkCrosswalkRecords(inputItinId);
            } catch (DaoException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


    //Start Point section
    /**
     * Get a Start Point object by the Itinerary Id
     *
     * @param id        The Itinerary Id (Start Point object must exists with the Itinerary Id populated within it)
     * @return          The Start Point object related to the Itinerary Id
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/startpoint/{id}", method = RequestMethod.GET)
    public StartPoint getStartPointByItineraryId(@PathVariable int id) {
        try {
            return startPointDao.getStartPointByItineraryId(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates the updatable fields in the Start Point record, with the data populated in the Start Point
     * object passed in.
     *
     * @param inputStartPoint       Start Point object with updated fields populated
     * @return                      The number of rows affected (should be 1)
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/startpoint/updateobject", method = RequestMethod.PUT)
    public int updateStartPointByStartPoint(@RequestBody StartPoint inputStartPoint) {

        Itinerary localItin;
        try {
            localItin = itineraryDao.getItineraryById(inputStartPoint.getItineraryId());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (localItin != null) {
            try {
                return startPointDao.updateStartPoint(inputStartPoint);
            } catch (DaoException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * "Deletes" (via updates to the deleted flag and deleted date) the Start Point record
     * related to the Itinerary Id
     *
     * @param inputItinId       The Itinerary Id, which is used to identify the Start Point record
     * @return                  The number of rows affected (should be 1)
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/startpoint/{inputItinId}/delete", method = RequestMethod.PUT)
    public int deleteStartPointByItineraryId(@PathVariable int inputItinId) {

        Itinerary localItin;
        try {
            localItin = itineraryDao.getItineraryById(inputItinId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (localItin != null) {
            try {
                return startPointDao.deleteStartPoint(inputItinId);
            } catch (DaoException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


    //Venues section
    /**
     * Gets a list of Venues (Id and Name of Type of Venue)
     *
     * @return      A list of Venues (Id and Name of Type of Venue)
     */
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/venues", method = RequestMethod.GET)
    public List<Venues> getVenues() {
        try {
            return venuesDao.getVenues();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Images section
    /**
     * Adds an image to the Landmark Images table (via an ImageItem JSON object - see API doc)
     *
     * @param inputImageItem
     * @return      The newly created ImageIem object, including the Id field.
     */
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/images", method = RequestMethod.POST)
    public ImageItem addLandmarkImage(@RequestBody ImageItem inputImageItem) {
        try {
            return imageItemDao.addLandmarkImage(inputImageItem);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets a list of ImageItem objects for the specified Landmark Id
     *
     *
     * @param landmarkId        The Landmark Id used to retrieve the records
     * @return                  The List of ImageItems related to the Landmark Id
     */
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/images/{landmarkId}", method = RequestMethod.GET)
    public List<ImageItem> getImagesListByLandmarkId(@PathVariable int landmarkId) {
        try {
            return imageItemDao.getImagesListByLandmarkId(landmarkId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Mapbox Section
    /**
     * Gets the MapBox API Token that is stored in the database
     *
     *
     * @return      The String representation of the MapBox API Token
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/mapbox/token", method = RequestMethod.GET)
    public String getMapBoxAPIToken() {
        try {
            return itineraryDao.getMapBoxAPIToken();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Generates the MapBox URL for the supplied Itinerary Id
     *
     * @param inputItineraryId      The Itinerary Id used to help generate the MapBox URL
     * @return                      The generated MapBox URL string
     */
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/mapboxURL/{inputItineraryId}", method = RequestMethod.GET)
    public String constructMBoxAPIURL(@PathVariable int inputItineraryId) {
        try {
            return itineraryDao.constructMBoxAPIURL(inputItineraryId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
