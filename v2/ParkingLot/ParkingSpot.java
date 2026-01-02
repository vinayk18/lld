package ParkingLot;

public class ParkingSpot {
    private final String spotId;
    private Vehicle vehicle;
    private final SpotType type;
    private final int level;

    ParkingSpot( String spotId, SpotType spotType,int level){
        this.spotId = spotId;
        this.type = spotType;
        this.level = level;
    }

    public String getLocation() {
        return spotId;
    }

    public boolean isFree() {
        return vehicle == null;
    }

    void occupy(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    void release(){
        this.vehicle = null;
    }

    public SpotType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }
}
