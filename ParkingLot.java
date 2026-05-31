import java.util.*;

// =========================
// ENUMS
// =========================

enum VehicleSize {
    MOTORCYCLE,
    COMPACT,
    LARGE
}

// =========================
// VEHICLES
// =========================

abstract class Vehicle {

    protected List<ParkingSpot> parkingSpots = new ArrayList<>();
    protected String licensePlate;
    protected int spotsNeeded;
    protected VehicleSize size;

    public int getSpotsNeeded() {
        return spotsNeeded;
    }

    public VehicleSize getSize() {
        return size;
    }

    public void parkInSpot(ParkingSpot spot) {
        parkingSpots.add(spot);
    }

    public void clearSpots() {

        for (ParkingSpot spot : parkingSpots) {
            spot.removeVehicle();
        }

        parkingSpots.clear();
    }

    public abstract boolean canFitInSpot(ParkingSpot spot);
}

class Motorcycle extends Vehicle {

    public Motorcycle(String plate) {
        this.licensePlate = plate;
        this.spotsNeeded = 1;
        this.size = VehicleSize.MOTORCYCLE;
    }

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return true;
    }
}

class Car extends Vehicle {

    public Car(String plate) {
        this.licensePlate = plate;
        this.spotsNeeded = 1;
        this.size = VehicleSize.COMPACT;
    }

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {

        return spot.getSpotSize() == VehicleSize.COMPACT
                || spot.getSpotSize() == VehicleSize.LARGE;
    }
}

class Bus extends Vehicle {

    public Bus(String plate) {
        this.licensePlate = plate;
        this.spotsNeeded = 5;
        this.size = VehicleSize.LARGE;
    }

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSpotSize() == VehicleSize.LARGE;
    }
}

// =========================
// PARKING SPOT
// =========================

class ParkingSpot {

    private Vehicle vehicle;
    private VehicleSize spotSize;

    private int row;
    private int spotNumber;

    private Level level;

    public ParkingSpot(
            Level level,
            int row,
            int spotNumber,
            VehicleSize spotSize) {

        this.level = level;
        this.row = row;
        this.spotNumber = spotNumber;
        this.spotSize = spotSize;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public VehicleSize getSpotSize() {
        return spotSize;
    }

    public int getRow() {
        return row;
    }

    public boolean canFitVehicle(Vehicle vehicle) {

        return isAvailable()
                && vehicle.canFitInSpot(this);
    }

    public boolean park(Vehicle vehicle) {

        if (!canFitVehicle(vehicle)) {
            return false;
        }

        this.vehicle = vehicle;
        vehicle.parkInSpot(this);

        return true;
    }

    public void removeVehicle() {

        vehicle = null;

        level.spotFreed();
    }
}

// =========================
// LEVEL
// =========================

class Level {

    private int floor;

    private ParkingSpot[] spots;

    private int availableSpots;

    private static final int SPOTS_PER_ROW = 10;

    public Level(int floor, int numberSpots) {

        this.floor = floor;

        spots = new ParkingSpot[numberSpots];

        availableSpots = numberSpots;

        int largeSpots = numberSpots / 4;
        int motorcycleSpots = numberSpots / 4;
        int compactSpots = numberSpots - largeSpots - motorcycleSpots;

        int spotNumber = 0;

        for (int i = 0; i < largeSpots; i++) {

            spots[i] = new ParkingSpot(
                    this,
                    i / SPOTS_PER_ROW,
                    spotNumber++,
                    VehicleSize.LARGE);
        }

        for (int i = largeSpots;
             i < largeSpots + compactSpots;
             i++) {

            spots[i] = new ParkingSpot(
                    this,
                    i / SPOTS_PER_ROW,
                    spotNumber++,
                    VehicleSize.COMPACT);
        }

        for (int i = largeSpots + compactSpots;
             i < numberSpots;
             i++) {

            spots[i] = new ParkingSpot(
                    this,
                    i / SPOTS_PER_ROW,
                    spotNumber++,
                    VehicleSize.MOTORCYCLE);
        }
    }

    public int availableSpots() {
        return availableSpots;
    }

    public void spotFreed() {
        availableSpots++;
    }

    public boolean parkVehicle(Vehicle vehicle) {

        if (availableSpots < vehicle.getSpotsNeeded()) {
            return false;
        }

        int spotNumber = findAvailableSpots(vehicle);

        if (spotNumber < 0) {
            return false;
        }

        return parkStartingAtSpot(spotNumber, vehicle);
    }

    private boolean parkStartingAtSpot(
            int spotNumber,
            Vehicle vehicle) {

        for (int i = spotNumber;
             i < spotNumber + vehicle.getSpotsNeeded();
             i++) {

            spots[i].park(vehicle);
        }

        availableSpots -= vehicle.getSpotsNeeded();

        return true;
    }

    private int findAvailableSpots(Vehicle vehicle) {

        int spotsNeeded = vehicle.getSpotsNeeded();

        int lastRow = -1;
        int spotsFound = 0;

        for (int i = 0; i < spots.length; i++) {

            ParkingSpot spot = spots[i];

            if (lastRow != spot.getRow()) {

                spotsFound = 0;
                lastRow = spot.getRow();
            }

            if (spot.canFitVehicle(vehicle)) {
                spotsFound++;
            } else {
                spotsFound = 0;
            }

            if (spotsFound == spotsNeeded) {
                return i - (spotsNeeded - 1);
            }
        }

        return -1;
    }

    public void print() {

        System.out.println("Floor " + floor);

        for (ParkingSpot spot : spots) {

            if (spot.isAvailable()) {
                System.out.print("[ ]");
            } else {
                System.out.print("[X]");
            }
        }

        System.out.println();
    }
}

// =========================
// PARKING LOT
// =========================

class ParkingLot {

    private Level[] levels;

    public ParkingLot(int numLevels,
                      int spotsPerLevel) {

        levels = new Level[numLevels];

        for (int i = 0; i < numLevels; i++) {

            levels[i] = new Level(
                    i,
                    spotsPerLevel);
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {

        for (Level level : levels) {

            if (level.parkVehicle(vehicle)) {
                return true;
            }
        }

        return false;
    }

    public void printStatus() {

        for (Level level : levels) {
            level.print();
        }
    }
}

// =========================
// DRIVER
// =========================

public class ParkingLotDemo {

    public static void main(String[] args) {

        ParkingLot parkingLot =
                new ParkingLot(3, 30);

        Vehicle car1 = new Car("CAR-1");
        Vehicle car2 = new Car("CAR-2");

        Vehicle bike1 =
                new Motorcycle("BIKE-1");

        Vehicle bus1 =
                new Bus("BUS-1");

        parkingLot.parkVehicle(car1);
        parkingLot.parkVehicle(car2);
        parkingLot.parkVehicle(bike1);
        parkingLot.parkVehicle(bus1);

        parkingLot.printStatus();
    }
}