package com.networkproject.pricemonitoringapp.model;

public class PriceModel {

    private String marketName,farmerPrice,sellerPrice,wholeSellerPrice,supply;

    public PriceModel() {
        this.marketName = null;
        this.farmerPrice = null;
        this.sellerPrice = null;
        this.wholeSellerPrice = null;
        this.supply = null;
    }

    public PriceModel(String marketName, String farmerPrice, String sellerPrice, String wholeSellerPrice, String supply) {
        this.marketName = marketName;
        this.farmerPrice = farmerPrice;
        this.sellerPrice = sellerPrice;
        this.wholeSellerPrice = wholeSellerPrice;
        this.supply = supply;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getFarmerPrice() {
        return farmerPrice;
    }

    public void setFarmerPrice(String farmerPrice) {
        this.farmerPrice = farmerPrice;
    }

    public String getSellerPrice() {
        return sellerPrice;
    }

    public void setSellerPrice(String sellerPrice) {
        this.sellerPrice = sellerPrice;
    }

    public String getWholeSellerPrice() {
        return wholeSellerPrice;
    }

    public void setWholeSellerPrice(String wholeSellerPrice) {
        this.wholeSellerPrice = wholeSellerPrice;
    }

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }
}
