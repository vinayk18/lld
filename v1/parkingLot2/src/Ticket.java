import java.time.LocalDateTime;

public class Ticket {
    private String id;
    private int level;
    private String spot;
    private String licensePLate;
    private VehicleType vehicleType;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Ticket(String id,int level, String spot, String licensePLate, VehicleType vehicleType, LocalDateTime entryTime){
        this.id = id;
        this.level = level;
        this.spot = spot;
        this.licensePLate = licensePLate;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public int getLevel() {
        return level;
    }

    public String getSpot() {
        return spot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public String getLicensePLate() {
        return licensePLate;
    }
    public String getId(){
        return  id;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", level=" + level +
                ", spot=" + spot +
                ", licensePlate=" + licensePLate +
                ", vehicleType=" + vehicleType +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                '}';
    }
}
