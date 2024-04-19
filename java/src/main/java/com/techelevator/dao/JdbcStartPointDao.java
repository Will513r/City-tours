package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Landmark;
import com.techelevator.model.StartPoint;
import com.techelevator.model.Itinerary;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcStartPointDao implements StartPointDao{
    private JdbcTemplate jdbcTemplate;
    //private ItineraryDao itineraryDao;

    public JdbcStartPointDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        //this.itineraryDao = itineraryDao;
    }

    @Override
    public StartPoint getStartPointByItineraryId(int id){
        StartPoint startPoint = null;
        String sql = "SELECT max(start_point_id) start_point_id, start_point_name, latitude_sp, longitude_sp, itinerary_id " +
                "FROM public.start_point " +
                "WHERE itinerary_id = ? " +
                "AND deletedflag = false " +
                "GROUP BY start_point_name, latitude_sp, longitude_sp, itinerary_id " +
                "ORDER BY max(start_point_id) desc " +
                "limit 1;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                startPoint = mapRowToStartPoint(results);
                //images

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return startPoint;
    };

    @Override
    public StartPoint getStartPointByStartPointId(int id){
        StartPoint startPoint = null;
        String sql = "SELECT start_point_id, start_point_name, latitude_sp, longitude_sp, itinerary_id " +
                "FROM public.start_point " +
                "WHERE start_point_id = ? " +
                "AND deletedflag = false;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                startPoint = mapRowToStartPoint(results);
                //images

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return startPoint;
    };

//    @Override
//    public Itinerary updateItinWithStartPointId(int inputItinId, int inputSPId){
//        Itinerary updatedItinerary = null;
//        String sql = "UPDATE public.itinerary " +
//                "SET start_point_id = ? " +
//                "WHERE itinerary_id = ?;";
//        try {
//            int rowsAffected = jdbcTemplate.update(sql, inputSPId, inputItinId);
//            if (rowsAffected == 0) {
//                throw new DaoException("Zero rows affected, expected at least one");
//            }
//            updatedItinerary = itineraryDao.getItineraryById(inputItinId);
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        } catch (DataIntegrityViolationException e) {
//            throw new DaoException("Data integrity violation", e);
//        }
//        return updatedItinerary;
//
//    }

    @Override
    public StartPoint createStartPoint(StartPoint inputStartPoint){
        StartPoint localStartPoint = null;
        Itinerary localItinerary = null;
        int localItinId = inputStartPoint.getItineraryId();

        String sql =
                "INSERT INTO public.start_point( " +
                        "start_point_name, latitude_sp, longitude_sp, itinerary_id) " +
                        "VALUES (?, ?, ?, ?) " +
                        "RETURNING start_point_id ;";

        String deleteSpSql = "SELECT start_point_id " +
                "FROM public.start_point " +
                "WHERE itinerary_id = ? " +
                "and deletedflag = false;";

        try {
            int newStartPointID = jdbcTemplate.queryForObject(sql, int.class, inputStartPoint.getNameStartPoint(), inputStartPoint.getLatitudeSP(), inputStartPoint.getLongitudeSP(), localItinId);

            // select all spIds for localItinId where deleted is false
            List<Integer> deleteSpIDs = jdbcTemplate.queryForList(deleteSpSql, Integer.class, localItinId);

            if (deleteSpIDs.size() > 0) {
                // for each loop to "Delete" all SPs in that list
                for (Integer localInt : deleteSpIDs) {
                    //    if SPid != newStartPointID then "Delete SP"
                    if (localInt != newStartPointID) {
                        int returnedRowsAffects = deleteStartPointByStartPointId(localInt);
                    }
                }
            }

            //localStartPoint = getStartPointByItineraryId(localItinId);
            localStartPoint = getStartPointByStartPointId(newStartPointID);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }


        return localStartPoint;
    };

    @Override
    public int deleteStartPointByStartPointId(int startPointId){
        int rowsAffected = 0;

        String sql = "UPDATE public.start_point " +
                "SET deletedflag = true, deleteddate = now() " +
                "WHERE start_point_id = ? " +
                "AND deletedflag = false;";

        try {
            rowsAffected = jdbcTemplate.update(sql, startPointId);
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
    public int updateStartPoint(StartPoint inputStartPoint){
        int rowsAffected = 0;


        String sql = "UPDATE public.start_point " +
                "SET start_point_name = ?, latitude_sp = ?, longitude_sp = ? " +
                "WHERE itinerary_id = ? " +
                "and deletedflag = false;";

        try {
            rowsAffected = jdbcTemplate.update(sql, inputStartPoint.getNameStartPoint(), inputStartPoint.getLatitudeSP(), inputStartPoint.getLongitudeSP(), inputStartPoint.getItineraryId());
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
    public int deleteStartPoint(int itineraryId){
        int rowsAffected = 0;

        String sql = "UPDATE public.start_point " +
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

    private StartPoint mapRowToStartPoint(SqlRowSet rs) {
        StartPoint startPoint = new StartPoint();
        startPoint.setStartPointId(rs.getInt("start_point_id"));
        startPoint.setNameStartPoint(rs.getString("start_point_name"));
        startPoint.setLatitudeSP(rs.getString("latitude_sp"));
        startPoint.setLongitudeSP(rs.getString("longitude_sp"));
        startPoint.setItineraryId(rs.getInt("itinerary_id"));

        return startPoint;
    }
}
