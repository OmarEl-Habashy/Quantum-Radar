package adapters;

import core.RadarReading;
import models.CarType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.DoubleToIntFunction;

public class TrafficCameraAdapter implements RadarReadingAdapter {
    //a hypothetical camera system used in radar system, so adding adapter for streamlining the data capturing from multiple systems into a single pipeline

    private final String detectedPlate;
    private final double speedDetected;
    private final int vehicleCode; //maybe 1 for private, 2 for bus, 3 for truck
    private final boolean beltOn;
    private final LocalDateTime dateTime;

    public TrafficCameraAdapter(String detectedPlate, double speedDetected, int vehicleCode, boolean beltOn, LocalDateTime dateTime) {
        this.detectedPlate = detectedPlate;
        this.speedDetected = speedDetected;
        this.vehicleCode = vehicleCode;

        this.beltOn = beltOn;
        this.dateTime = dateTime;
    }

    @Override
    public RadarReading toRadarReading() {
        //translate to the old system readings

        CarType carType;
        switch (vehicleCode){
            case 1:
                carType = CarType.PRIVATE;
                break;
            case 2:
                carType = CarType.BUS;
                break;
            case 3:
                carType = CarType.TRUCK;
                break;
            default:// just fallback to private
            carType = CarType.PRIVATE;
        }
        int vehicleSpeed = (int) Math.round(speedDetected);
        LocalDate date = dateTime.toLocalDate();

        return new RadarReading(this.detectedPlate, carType, date, vehicleSpeed, this.beltOn);
    }
}
