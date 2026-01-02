public class ParkingSpot {
    private String id;
    private VehicleType type;
    private Vehicle vehicle;
    private int level;

    public ParkingSpot(int level , String id, VehicleType type){
        this.level = level;
        this.id = id;
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public String getId() {
        return id;
    }

    public VehicleType getType() {
        return type;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean assignVehicle(Vehicle vehicle) {
        if( vehicle == null ) return false;
        if(isCompatible(vehicle.getType())){
            this.vehicle = vehicle;
            return true;
        }
        return false;
    }

    public boolean removeVehicle() {
        if (vehicle == null) return false;
        vehicle = null;
        return true;
    }

    public boolean isAllocated(){
        return vehicle != null;
    }

    private boolean isCompatible(VehicleType vehicleType) {
        return this.type.ordinal() >= vehicleType.ordinal();
    }

    @Override
    public String toString() { return "L" + level + "-" + id; }
}
