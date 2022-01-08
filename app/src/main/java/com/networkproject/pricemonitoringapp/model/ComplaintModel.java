package com.networkproject.pricemonitoringapp.model;

public class ComplaintModel {

    private String shopNo,shopName,marketName,region,complaintDetails;

    public ComplaintModel(String shopNo, String shopName, String marketName, String region, String complaintDetails) {
        this.shopNo = shopNo;
        this.shopName = shopName;
        this.marketName = marketName;
        this.region = region;
        this.complaintDetails = complaintDetails;
    }

    public ComplaintModel() {
        this.shopNo = null;
        this.shopName = null;
        this.marketName = null;
        this.region = null;
        this.complaintDetails = null;
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getComplaintDetails() {
        return complaintDetails;
    }

    public void setComplaintDetails(String complaintDetails) {
        this.complaintDetails = complaintDetails;
    }
}
