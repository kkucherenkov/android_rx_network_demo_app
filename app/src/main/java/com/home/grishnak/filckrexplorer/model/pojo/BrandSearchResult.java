package com.home.grishnak.filckrexplorer.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BrandSearchResult {
    public Brands brands;
    public String stat;

    public BrandSearchResult() {}

    public List<Brand> getBrands() {
        return brands.brand;
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Brands {
        public List<Brand> brand;
    }

}
