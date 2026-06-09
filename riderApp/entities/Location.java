package riderApp.entities;

public class Location {
    private final double longitude;
    private final double latitude;

    public Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public double getDistance(Location other){
        double dx=other.latitude-this.latitude;
        double dy=other.longitude-this.longitude;
        double dis=(dx*dx)+(dy*dy);
        return Math.sqrt(dis);
    }
}
