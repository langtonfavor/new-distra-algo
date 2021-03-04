package za.co.discovery.assignment.langton.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import za.co.discovery.assignment.langton.models.Node;
import za.co.discovery.assignment.langton.models.Planet;
import za.co.discovery.assignment.langton.repository.NodeRepository;
import za.co.discovery.assignment.langton.repository.PlanetRepo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


@Component

public class ApplicationListenerInitialize implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    Environment env;
    @Autowired
    NodeRepository nodeRepository;
    @Autowired
    PlanetRepo planetRepo;
    private static final Logger logger = LoggerFactory.getLogger(ApplicationListenerInitialize.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String path= env.getRequiredProperty("Sys.init_path");
        String planetPath= env.getRequiredProperty("Sys.init_planet_path");
       //delete first
        planetRepo.deleteAll();
        nodeRepository.deleteAll();
        //initialize data
        try(BufferedReader br = new BufferedReader(new FileReader(new File(planetPath)))){
            String line= br.readLine();
            while ((line=br.readLine())!=null){
                String[]arr= line.split(",");
                planetRepo.save(new Planet(arr[0].trim(),arr[1].trim()));
                logger.info(line);
            }
        }
        catch (IOException iox){
            System.out.println("Pakaipa!"+iox.toString());
        }
        try(BufferedReader br = new BufferedReader(new FileReader(new File(path)))){
            String line= br.readLine();
            while ((line=br.readLine())!=null){
                String[]arr= line.split(",");
                nodeRepository.save(new Node(arr[1].trim(),arr[2].trim(),Double.parseDouble(arr[3]),Double.parseDouble(arr[4])));
                logger.info(line);
            }
        }
        catch (IOException iox){
            System.out.println("Pakaipa!"+iox.toString());
        }
        logger.info("Still waiting!");
    }
}