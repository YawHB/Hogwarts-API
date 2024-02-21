package edu.hogwarts;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HouseDTO {


    private String founder;
    private String name;
    private List<String> colors;


    public HouseDTO() {}

    public HouseDTO(String founder, String name, List<String> colors) {
        this.founder = founder;
        this.name = name;
        this.colors = colors;
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

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }
}
