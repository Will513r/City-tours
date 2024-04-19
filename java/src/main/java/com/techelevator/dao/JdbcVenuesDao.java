package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.DayTime;
import com.techelevator.model.Venues;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcVenuesDao implements VenuesDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcVenuesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Venues> getVenues() {
        List<Venues> venues = new ArrayList<>();
        String sql =
                "SELECT venue_type_id, venue_type_name " +
                "FROM public.venue_type;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Venues venue = mapRowToVenues(results);
                venues.add(venue);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return venues;
    }

    private Venues mapRowToVenues(SqlRowSet rs) {
        Venues venue = new Venues();
        venue.setVenueId(rs.getInt("venue_type_id"));
        venue.setVenueName(rs.getString("venue_type_name"));
        return venue;
    }

}
