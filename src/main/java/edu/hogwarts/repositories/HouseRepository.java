package edu.hogwarts.repositories;

import edu.hogwarts.models.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Integer> {
    House findByName(String name);
}
