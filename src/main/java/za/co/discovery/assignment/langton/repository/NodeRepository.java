package za.co.discovery.assignment.langton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.discovery.assignment.langton.models.Node;

import java.util.List;

@Repository
public interface NodeRepository  extends CrudRepository<Node,Integer> {
    // Gets all Todos of the given user
    @Query("SELECT n FROM Node n WHERE n.planetNode = ?1")
    Node getNodeByNode(String planetNode);

    // Gets Todos related to given user in the desc order of id so that most recent comes first
    @Query("SELECT n FROM Node n WHERE n.planetNode = ?1 AND  n.planetOrig =?2")
    List<Node> getNodeInbetween(String destNode, String originNode);
    @Query("SELECT n FROM Node n")
    List<Node> getAllRoutes();
    @Query("SELECT n FROM Node n WHERE n.planetNode = ?1")
    List<Node> getPrimeNodes(String destNode);


}
