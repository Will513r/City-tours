package com.techelevator.dao;

import com.techelevator.model.ImageItem;
import java.util.List;

public interface ImageItemDao {

    /**
     *  Interface method declarations
     */

    ImageItem addLandmarkImage(ImageItem inputImage);

    List<ImageItem> getImagesListByLandmarkId(int inputLandmarkId);

    ImageItem getImageItemByImageId(int inputImageId);

}
