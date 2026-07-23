package core;


import models.CarType;

import java.time.LocalDate;

public class RadarReading {
    private final String plateNumber;
    private final CarType carType;
    private final LocalDate date;
    private final int speed;
    private final boolean seatbeltStatus;


    public RadarReading(String plateNumber, CarType carType, LocalDate date, int speed, boolean seatbeltStatus) {
        this.plateNumber = plateNumber;
        this.carType = carType;
        this.date = date;
        this.speed = speed;
        this.seatbeltStatus = seatbeltStatus;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public CarType getCarType() {
        return carType;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isSeatbeltFastened() {
        return seatbeltStatus;
    }
}
