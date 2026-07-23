package core;

import models.Fine;
import models.Violation;

import java.util.ArrayList;
import java.util.List;

public class Radar {
    private List<Fine> fines = new ArrayList<>();
    private List<Rule> rules =  new ArrayList<>();

    public void addRule(Rule rule){
        rules.add(rule);
    }
    public Fine processReading(RadarReading reading) {
        List<Violation> violations = new ArrayList<>();
        for (Rule rule : rules) {
            Violation v = rule.checkViolation(reading);
            if (v != null) {
                violations.add(v);
            }
        }
        if (violations.isEmpty()) {
            return null;
        }
        Fine fine = new Fine(reading.getPlateNumber(), violations, reading.getCarType());
        fines.add(fine);
        return fine;
    }

}
