import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private ParkingManager parkingManager;
    private Map<Vehicle,Ticket> ticketMap = new HashMap<>();
    PaymentProcessor paymentProcessor = new PaymentProcessor();
    private FareStrategy fareStrategy;


    ParkingLot(){
        initializeParkingLot();
    }

    private void initializeParkingLot(){
        ParkingSpot pk1 = new ParkingSpot(0,"mc1",VehicleType.MOTORCYCLE);
        ParkingSpot pk2 = new ParkingSpot(0,"mc2",VehicleType.MOTORCYCLE);
        ParkingSpot pk3 = new ParkingSpot(0,"c1",VehicleType.CAR);
        ParkingSpot pk4= new ParkingSpot(0,"c2",VehicleType.CAR);
        ParkingSpot pk5 = new ParkingSpot(0,"c3",VehicleType.CAR);
        ParkingSpot pk6 = new ParkingSpot(0,"c4",VehicleType.CAR);
        ParkingSpot pk7 = new ParkingSpot(0,"t1",VehicleType.TRUCK);


        Map<VehicleType, List<ParkingSpot>> map = new HashMap<>();

        map.put(VehicleType.MOTORCYCLE,List.of(pk1,pk2));
        map.put(VehicleType.CAR,List.of(pk3,pk4,pk5,pk6));
        map.put(VehicleType.TRUCK,List.of(pk7));

        ParkingLevel pl = new ParkingLevel(0,map);

        parkingManager  = new ParkingManager(List.of(pl), new SmallestFitStrategy());
        fareStrategy = new DynamicFareStrategy();

    }

    public void enterVehicle(Vehicle vh){
        Ticket ticket = parkingManager.allocateSpot(vh);
        if( ticket == null ){
            System.out.println("no spots available for vehicle:"+ vh.getLicensePlate());
            return;
        }
        System.out.println(ticket);
        ticketMap.put(vh,ticket);
    }

    public void exitVehicle(Vehicle vh){
        Ticket ticket = ticketMap.get(vh);
        ticket.setExitTime(LocalDateTime.now());
        double fare = fareStrategy.calculateFare(ticket);
        paymentProcessor.processPayment(fare);
        parkingManager.releaseSpot(ticket);
    }
}
