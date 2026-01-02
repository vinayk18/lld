package ParkingLot;

import java.time.Instant;
import java.util.*;

public class ParkingLot {

    private List<ParkingLevel> levels = new ArrayList<>();
    private final ParkingStrategy parkingStrategy;
    private final PricingStrategy pricingStrategy;
    private final Map<Integer, ParkingSpot> activeTickets = new HashMap<>();

    ParkingLot( List<ParkingLevel> levels , ParkingStrategy parkingStrategy, PricingStrategy pricingStrategy){
        this.levels = levels;
        this.parkingStrategy = parkingStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    Ticket enter( Vehicle vehicle ){
        List<ParkingSpot> candidates = findCandidateSpots(vehicle);
        ParkingSpot spot = parkingStrategy.selectSpot(vehicle,candidates);
        if( spot == null ) {
            throw new IllegalStateException("Parking lot full");
        }
        spot.occupy(vehicle);
        Ticket ticket = generateTicket(vehicle,spot);
        activeTickets.put(ticket.getTicketId(),spot);
        return ticket;
    }

    int exit( Ticket ticket ){
        ParkingSpot spot = activeTickets.remove(ticket.getTicketId());
        if( spot == null || ticket.getStatus().equals(TicketStatus.CLOSED)){
            throw new IllegalArgumentException("Invalid ticket");
        }
        int fare = pricingStrategy.calculateFare(ticket);
        spot.release();
        ticket.close();
        return fare;
    }

    private List<ParkingSpot> findCandidateSpots(Vehicle vehicle) {
        List<ParkingSpot> result = new ArrayList<>();

        for (ParkingLevel level : levels) {
            for (ParkingSpot spot : level.getSpots()) {
                if (spot.isFree() && isCompatible(vehicle, spot)) {
                    result.add(spot);
                }
            }
        }
        return result;
    }

    private boolean isCompatible(Vehicle vehicle, ParkingSpot spot) {
        return true;
    }

    private Ticket generateTicket(Vehicle vehicle, ParkingSpot spot ){
        return new Ticket(spot.getLocation(),vehicle, Instant.now().getEpochSecond(), TicketStatus.ACTIVE);
    }

}
