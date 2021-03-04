package za.co.discovery.assignment.langton.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.langton.models.Node;
import za.co.discovery.assignment.langton.models.Planet;
import za.co.discovery.assignment.langton.repository.NodeRepository;
import za.co.discovery.assignment.langton.repository.PlanetRepo;

import java.util.*;

@Service
public class RoutesService {
    @Autowired
    NodeRepository nodeRepository;
    @Autowired
    PlanetRepo planetRepo;
    public String lookUp(String planetName){
        String planetNode=null;
        for (Planet p:planetRepo.findAll()
             ) {
            if(p.getName().equalsIgnoreCase(planetName)){
                planetNode= p.getPlanetNode();
                break;
            }
        }
        return planetNode;
    }
    private List<Node> getRouteBetween(String planetOrig, String planetDest){
        String orig=lookUp(planetOrig);
        String dest = lookUp(planetDest);
        List<Node>lst = nodeRepository.getNodeInbetween(orig,dest);
        return lst;
    }
    private List<Node> getPrimeRoute(String planetDest){

        String dest = lookUp(planetDest);
        //List<Node>lst = nodeRepository.getNodeInbetween(orig,dest);
        List<Node>lst = nodeRepository.getPrimeNodes(dest);// get the possible routes
        return lst;
    }
    private List<Node> getSubRoute(String planetDest){

        List<Node>lst = nodeRepository.getPrimeNodes(planetDest);// get the possible routes
        return lst;
    }
    public List<Node>getShortest(String planetOrig, String planetDest){
        String origin = lookUp(planetOrig);
        List<Node>nodeList= getPrimeRoute(planetDest);

        List<List<Node>> tempList= new ArrayList<List<Node>>();
        for (Node n: nodeList ) {


            List<Node> lst1 = new ArrayList<>();
            //add the first one
            Node node1 = n;
            lst1.add(node1);
            String tempOrig = node1.getPlanetOrig();
            do {
                Node tempNode = getSubRoute(tempOrig).get(0);
                lst1.add(tempNode);
                tempOrig = tempNode.getPlanetOrig();

            } while (!tempOrig.equalsIgnoreCase(origin));
            tempList.add(lst1);
        }
        Map<Double,List<Node>> map= new HashMap<>();
        for (List<Node> lst:tempList) {

            DoubleSummaryStatistics summaryStatistics = nodeList.stream()
                    .mapToDouble(n -> n.getSpeedDelay() * n.getDistance())
                    .summaryStatistics();
            map.put(summaryStatistics.getSum(),lst);


        }
      double min=  map.keySet().stream().mapToDouble(d->d).min().orElseThrow(NoSuchElementException::new);//get the smallest

        return map.get(min);
    }
}
