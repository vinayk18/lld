import java.util.List;

public interface SpotAllocationStrategy {
    ParkingSpot findSpot(List<ParkingLevel> parkingLevels, Vehicle vehicle);
}
