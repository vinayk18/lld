public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        Vehicle v1 = new Car("c1");
        parkingLot.enterVehicle(v1);

        Vehicle t1 = new Truck("t1");
        Vehicle t2 = new Truck("t2");
        parkingLot.enterVehicle(t1);
        parkingLot.enterVehicle(t2);

        parkingLot.exitVehicle(v1);
        parkingLot.exitVehicle(t1);

        parkingLot.enterVehicle(t2);
    }
}