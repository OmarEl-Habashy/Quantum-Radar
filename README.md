# QuantumRadar

A traffic radar simulation system built in Java

## What it does

The system simulates a traffic radar that reads vehicle data and checks them against a set of rules. If a vehicle breaks a rule, a fine is issued with the details of the violation and the total amount.

It supports multiple radars (like highway, school zone, etc.) each with their own rules and fines.

## Project structure

```
src/
  Core/       - main radar and data models (RadarReading, Rule interface, Radar)
  models/     - CarType, Violation, Fine
  rules/      - the actual traffic rules (speed, seatbelt, truck restriction)
  adapters/   - converts data from external systems into RadarReading
```

Adding a new rule is simple, just create a new class in the rules folder that implements the Rule interface and add it to a radar in Main.java. The radar itself doesn't need any changes.

## Adapters

The system supports data from different sources using the adapter pattern. Right now there is only one:

- **TrafficCameraAdapter** - camera systems that send decimal speed and integer vehicle codes

Converts to a standard RadarReading that the radar can process.

## Sample output

```
Traffic fine for CAR OMAR-264
Total amount: 700 EGP
Violations:
- Seatbelt not fastned : 200 EGP
- speed of 130 exceeded max allowed 120 : 500 EGP

Highway All Fines :
OMAR-264 : 700 EGP
ALO-2255 : 700 EGP

Highway Violation Counts :
Seatbelt Rule : 1
Speed Rule : 2
```
