import java.time.temporal.ChronoUnit;
import java.util.Map;

public class FareCalculator {

    private static final Map<VehicleType,Double> fares = Map.of(
      VehicleType.MOTORCYCLE, 50.0,
      VehicleType.CAR,100.0,
      VehicleType.TRUCK, 200.0

    );

    public static double calculateFare(Ticket ticket){
        long diff = ChronoUnit.MINUTES.between(ticket.getEntryTime(), ticket.getExitTime());
        return   fares.get(ticket.getVehicleType());
    };

}
