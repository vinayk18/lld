import java.time.Duration;
import java.time.LocalDateTime;

public class PaymentCalculator {

    private final double perDayFine;

    // simple constructor to allow different policies; default 1.0 per day
    public PaymentCalculator(double perDayFine) {
        this.perDayFine = perDayFine;
    }

    public PaymentCalculator() {
        this(1.0);
    }

    public double calculateFine(LocalDateTime dueDate, LocalDateTime returnDate) {
        if (returnDate == null || !returnDate.isAfter(dueDate)) return 0.0;
        long daysLate = Duration.between(dueDate.toLocalDate().atStartOfDay(),
                returnDate.toLocalDate().atStartOfDay()).toDays();
        if (daysLate <= 0) return 0.0;
        return daysLate * perDayFine;
    }
}