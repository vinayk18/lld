package ParkingLot;

public class Vehicle {
    String id;
    VehicleType type;

    Vehicle( String id, VehicleType type){
        this.id = id;
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
