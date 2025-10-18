import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SmallestFitStrategy implements SpotAllocationStrategy {

    // If you prefer enum order, ensure VehicleType order is MOTORCYCLE, CAR, TRUCK
    // Otherwise use a rank map similar to earlier suggestions.
    private final Map<VehicleType, Integer> rank = Map.of(
            VehicleType.MOTORCYCLE, 1,
            VehicleType.CAR, 2,
            VehicleType.TRUCK, 3
    );

    @Override
    public ParkingSpot findSpot(List<ParkingLevel> levels, Vehicle vehicle) {
        // For each level, try spot types starting from vehicleType up to largest
        for (ParkingLevel level : levels) {
            List<VehicleType> candidateTypes = getCandidateTypes(vehicle.getType());
            for (VehicleType spotType : candidateTypes) {
                List<ParkingSpot> spots = level.getSpotsByType().get(spotType);
                if (spots == null) continue;
                for (ParkingSpot spot : spots) {
                    // assignVehicle is synchronized and returns true only if it succeeds
                    if (!spot.isAllocated() && spot.assignVehicle(vehicle)) {
                        return spot;
                    }
                }
            }
        }
        return null;
    }

    // Returns list like [CAR, TRUCK] for vehicle CAR (smallest-first)
    private List<VehicleType> getCandidateTypes(VehicleType vehicleType) {
        int vRank = rank.get(vehicleType);
        List<VehicleType> types = new ArrayList<>();
        // add all types whose rank >= vehicle rank, sorted ascending by rank
        rank.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .forEach(e -> {
                    if (e.getValue() >= vRank) types.add(e.getKey());
                });
        return types;
    }
}