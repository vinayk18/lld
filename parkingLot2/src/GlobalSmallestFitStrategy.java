import java.util.*;
import java.util.stream.Collectors;

/**
 * Global strategy that scans all levels by size category first.
 * Ensures smaller compatible spots (across all levels) are exhausted
 * before allocating a larger spot to smaller vehicles.
 */
public class GlobalSmallestFitStrategy implements SpotAllocationStrategy {

    // Explicit size ranking so enum order changes won't break it
    private static final Map<VehicleType, Integer> SIZE_RANK = Map.of(
            VehicleType.MOTORCYCLE, 1,
            VehicleType.CAR, 2,
            VehicleType.TRUCK, 3
    );

    @Override
    public ParkingSpot findSpot(List<ParkingLevel> levels, Vehicle vehicle) {
        VehicleType vehicleType = vehicle.getType();
        int vehicleRank = SIZE_RANK.get(vehicleType);

        // Create list of candidate spot types (same or larger)
        List<VehicleType> candidateTypes = SIZE_RANK.entrySet().stream()
                .filter(e -> e.getValue() >= vehicleRank)
                .sorted(Map.Entry.comparingByValue()) // smallest to largest
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Outer loop: spot size category (MOTORCYCLE, CAR, TRUCK)
        for (VehicleType candidateType : candidateTypes) {
            // Inner loop: all levels
            for (ParkingLevel level : levels) {
                List<ParkingSpot> spots = level.getSpotsByType().get(candidateType);
                if (spots == null) continue;

                for (ParkingSpot spot : spots) {
                    // Attempt safe assignment
                    if (!spot.isAllocated() && spot.assignVehicle(vehicle)) {
                        return spot; // Found compatible spot globally
                    }
                }
            }
        }
        return null; // No spot found
    }
}