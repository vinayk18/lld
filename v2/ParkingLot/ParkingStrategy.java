package ParkingLot;

import java.util.List;

public interface ParkingStrategy {
    ParkingSpot selectSpot(Vehicle vehicle, List<ParkingSpot> candidateSpots);
}
