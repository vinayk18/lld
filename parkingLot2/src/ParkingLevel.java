import java.util.List;
import java.util.Map;

public class ParkingLevel {
    private int level;
    private Map<VehicleType, List<ParkingSpot>> slotDetails ;

    public ParkingLevel( int level, Map<VehicleType, List<ParkingSpot>> slotDetails){
        this.level = level;
        this.slotDetails = slotDetails;
    }

    public int getLevel() {
        return level;
    }

    public Map<VehicleType, List<ParkingSpot>> getSpotsByType() {
        return slotDetails;
    }

    public ParkingSpot occupySlot(Vehicle vehicle){
        for (List<ParkingSpot> sameTypeSpots : slotDetails.values()) {
            for (ParkingSpot spot : sameTypeSpots) {
                if (!spot.isAllocated() && spot.assignVehicle(vehicle)) {
                    return spot; // spot now carries its own level info
                }
            }
        }
        return null;
    }


    public synchronized boolean freeSlot(String spotId) {
        for (List<ParkingSpot> list : slotDetails.values()) {
            for (ParkingSpot spot : list) {
                if (spot.getId().equals(spotId)) {
                    return spot.removeVehicle();
                }
            }
        }
        return false;
    }
}
