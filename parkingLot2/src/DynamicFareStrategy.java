import java.time.LocalDateTime;

public class DynamicFareStrategy implements FareStrategy {
    @Override
    public double calculateFare(Ticket ticket) {
        long minutes = java.time.Duration.between(ticket.getEntryTime(), ticket.getExitTime()).toMinutes();
        double baseRate = switch (ticket.getVehicleType()) {
            case MOTORCYCLE -> 50.0;
            case CAR -> 100.0;
            case TRUCK -> 200.0;
        };
        double multiplier = (isPeakHour(ticket.getExitTime())) ? 1.5 : 1.0;
        long hours = Math.max(1, (minutes + 59) / 60);
        return baseRate * hours * multiplier;
    }

    private boolean isPeakHour(LocalDateTime time) {
        int hour = time.getHour();
        return (hour >= 8 && hour <= 11) || (hour >= 17 && hour <= 20);
    }
}