package rules;
import core.RadarReading;
import core.Rule;
import models.CarType;
import models.Violation;

/*
* speed rule is used for every car type only, so private has a speedrule, truck has another object of speedrule, same goes to bus
* */
public class SpeedRule implements Rule {
    private final int fee;
    private final int speedLimit;
    CarType carType;

    public SpeedRule(int fee, int speedLimit, CarType carType){
        this.fee = fee;
        this.speedLimit = speedLimit;
        this.carType = carType;
    }
    public CarType getCarType() {
        return carType;
    }
    public int getFee(){
        return fee;
    }
    public int getSpeedLimit(){

        return speedLimit;
    }

    @Override
    public String getRuleName() {
        return "Speed Rule";
    }

    @Override
    public Violation checkViolation(RadarReading reading) {
        if(reading.getCarType() != carType){
            return null;
        }
        if(reading.getSpeed() <= speedLimit){
            return null;
        }
        return new Violation(getRuleName(), "speed of " + reading.getSpeed() + " exceeded max allowed " + speedLimit, fee);
    }

}
