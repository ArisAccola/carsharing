package com.ffhs.carsharing_v2.dto;

/**
 * Java Representation of the Cars table in the database
 *
 * @author Aris M. Accola and Andreas Schwyter
 */
public class Car {

    private int carId;
    private String carManufacturer;
    private String carModel;
    private String carType;
    private String plateNumber;
    private String status;

    /**
     * Constructor
     *
     * @param carId the unique car id
     * @param carManufacturer manufacturer of the car
     * @param carModel model of the car
     * @param carType type of the car
     * @param plateNumber the plate number of the car
     * @param status the status of the car e.g. available - unavailable
     */
    public Car(int carId, String carManufacturer, String carModel, String carType, String plateNumber, String status) {
        this.carId = carId;
        this.carManufacturer = carManufacturer;
        this.carModel = carModel;
        this.carType = carType;
        this.plateNumber = plateNumber;
        this.status = status;
    }

    public Car(){
        super();
    }

    /**
     * Get Function for carId
     *
     * @return carId
     */
    public int getCarId() {
        return carId;
    }

    /**
     * Set Function for carId
     *
     * @param carId set the CarId
     */
    public void setCarId(int carId) {
        this.carId = carId;
    }

    /**
     * Get Function for carManufacturer
     *
     * @return carManufacturer
     */
    public String getCarManufacturer() {
        return carManufacturer;
    }

    /**
     * Set Function for carManufacturer
     * @param carManufacturer set the car manufacturer
     */
    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    /**
     * Get Function for carModel
     *
     * @return car Model
     */
    public String getCarModel() {
        return carModel;
    }

    /**
     * Set Function for carModel
     *
     * @param carModel set Car Model
     */
    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    /**
     * Get Function for carType
     *
     * @return car Type
     */
    public String getCarType() {
        return carType;
    }

    /**
     * Set Function for carType
     *
     * @param carType set the car type
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * Get Function for plateNumber
     *
     * @return plate number
     */
    public String getPlateNumber() {
        return plateNumber;
    }

    /**
     * Set Function for plateNumber
     *
     * @param plateNumber set plate number
     */
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    /**
     * Get Function for status
     *
     * @return get status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set Fucntion for status
     *
     * @param status set the status of the car
     */
    public void setStatus(String status) {
        this.status = status;
    }
}