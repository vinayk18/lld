import java.time.Duration;

public class HourlyFareStrategy implements FareStrategy {
    @Override
    public double calculateFare(Ticket ticket) {
        long minutes = Duration.between(ticket.getEntryTime(), ticket.getExitTime()).toMinutes();
        long hours = Math.max(1, (minutes + 59) / 60);
        double rate = switch (ticket.getVehicleType()) {
            case MOTORCYCLE -> 50.0;
            case CAR -> 100.0;
            case TRUCK -> 200.0;
        };
        return rate * hours;
    }
}