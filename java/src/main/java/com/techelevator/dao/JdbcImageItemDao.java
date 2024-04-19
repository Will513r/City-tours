package com.techelevator.dao;

import com.techelevator.model.ImageItem;
import org.springframework.stereotype.Component;

import com.techelevator.exception.DaoException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcImageItemDao implements ImageItemDao {
    /**
     *  Declarations of variables
     */
    private JdbcTemplate jdbcTemplate;

    /**
     * Class constructor specifying number of objects to create.
     *
     * @param jdbcTemplate
     */
    public JdbcImageItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Adds an image URL to the database
     *
     * @param inputImage        The ImageItem with the Landmark Id and the URL string
     * @return                  The ImageItem including the image id
     */
    @Override
    public ImageItem addLandmarkImage(ImageItem inputImage){
        ImageItem localImageItem = null;
        String sql =
                "INSERT INTO public.image_landmark( " +
                        " landmark_id, image_url) " +
                        "VALUES (?, ?) " +
                        "RETURNING image_id;";

        int localLandMarkId = inputImage.getLandmarkId();

        try {
            int newImageItemID = jdbcTemplate.queryForObject(sql, int.class, localLandMarkId, inputImage.getImageUrl());
            localImageItem = getImageItemByImageId(newImageItemID);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return localImageItem;
    };

    /**
     * Gets an Image Item by its Image Id
     *
     * @param inputImageId      The Image Id
     * @return                  The ImageItem including the image id
     */
    @Override
    public ImageItem getImageItemByImageId(int inputImageId){
        ImageItem imageItem = null;
        String sql = "SELECT image_id, landmark_id, image_url " +
                "FROM public.image_landmark " +
                "WHERE image_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, inputImageId);
            if (results.next()) {
                imageItem = mapRowToImageItem(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return imageItem;
    };

    /**
     * Gets a List of Image Items for a specific Landmark Id
     *
     * @param inputLandmarkId       The Landmark Id
     * @return                      A List of Image Items
     */
    @Override
    public List<ImageItem> getImagesListByLandmarkId(int inputLandmarkId){
        List<ImageItem> localImageList = new ArrayList<>();
        String sql = "SELECT image_id, landmark_id, image_url " +
                "FROM public.image_landmark " +
                "WHERE landmark_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, inputLandmarkId);
            while (results.next()) {
                ImageItem localImageItem = mapRowToImageItem(results);
                localImageList.add(localImageItem);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return localImageList;
    };

    /**
     * Maps a single SQL Row Object and its fields to an Image Item
     *
     * @param rs        A single SQL Row Object
     * @return          A populated Image Item
     */
    private ImageItem mapRowToImageItem(SqlRowSet rs) {
        ImageItem localImageItem = new ImageItem();
        localImageItem.setImageId(rs.getInt("image_id"));
        localImageItem.setLandmarkId(rs.getInt("landmark_id"));
        localImageItem.setImageUrl(rs.getString("image_url"));

        return localImageItem;
    }
}
