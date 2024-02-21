package edu.hogwarts.controllers;

import edu.hogwarts.HouseDTO;
import edu.hogwarts.models.House;
import edu.hogwarts.repositories.HouseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/houses")
public class HouseController {

    private final HouseRepository houseRepository;
    private final HouseDTO houseDTO;

    public HouseController(HouseRepository houseRepository, HouseDTO houseDTO) {
        this.houseRepository = houseRepository;
        this.houseDTO = houseDTO;
    }


    //H1***********************  GET *************************
    @GetMapping()
    public ResponseEntity<List<HouseDTO>> getAllHouses() {
         List<House> houses = houseRepository.findAll();
                List<HouseDTO> housesDTO  = new ArrayList<>(); //DTO list to hold DTO houses
         if(!houses.isEmpty()) {
             for(House house: houses) {
            HouseDTO houseDTO = new HouseDTO();
            houseDTO.setName(house.getName());
            houseDTO.setFounder(house.getFounder());
            houseDTO.setColors(house.getColors());
            housesDTO.add(houseDTO);
             }
             return ResponseEntity.ok(housesDTO);
         } else {
                return ResponseEntity.notFound().build();
         }
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseDTO> getSingleHouse(@PathVariable int id) {
       Optional<House> house = houseRepository.findById(id);
       if(house.isPresent()) {
           House originalHouse = house.get();
        HouseDTO houseDTO = new HouseDTO(); //Creating a new object
        houseDTO.setName(originalHouse.getName()); // copying the values from original object to DTO object
        houseDTO.setFounder(originalHouse.getFounder());
        houseDTO.setColors(originalHouse.getColors());
        return ResponseEntity.ok(houseDTO);

       } else {
           return ResponseEntity.notFound().build(); //Return 404 Not Found
       }



    }
}
