package core;

import models.Violation;

//apply strategy design pattern here
public interface Rule {
    String getRuleName();
    Violation checkViolation(RadarReading reading);
}
