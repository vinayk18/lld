package LibraryManagementSystem;
import java.time.LocalDateTime;

public class Reservation {
    private final int reservationId;
    private final Book book;
    private final Member member;
    private final LocalDateTime createdAt;
    private final LocalDateTime expiresAt;

    private ReservationStatus status;

    public Reservation(int reservationId,
                       Book book,
                       Member member,
                       LocalDateTime createdAt,
                       LocalDateTime expiresAt) {

        this.reservationId = reservationId;
        this.book = book;
        this.member = member;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.status = ReservationStatus.ACTIVE;
    }

    public int getReservationId() {
        return reservationId;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public boolean isActive() {
        return status == ReservationStatus.ACTIVE;
    }

    public boolean isExpired(LocalDateTime now) {
        return isActive() && now.isAfter(expiresAt);
    }

    public void markFulfilled() {
        if (status != ReservationStatus.ACTIVE) {
            throw new IllegalStateException("Reservation not active");
        }
        status = ReservationStatus.FULFILLED;
    }

    public void markExpired() {
        if (status != ReservationStatus.ACTIVE) {
            throw new IllegalStateException("Reservation not active");
        }
        status = ReservationStatus.EXPIRED;
    }

    public void cancel() {
        if (status != ReservationStatus.ACTIVE) {
            throw new IllegalStateException("Reservation not active");
        }
        status = ReservationStatus.CANCELLED;
    }
}