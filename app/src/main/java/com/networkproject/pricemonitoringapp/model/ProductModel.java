package com.networkproject.pricemonitoringapp.model;

public class ProductModel {
    private String name,searchName,imageLink;

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public ProductModel()
    {
        this.name = null;
        this.imageLink = null;
        this.searchName = null;
    }

    public ProductModel(String name, String searchName, String imageLink ) {
        this.name = name;
        this.imageLink = imageLink;
        this.searchName = searchName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
