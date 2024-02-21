package edu.hogwarts;

import jakarta.persistence.ElementCollection;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HouseDTO {


    private String founder;
    private String name;
    private String color1;
    private String color2;

    public HouseDTO() {}

    public HouseDTO(String founder, String name, String color1, String color2) {
        this.founder = founder;
        this.name = name;
        this.color1 = color1;
        this.color2 = color2;
    }


    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
      //  colors.add(color1);
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;

    }


}
