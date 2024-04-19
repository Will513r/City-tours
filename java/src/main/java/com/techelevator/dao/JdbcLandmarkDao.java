package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Landmark;
import com.techelevator.model.DayTime;
import com.techelevator.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcLandmarkDao implements LandmarkDao{
    private JdbcTemplate jdbcTemplate;
    private ImageItemDao imageItemDao;

    public JdbcLandmarkDao(JdbcTemplate jdbcTemplate, ImageItemDao imageItemDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.imageItemDao = imageItemDao;
    }

    @Override
    public Landmark getLandmarkById(int id) {
        Landmark landmark = null;
        String sql = "SELECT landmark_id, landmark_name, landmark_description, venue_type_name, longitude, latitude, address, landmark_like, landmark_dislike " +
                "FROM public.landmark l " +
                "join public.venue_type v on v.venue_type_id = l.venue_type_id " +
                "where landmark_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                landmark = mapRowToLandmark(results);
                //images
                landmark.setImageURLs(imageItemDao.getImagesListByLandmarkId(id));
                landmark.setDayTimes(getDayTimesByLandmarkId(landmark.getLandmarkId()));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return landmark;
    }

    @Override
    public List<Landmark> getLandmarks() {
        List<Landmark> landmarks = new ArrayList<>();
        String sql = "SELECT landmark_id, landmark_name, landmark_description, venue_type_name, longitude, latitude, address, landmark_like, landmark_dislike " +
                "FROM public.landmark l " +
                "join public.venue_type v on v.venue_type_id = l.venue_type_id;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Landmark landmark = mapRowToLandmark(results);
                landmark.setDayTimes(getDayTimesByLandmarkId(landmark.getLandmarkId()));
                landmark.setImageURLs(imageItemDao.getImagesListByLandmarkId(landmark.getLandmarkId()));
                landmarks.add(landmark);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return landmarks;
    }

    @Override
    public Landmark getLandmarkByName(String landmarkName) {
        Landmark landmark = null;
        String sql = "SELECT landmark_id, landmark_name, landmark_description, venue_type_id, longitude, latitude, address, landmark_like, landmark_dislike " +
                "FROM public.landmark " +
                "WHERE landmark_name = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, landmarkName);
            if (results.next()) {
                landmark = mapRowToLandmark(results);
                //images
                landmark.setDayTimes(getDayTimesByLandmarkId(landmark.getLandmarkId()));
                landmark.setImageURLs(imageItemDao.getImagesListByLandmarkId(landmark.getLandmarkId()));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return landmark;
    }

    // fix later
    @Override
    public int createLandmark(Landmark landmark) {

        return 0;
    }

    @Override
    public Landmark updateLandmark(Landmark landmark) {
        return null;
    }



    @Override
    public List<DayTime> getDayTimesByLandmarkId (int id){
        List<DayTime> dayTimes = new ArrayList<>();
        String sql = "SELECT day_time_id, landmark_id, day_name, open_time, close_time " +
                "FROM public.day_time " +
                "WHERE landmark_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            while (results.next()) {
                DayTime dayTime = mapRowToDayTime(results);
                dayTimes.add(dayTime);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return dayTimes;
    }

    @Override
    public void addItinLandmarkCrosswalk(int inputItinId, List<Integer> inputLMIds){

        int sortOrderCount = 0;
        try {
            for (Integer localInt : inputLMIds) {
                String sql =
                        "INSERT INTO public.itinerary_landmark( " +
                                "itinerary_id, landmark_id, sort_order) " +
                                "VALUES (?, ?, ?);";
                sortOrderCount++;
                jdbcTemplate.update(sql, inputItinId, localInt, sortOrderCount);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public int updateItinLandmarkCrosswalk(int inputItinId, List<Integer> inputLMIds){
        int rowsAffected = 0;

        try {
            rowsAffected = deleteItinLandmarkCrosswalkRecords(inputItinId);

            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }

            addItinLandmarkCrosswalk(inputItinId, inputLMIds);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return rowsAffected;
    }

    @Override
    public int deleteItinLandmarkCrosswalkRecords(int itineraryId){
        int rowsAffected = 0;

        String sql = "UPDATE public.itinerary_landmark " +
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

    @Override
    public int updateLandmarkLikes(int landmarkId, int likeCounter){
        int rowsAffected = 0;
        int returnCount = 0;
        Landmark localLandmark = null;

        String sql = "UPDATE public.landmark " +
                "SET landmark_like = landmark_like + ? " +
                "WHERE landmark_id = ?;";

        try {
            rowsAffected = jdbcTemplate.update(sql, likeCounter, landmarkId);
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            localLandmark = getLandmarkById(landmarkId);
            returnCount = localLandmark.getLandmarkLike();


        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return returnCount;
    }

    @Override
    public int updateLandmarkDislikes(int landmarkId, int dislikeCounter){
        int rowsAffected = 0;
        int returnCount = 0;
        Landmark localLandmark = null;


        String sql = "UPDATE public.landmark " +
                "SET landmark_dislike = landmark_dislike + ? " +
                "WHERE landmark_id = ?;";

        try {
            rowsAffected = jdbcTemplate.update(sql, dislikeCounter, landmarkId);
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            localLandmark = getLandmarkById(landmarkId);
            returnCount = localLandmark.getLandmarkDislike();

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return returnCount;
    }


    private DayTime mapRowToDayTime(SqlRowSet rs) {
        DayTime dayTime = new DayTime();
        dayTime.setDayTimeId(rs.getInt("day_time_id"));
        dayTime.setLandmarkId(rs.getInt("landmark_id"));
        dayTime.setDayName(rs.getString("day_name"));
        dayTime.setOpenTime(rs.getString("open_time"));
        dayTime.setCloseTime(rs.getString("close_time"));
        return dayTime;
    }

    //we are not coding imageURLs and dayTime even though it's in the POJO
    private Landmark mapRowToLandmark(SqlRowSet rs) {
        Landmark landmark = new Landmark();
        landmark.setLandmarkId(rs.getInt("landmark_id"));
        landmark.setLandmarkName(rs.getString("landmark_name"));
        landmark.setDescription(rs.getString("landmark_description"));
        landmark.setVenueTypeName(rs.getString("venue_type_name"));
        landmark.setLatitudeLM(rs.getString("latitude"));
        landmark.setLongitudeLM(rs.getString("longitude"));
        landmark.setAddress(rs.getString("address"));
        landmark.setLandmarkLike(rs.getInt("landmark_like"));
        landmark.setLandmarkDislike(rs.getInt("landmark_dislike"));
        return landmark;
    }

}
