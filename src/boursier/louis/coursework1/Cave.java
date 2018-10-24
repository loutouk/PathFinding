package boursier.louis.coursework1;

import java.util.ArrayList;
import java.util.Comparator;

class Cave implements Comparable<Cave>{

    private String id;
    private double x;
    private double y;
    private ArrayList<Cave> links;
    private double distanceFromStart;
    private Cave closestCave;

    public Cave(double x, double y, String id) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.links = new ArrayList<>();
        distanceFromStart = Double.MAX_VALUE;
        closestCave = null;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getId() {
        return id;
    }

    public double getDistanceFromStart() {
        return distanceFromStart;
    }

    public Cave getClosestCave() {
        return closestCave;
    }

    public void setDistanceFromStart(double distanceFromStart) {
        this.distanceFromStart = distanceFromStart;
    }

    public void setClosestCave(Cave closestCave) {
        this.closestCave = closestCave;
    }

    public void addLink(Cave other){
        this.links.add(other);
    }

    public ArrayList<Cave> getLinks() {
        return links;
    }

    @Override
    public String toString() {
        return "Cave{" +
                "x=" + x +
                ", y=" + y +
                ", links=" + links.size() +
                '}';
    }

    public int compareTo(Cave other) {
        return Double.compare(this.distanceFromStart, other.distanceFromStart);
    }

    public double getDistance(Cave other){
        return Math.sqrt((Math.pow(other.x-x,2))+Math.pow(other.y-y,2));
    }
}