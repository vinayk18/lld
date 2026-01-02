import java.util.List;

public class FirstAvailableStrategy implements SpotAllocationStrategy {
    @Override
    public ParkingSpot findSpot(List<ParkingLevel> parkingLevels, Vehicle vehicle) {
        for (ParkingLevel level : parkingLevels) {
            ParkingSpot spot = level.occupySlot(vehicle);
            if (spot != null) return spot;
        }
        return null;
    }
}