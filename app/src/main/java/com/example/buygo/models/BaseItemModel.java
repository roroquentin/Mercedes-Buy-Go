package com.example.buygo.models;

/**
 * Created by hamurcuabi on 23,June,2020
 **/
public class BaseItemModel {
    private String userMail;
    private String produktName;
    private String produktPrice;
    private String produktStiation;
    private String produktInformation;
    private String produktImage;

    public BaseItemModel() {
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getProduktName() {
        return produktName;
    }

    public void setProduktName(String produktName) {
        this.produktName = produktName;
    }

    public String getProduktPrice() {
        return produktPrice;
    }

    public void setProduktPrice(String produktPrice) {
        this.produktPrice = produktPrice;
    }

    public String getProduktStiation() {
        return produktStiation;
    }

    public void setProduktStiation(String produktStiation) {
        this.produktStiation = produktStiation;
    }

    public String getProduktInformation() {
        return produktInformation;
    }

    public void setProduktInformation(String produktInformation) {
        this.produktInformation = produktInformation;
    }

    public String getProduktImage() {
        return produktImage;
    }

    public void setProduktImage(String produktImage) {
        this.produktImage = produktImage;
    }
}
