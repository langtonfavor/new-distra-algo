package za.co.discovery.assignment.langton.models;

import javax.persistence.*;

@Entity
@Table(name = "nodes", schema = "app")
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int routeId;
    @Transient
    private String name;
    private String planetOrig;
    @Column(length=5)
    private String planetNode;
    private double speedDelay;
    private double distance;

    public Node() {
    }

    public Node(String planetOrig, String planetNode, double speedDelay, double distance) {
        this.planetOrig = planetOrig;
        this.planetNode = planetNode;
        this.speedDelay = speedDelay;
        this.distance = distance;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getName() {
        return name;
    }

    public String getPlanetNode() {
        return planetNode;
    }

    public void setPlanetNode(String planetNode) {
        this.planetNode = planetNode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanetOrig() {
        return planetOrig;
    }

    public void setPlanetOrig(String planetOrig) {
        this.planetOrig = planetOrig;
    }



    public double getSpeedDelay() {
        return speedDelay;
    }

    public void setSpeedDelay(double speedDelay) {
        this.speedDelay = speedDelay;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
