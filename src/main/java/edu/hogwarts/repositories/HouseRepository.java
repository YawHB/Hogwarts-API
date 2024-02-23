package edu.hogwarts.repositories;

import edu.hogwarts.models.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, String> {
    Optional<House> findByName(String name);

   // Optional<House> findHouseByName(String name);
}
