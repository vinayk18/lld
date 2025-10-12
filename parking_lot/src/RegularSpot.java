public class RegularSpot implements ParkingSpot{
    private int spotNumber;
    private Vehicle vehicle;

    RegularSpot(int spotNumber, Vehicle vehicle){
        this.spotNumber = spotNumber;
        this.vehicle = vehicle;
    }

    @Override
    public boolean isAvailable() {
        return vehicle == null;
    }

    @Override
    public void occupy(Vehicle vehicle) {
        if(isAvailable())
            this.vehicle = vehicle;
    }

    @Override
    public void vacate() {
        vehicle = null;
    }

    @Override
    public int getSpotNumber() {
        return spotNumber;
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }
}
