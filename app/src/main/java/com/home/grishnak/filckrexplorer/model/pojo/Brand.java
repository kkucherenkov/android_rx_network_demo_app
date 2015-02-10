package com.home.grishnak.filckrexplorer.model.pojo;

public class Brand {
    private String name;
    private String id;

    public Brand(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Brand() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
