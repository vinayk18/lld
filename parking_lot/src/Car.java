public class Car implements Vehicle{
    private String licensePlate;

    Car( String licensePlate){
        this.licensePlate = licensePlate;
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }

    @Override
    public String getLincensePlate() {
        return licensePlate;
    }
}
