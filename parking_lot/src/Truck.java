public class Truck implements Vehicle{
    private String licensePlate;

    Truck(String licensePlate){
        this.licensePlate = licensePlate;
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.LARGE;
    }

    @Override
    public String getLincensePlate() {
        return licensePlate;
    }
}
