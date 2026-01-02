import java.util.concurrent.atomic.AtomicLong;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class TicketIdGenerator {

    private static final AtomicLong COUNTER = new AtomicLong(0);
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**
     * Generates a unique, human-readable ticket ID.
     * Example: TKT-20251018-000042
     */
    public static String nextId() {
        long seq = COUNTER.incrementAndGet();
        String timestamp = LocalDateTime.now().format(DATE_FMT);
        return "TKT-" + timestamp + "-" + String.format("%05d", seq);
    }
}