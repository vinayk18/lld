import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingManager {
    private List<ParkingLevel> parkingLevels;
    private final SpotAllocationStrategy allocationStrategy;
    private Map<String,ParkingSpot> ticketSpotMap = new HashMap<>();

    public ParkingManager( List<ParkingLevel>  levels,SpotAllocationStrategy allocationStrategy){
        this.parkingLevels = levels;
        this.allocationStrategy = allocationStrategy;
    }

    public List<ParkingLevel> getParkingLevels() {
        return parkingLevels;
    }


    public Ticket allocateSpot(Vehicle vh){
        ParkingSpot spot =  allocationStrategy.findSpot(parkingLevels,vh);
        if( spot != null ){
            Ticket ticket  = new Ticket(TicketIdGenerator.nextId(), spot.getLevel(),spot.toString(),vh.getLicensePlate(),vh.getType(), LocalDateTime.now());
            ticketSpotMap.put(ticket.getId(),spot);
            return ticket;
        }
        return null;
    }

    public void releaseSpot(Ticket ticket){
        ParkingSpot spot  = ticketSpotMap.get(ticket.getId());
        spot.removeVehicle();
   }
}
