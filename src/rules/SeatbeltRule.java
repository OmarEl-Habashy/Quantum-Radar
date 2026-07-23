package rules;

import core.RadarReading;
import core.Rule;
import models.Violation;

public class SeatbeltRule implements Rule {
    private int fee;

    public SeatbeltRule(int fee){
        this.fee = fee;
    }
    @Override
    public String getRuleName() {
        return "Seatbelt Rule";
    }

    @Override
    public Violation checkViolation(RadarReading reading) {
        if(reading.isSeatbeltFastened()) {
            return null;
        }
        else {
            return new Violation(getRuleName(), "Seatbelt not fastned", fee);
        }
    }

}
