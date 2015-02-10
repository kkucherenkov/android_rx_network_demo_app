package com.home.grishnak.filckrexplorer.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CameraSearchResult {
    public FlickrCamera cameras;

    public List<Camera> getCameras() {
        return cameras.camera;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FlickrCamera {

        public String brand;
        public String stat;
        public List<Camera> camera;
    }
}
