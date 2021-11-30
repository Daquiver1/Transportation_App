//package src;

public class Route {
    StringBuilder full_path;
    double distance;
    double time_taken;

    Route(StringBuilder full_path, double distance, double time_taken){
        this.full_path = full_path;
        this.distance = distance;
        this.time_taken = time_taken;
    }
}
