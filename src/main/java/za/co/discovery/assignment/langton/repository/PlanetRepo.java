package za.co.discovery.assignment.langton.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import za.co.discovery.assignment.langton.models.Node;
import za.co.discovery.assignment.langton.models.Planet;

import java.util.List;

public interface PlanetRepo extends CrudRepository<Planet,String> {
//    // Gets all Todos of the given user
//    @Query("SELECT n FROM Node n WHERE n.planetNode = ?1")
//    Node getNodeByNode(String planetNode);
//
//    // Gets Todos related to given user in the desc order of id so that most recent comes first
//    @Query("SELECT n FROM Node n WHERE n.planetNode = ?1 AND  n.planetOrig =?2")
//    List<Node> getNodeInbetween(String destNode, String originNode);
//    @Query("SELECT p FROM Planet p")
//    List<Node> getAllPlanets();
}
