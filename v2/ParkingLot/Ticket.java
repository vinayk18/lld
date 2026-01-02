package ParkingLot;

import java.util.concurrent.atomic.AtomicInteger;

public class Ticket {
    private final int ticketId;
    private final String spotId;
    private final Vehicle vehicle;
    private final long entryTime;

    TicketStatus status;

    private static final AtomicInteger ID_GEN = new AtomicInteger(0);

    Ticket(String spotId,Vehicle vehicle, long entryTime , TicketStatus status){
        this.spotId = spotId;
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.status = status;
        this.ticketId = ID_GEN.incrementAndGet();
    }

    public Vehicle vehicle() {
        return vehicle;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public String getSpotId() {
        return spotId;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void close() {
        this.status = TicketStatus.CLOSED;
    }

    public Integer getTicketId() {
        return ticketId;
    }
}
