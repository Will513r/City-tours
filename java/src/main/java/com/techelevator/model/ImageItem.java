package com.techelevator.model;
import java.util.Objects;
public class ImageItem {
    private int imageId;
    private int landmarkId;
    private String imageUrl;

    public ImageItem() {
    }

    public ImageItem(int imageId, int landmarkId, String imageUrl) {
        this.imageId = imageId;
        this.landmarkId = landmarkId;
        this.imageUrl = imageUrl;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getLandmarkId() {
        return landmarkId;
    }

    public void setLandmarkId(int landmarkId) {
        this.landmarkId = landmarkId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageItem ImageItem = (ImageItem) o;
        return imageId == ImageItem.imageId &&
                landmarkId == ImageItem.landmarkId &&
                imageUrl == ImageItem.imageUrl;
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, landmarkId, imageUrl);
    }

    @Override
    public String toString() {
        return "ImageItem{" +
                "imageId=" + imageId +
                ", landmarkId=" + landmarkId +
                ", imageUrl=" + imageUrl +
                '}';
    }
}
