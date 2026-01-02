public class MotorCycle implements Vehicle{
    private String licensePlate;

    MotorCycle(String licensePlate){
        this.licensePlate = licensePlate;
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.SMALL;
    }

    @Override
    public String getLincensePlate() {
        return licensePlate;
    }
}
