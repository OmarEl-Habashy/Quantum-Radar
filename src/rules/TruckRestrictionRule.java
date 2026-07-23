package rules;

import core.RadarReading;
import core.Rule;
import models.CarType;
import models.Violation;

public class TruckRestrictionRule implements Rule {

    private final int fee;

    public TruckRestrictionRule(int fee) {
        this.fee = fee;
    }

    @Override
    public String getRuleName() {
        return "Truck Restriction Rule";
    }

    @Override
    public Violation checkViolation(RadarReading reading) {
        if (reading.getCarType() != CarType.TRUCK) {
            return null;
        }
        return new Violation(getRuleName(), "Trucks are not allowed in this zone", fee);
    }
}
