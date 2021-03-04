package za.co.discovery.assignment.langton.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.discovery.assignment.langton.models.Node;
import za.co.discovery.assignment.langton.models.Planet;
import za.co.discovery.assignment.langton.repository.NodeRepository;
import za.co.discovery.assignment.langton.repository.PlanetRepo;
import za.co.discovery.assignment.langton.services.RoutesService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class GraphController {

    @Autowired
    NodeRepository nodeRepository;
    @Autowired
    PlanetRepo planetRepo;
    @Autowired
    RoutesService routesService;

//    @PostMapping("/graphs")
//    public ResponseEntity<Node> create(@RequestBody Graph graph) {
//
//        return ResponseEntity.ok(graphImp.saveOrUpdate(graph));
//    }

    @DeleteMapping("/graphs/{id}")
    public void deleteById(@PathVariable Long id) {


    }

    @GetMapping("/graphs")
    public ResponseEntity<List<Node>> findAll() {
        List<Node>lst = nodeRepository.getAllRoutes();
        return ResponseEntity.ok().body(lst);
    }
    @GetMapping("/planets")
    public ResponseEntity<Iterable<Planet>> findAllPlanets() {
        Iterable<Planet> lst = planetRepo.findAll();
        return ResponseEntity.ok().body(lst);
    }

    @GetMapping("/graphs/{id}")
    public ResponseEntity<Node> findById(@PathVariable Long id) {

        return ResponseEntity.ok().body(new Node());
    }
    @GetMapping("/route/{originPlanet}/{destPlanet}")
    public ResponseEntity<List<Node>> shortestRoute(@PathVariable String originPlanet, @PathVariable String destPlanet) {

           List<Node>lst = routesService.getShortest(originPlanet,destPlanet);
        return ResponseEntity.ok().body(lst);
    }

    @GetMapping("/planets/{id}")
    public ResponseEntity<Planet> findByName(@PathVariable String id) {
         Optional<Planet> p = planetRepo.findById(id);
        return ResponseEntity.ok().body(p.get());
    }

    @PostMapping("/routes/create")
    public ResponseEntity<Node> create(@RequestBody Node node) {
        planetRepo.save(new Planet(node.getPlanetNode(),node.getName()));

        return ResponseEntity.ok(nodeRepository.save(node));
    }

}
