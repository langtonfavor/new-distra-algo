package za.co.discovery.assignment.langton.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "planets", schema = "app")
public class Planet {
    @Id
    @Column(length=5)
        private String planetNode;
    @Column(length=50)
        private String name;

    public Planet(String planetNode, String name) {
        this.planetNode = planetNode;
        this.name = name;
    }

    public Planet() {
    }

    // One planet - many Nodes
    @OneToMany(mappedBy = "planetNode")
    private List<Node> nodes = new ArrayList<Node>();

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
    public String getPlanetNode() {
        return planetNode;
    }

    public void setPlanetNode(String planetNode) {
        this.planetNode = planetNode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
