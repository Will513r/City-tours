package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Itinerary;
import com.techelevator.model.StartPoint;
import com.techelevator.model.Landmark;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcItineraryDao implements ItineraryDao {
    /**
     *  Declarations of variables and DAO Objects
     */
    private JdbcTemplate jdbcTemplate;

    private StartPointDao startPointDao;
    private LandmarkDao landmarkDao;

    /**
     * Class constructor specifying number of objects to create.
     *
     * @param jdbcTemplate
     * @param startPointDao
     * @param landmarkDao
     */
    public JdbcItineraryDao(JdbcTemplate jdbcTemplate, StartPointDao startPointDao, LandmarkDao landmarkDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.startPointDao = startPointDao;
        this.landmarkDao = landmarkDao;
    }

    /**
     * Gets the Map Box API Token
     *
     * @return      The Map Box API Token
     */
    @Override
    public String getMapBoxAPIToken(){

        String localToken = "";
        String sql = "SELECT map_box_token FROM public.mapboxapitoken;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            if (results.next()) {
                localToken = mapRowToToken(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return localToken;
    }

    /**
     * Generates the Map Box URL that is needed for the frontend API call to MapBox
     *
     * @param localItineraryId      Itinerary Id of the Itinerary that is used to fill in the components of the URL string
     * @return                      The generated Map Box URL
     */
    @Override
    public String constructMBoxAPIURL(int localItineraryId){
        Itinerary localItinerary = getItineraryById(localItineraryId);

        String baseURL = "https://api.mapbox.com/directions/v5/mapbox/driving/";
        String endURL = "?alternatives=true&geometries=geojson&language=en&overview=full&steps=true&access_token=";
        String mapBoxToken = getMapBoxAPIToken();
        String coordinates = "";

        //Startpoint - add to coordinates
        StartPoint localStartPoint = startPointDao.getStartPointByItineraryId(localItineraryId);
        coordinates = coordinates + localStartPoint.getLongitudeSP() + ',' + localStartPoint.getLatitudeSP() + ';';

        for(Landmark landmark : localItinerary.getLandmarks()){
            String LMLong = landmark.getLongitudeLM();
            String LMLat = landmark.getLatitudeLM();
            coordinates = coordinates + LMLong + "," + LMLat + ";";
        }
        coordinates = coordinates.substring(0, coordinates.length()-2);

        return baseURL + coordinates + endURL + mapBoxToken;
    }

    /**
     * Gets an Itinerary object
     *
     * @param itineraryId       Itinerary Id of the Itinerary to be returned
     * @return                  The Itinerary Object
     */
    @Override
    public Itinerary getItineraryById(int itineraryId) {
        Itinerary localItinerary = null;
        String sql = "SELECT itinerary_id, user_id, itinerary_name, itinerary_date, start_point_id " +
                "FROM public.itinerary " +
                "WHERE itinerary_id = ? " +
                "AND deletedflag = false;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, itineraryId);
            while (results.next()) {
                localItinerary = mapRowToItinerary(results);
                localItinerary.setStartPoint(startPointDao.getStartPointByItineraryId(itineraryId));

                Landmark localLandmark = null;
                List<Landmark> returnLandmarks = new ArrayList<>();
                List<Integer> returnLandmarkList = getItinLandCrosswalkByItineraryId(itineraryId);
                for (Integer localInt : returnLandmarkList) {
                    localLandmark = landmarkDao.getLandmarkById(localInt);
                    returnLandmarks.add(localLandmark);
                }

                localItinerary.setLandmarks(returnLandmarks);

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return localItinerary;
    }

    /**
     * Updates an Itinerary record with the Start Point Id from the Start Point record related to the Itinerary Id.
     *
     * @param inputItinId       The Itinerary Id
     * @return                  The Itinerary object, with the Start Point Id field populated
     */
    @Override
    public Itinerary updateItinWithStartPointId(int inputItinId){
        Itinerary updatedItinerary = null;

        int inputSPId = startPointDao.getStartPointByItineraryId(inputItinId).getStartPointId();

        String sql = "UPDATE public.itinerary " +
                "SET start_point_id = ? " +
                "WHERE itinerary_id = ? " +
                "AND deletedflag = false;";
        try {
            int rowsAffected = jdbcTemplate.update(sql, inputSPId, inputItinId);
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            updatedItinerary = getItineraryById(inputItinId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedItinerary;

    }

    /**
     * Get the list of Landmark IDs for an Itinerary
     *
     * @param id        The Itinerary Id
     * @return          The list of Landmark IDs
     */
    public List<Integer> getItinLandCrosswalkByItineraryId(int id) {
        List<Integer> itinlandRecords = new ArrayList<>();
        String sql = "SELECT landmark_id, sort_order " +
                "FROM public.itinerary_landmark " +
                "where itinerary_id = ? " +
                "AND deletedflag = false " +
                "order by sort_order;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            while (results.next()) {
                Integer landmarkIdFromCrosswalk = mapRowToItinLand(results);
                itinlandRecords.add(landmarkIdFromCrosswalk);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return itinlandRecords;
    }

    /**
     * Creates an Itinerary record, using a minimal populated Itinerary object
     *
     * @param inputItinerary        A minimal populated Itinerary object
     * @return                      A populated Itinerary object with an Itinerary Id
     */
    @Override
    public Itinerary createItinerary(Itinerary inputItinerary) {
        Itinerary localItinerary = null;
        String sql =
                "INSERT INTO public.itinerary( " +
                        "user_id, itinerary_name, itinerary_date) " +
                        "VALUES (?, ?, to_date(?, 'MM-DD-YYYY') ) " +
                        "RETURNING itinerary_id ;";
        try {
            int newItineraryID = jdbcTemplate.queryForObject(sql, int.class, inputItinerary.getUserId(), inputItinerary.getItineraryName(), inputItinerary.getItineraryDate());
            localItinerary = getItineraryById(newItineraryID);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return localItinerary;
    }

    /**
     * Gets a summary list of Itinerary IDs and their Names for a specific User
     *
     * @param id        The User Id
     * @return          The summary list of Itinerary IDs and their Names
     */
    public List<Itinerary> getItinSummaryListByUserId(int id) {
        List<Itinerary> itinSummaryList = new ArrayList<>();
        String sql = "SELECT itinerary_id, itinerary_name " +
                "FROM public.itinerary " +
                "WHERE user_id = ? " +
                "AND deletedflag = false " +
                "ORDER BY itinerary_id;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            while (results.next()) {
                Itinerary itinListItem = mapRowToListItinerary(results);
                itinSummaryList.add(itinListItem);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return itinSummaryList;
    }

    /**
     * Update an existing Itinerary Record with update info in an Itinerary Object
     *
     * @param inputItinerary        The Itinerary Object
     * @return                      The number of rows affected (should be 1)
     */
    @Override
    public int updateItinerary(Itinerary inputItinerary){
        int rowsAffected = 0;

        String sql = "UPDATE public.itinerary " +
                "SET itinerary_name = ?, itinerary_date = to_date(?, 'YYYY-MM-DD') " +
                "WHERE itinerary_id = ? " +
                "and deletedflag = false;";

        try {
            rowsAffected = jdbcTemplate.update(sql, inputItinerary.getItineraryName(), inputItinerary.getItineraryDate(), inputItinerary.getItineraryId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return rowsAffected;
    }

    /**
     * "Deletes" (via updates to the deleted flag and deleted date) the Itinerary record
     *
     * @param itineraryId       The Itinerary Id to be "deleted"
     * @return                  The number of rows affected (should be 1)
     */
    @Override
    public int deleteItinerary(int itineraryId){
        int rowsAffected = 0;

        String sql = "UPDATE public.itinerary " +
                "SET deletedflag = true, deleteddate = now() " +
                "WHERE itinerary_id = ? " +
                "AND deletedflag = false;";

        try {
            rowsAffected = jdbcTemplate.update(sql, itineraryId);
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return rowsAffected;
    }

    /**
     * Maps a single SQL Row Object and its fields to an Itinerary Object
     *
     * @param rs        A single SQL Row Object
     * @return          A populated Itinerary Object
     */
    private Itinerary mapRowToItinerary(SqlRowSet rs) {
        Itinerary itinerary = new Itinerary();
        itinerary.setItineraryId(rs.getInt("itinerary_id"));
        itinerary.setItineraryName(rs.getString("itinerary_name"));
        itinerary.setUserId(rs.getInt("user_id"));
        itinerary.setItineraryDate(rs.getString("itinerary_date"));

        return itinerary;
    }

    /**
     * Maps a single SQL Row Object and its fields to an
     *
     * @param rs        A single SQL Row Object
     * @return          A populated Itinerary Object
     */
    private Itinerary mapRowToListItinerary(SqlRowSet rs) {
        Itinerary itinerary = new Itinerary();
        itinerary.setItineraryId(rs.getInt("itinerary_id"));
        itinerary.setItineraryName(rs.getString("itinerary_name"));


        return itinerary;
    }

    /**
     * Maps a single SQL Row Object and its fields to an
     *
     * @param rs        A single SQL Row Object
     * @return          A string containing the Map Box Token
     */
    private String mapRowToToken(SqlRowSet rs) {
        String localToken = "";
        localToken = rs.getString("map_box_token");
        return localToken;
    }

    /**
     * Maps a single SQL Row Object and its fields to an
     *
     * @param rs        A single SQL Row Object
     * @return          A Landmark Id integer value
     */
    private Integer mapRowToItinLand(SqlRowSet rs) {
        Integer returnInt = rs.getInt("landmark_id");
        return returnInt;
    }
}
