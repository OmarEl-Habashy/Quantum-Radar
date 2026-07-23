package models;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
/*
* Fine will act as a container for connecting the plate number with the violations it did
* protect the list from being modified externally or at all!
* */
public class Fine {
    private final String plateNumber;
    private final List<Violation> violations;

    public Fine(String plateNumber, List<Violation> violations) {
        this.plateNumber = plateNumber;
        this.violations = violations;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public List<Violation> getViolations() {
        return Collections.unmodifiableList(violations);
    }
    public int getTotalFees(){
        int sum = 0;
        if(!violations.isEmpty()) {
            for (Violation x : violations) {
                sum += x.getFee();
            }
        }
        return sum;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Traffic fine for car ").append(plateNumber).append("\n");
        sb.append("Total amount: ").append(getTotalFees()).append(" EGP\n");
        sb.append("Violations:\n");
        for (Violation x : violations) {
            sb.append(x).append("\n");
        }
        return sb.toString();
    }
}
