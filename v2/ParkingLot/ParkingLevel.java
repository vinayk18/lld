package ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLevel {

    int level;
    List<ParkingSpot> spots = new ArrayList<>();

    public ParkingLevel( int level, List<ParkingSpot> spots ){
        this.level = level;
        this.spots = spots;
    }

    public int getLevel() {
        return level;
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

}
