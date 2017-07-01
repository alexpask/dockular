package org.alexpask.model;

/**
 * Created by alexpask on 01/07/2017.
 */
public class DockerCount {
    private int imageCount;

    public DockerCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }
}
