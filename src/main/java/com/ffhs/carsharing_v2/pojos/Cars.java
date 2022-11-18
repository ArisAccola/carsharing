package com.ffhs.carsharing_v2.pojos;

public class Cars {

   private String carManufacturer;

    private String carModel;

    private String carType;

    private String plateNumber;

    private String status;

    public Cars(String carManufacturer, String carModel, String carType, String plateNumber, String status) {
        this.carManufacturer = carManufacturer;
        this.carModel = carModel;
        this.carType = carType;
        this.plateNumber = plateNumber;
        this.status = status;
    }

    public String getCarManufacturer(){
        return carManufacturer;
    }

    public void setCarManufacturer(){
        this.carManufacturer = carManufacturer;
    }


    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}