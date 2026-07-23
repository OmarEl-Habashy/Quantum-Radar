package core;
import adapters.TrafficCameraAdapter;
import models.CarType;
import models.Fine;
import rules.SeatbeltRule;
import rules.SpeedRule;
import rules.TruckRestrictionRule;
import java.time.LocalDateTime;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Radar highway = new Radar("Highway");
        highway.addRule(new SeatbeltRule(200));
        highway.addRule(new SpeedRule(500, 120, CarType.CAR));
        highway.addRule(new SpeedRule(700, 80,  CarType.TRUCK));
        highway.addRule(new SpeedRule(600, 100, CarType.BUS));

        Fine f1 = highway.processReading(new TrafficCameraAdapter("OMAR-264", 130.0, 1, false, LocalDateTime.now()).toRadarReading());
        Fine f2 = highway.processReading(new TrafficCameraAdapter("ALO-2255", 95.0,  3, true,  LocalDateTime.now()).toRadarReading());
        Fine f3 = highway.processReading(new TrafficCameraAdapter("YEPPYY123", 90.0,  2, true,  LocalDateTime.now()).toRadarReading());

        if (f1 != null) System.out.println(f1);
        if (f2 != null) System.out.println(f2);
        if (f3 == null) System.out.println("YEPPYY123 : No violation.\n");


        Radar schoolZone = new Radar("School Zone");
        schoolZone.addRule(new SeatbeltRule(300));
        schoolZone.addRule(new SpeedRule(1000, 30, CarType.CAR));
        schoolZone.addRule(new SpeedRule(1000, 20, CarType.TRUCK));
        schoolZone.addRule(new TruckRestrictionRule(800));

        Fine f4 = schoolZone.processReading(new TrafficCameraAdapter("ABR-987",   45.0, 1, false, LocalDateTime.now()).toRadarReading());
        Fine f5 = schoolZone.processReading(new TrafficCameraAdapter("YASS-1234", 28.0, 1, true,  LocalDateTime.now()).toRadarReading());
        Fine f6 = schoolZone.processReading(new TrafficCameraAdapter("TRUK-001",  15.0, 3, true,  LocalDateTime.now()).toRadarReading());

        if (f4 != null) System.out.println(f4);
        if (f5 == null) System.out.println("YASS-1234 : No violation.\n");
        if (f6 != null) System.out.println(f6);


        Radar cityCenter = new Radar("City Center");
        cityCenter.addRule(new SeatbeltRule(150));
        cityCenter.addRule(new SpeedRule(400, 60, CarType.CAR));
        cityCenter.addRule(new SpeedRule(400, 50, CarType.TRUCK));
        cityCenter.addRule(new SpeedRule(400, 55, CarType.BUS));

        Fine f7 = cityCenter.processReading(new TrafficCameraAdapter("OMAR-264", 75.0, 1, true,  LocalDateTime.now()).toRadarReading());
        Fine f8 = cityCenter.processReading(new TrafficCameraAdapter("ALO-2255", 48.0, 3, true,  LocalDateTime.now()).toRadarReading());
        Fine f9 = cityCenter.processReading(new TrafficCameraAdapter("YEPPYY123", 60.0, 2, false, LocalDateTime.now()).toRadarReading());

        if (f7 != null) System.out.println(f6);
        if (f8 == null) System.out.println("ALO-2255 : No violation.\n");
        if (f9 != null) System.out.println(f8);


        Radar[] allRadars = { highway, schoolZone, cityCenter };

        for (Radar radar : allRadars) {
            System.out.println(radar.getName() + " All Fines : ");
            Map<String, Integer> fines = radar.getAllFines();
            for (String plate : fines.keySet()) {
                System.out.println(plate + " : " + fines.get(plate) + " EGP");
            }
            System.out.println("\n");
            System.out.println(radar.getName() + " Violation Counts : ");
            Map<String, Integer> counts = radar.getViolationCounts();
            for (String ruleName : counts.keySet()) {
                System.out.println(ruleName + " : " + counts.get(ruleName));
            }
            System.out.println();
        }
    }
}