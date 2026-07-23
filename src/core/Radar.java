package core;

import models.Fine;
import models.Violation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Radar {
    private List<Fine> fines = new ArrayList<>();
    private List<Rule> rules =  new ArrayList<>();
    private String name;

    public Radar(String name) {
        this.name = name;
    }

    public void addRule(Rule rule){
        rules.add(rule);
    }
    public String getName() {
        return name;
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


    public Map<String, Integer> getAllFines() {
        Map<String, Integer> totals = new HashMap<>();
        for (Fine fine : fines) {
            String plate = fine.getPlateNumber();
            int fineTotal = fine.getTotalFees();
            if (totals.containsKey(plate)) {
                int current = totals.get(plate);
                totals.put(plate, current + fineTotal);
            } else {
                totals.put(plate, fineTotal);
            }
        }
        return totals;
    }

    public Map<String, Integer> getViolationCounts() {
        Map<String, Integer> counts = new HashMap<>();
        for (Fine fine : fines) {
            for (Violation v : fine.getViolations()) {
                String ruleName = v.getRuleName();
                if (counts.containsKey(ruleName)) {
                    int current = counts.get(ruleName);
                    counts.put(ruleName, current + 1);
                } else {
                    counts.put(ruleName, 1);
                }
            }
        }
        return counts;
    }
}
