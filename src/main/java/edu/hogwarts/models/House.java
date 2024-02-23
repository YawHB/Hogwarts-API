package edu.hogwarts.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class House {

    @Id
    private String name;
    private String founder;
    private String color1;
    private String color2;
    @ElementCollection
    private List<String> colors; //ElementCollection laver en join tabel med House og colors, således at tabellerne kan forblive atomar da colors er et array.

    public House() {

    }

    public House( String name, String founder, String color1, String color2) {
        this.name = name;
        this.founder = founder;
        this.color1 = color1;
        this.color2 = color2;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getColor1() {
        return color1;
    }
    public void setColor1(String color1) {
        this.color1 = color1;
        //colors.add(color1);
    }
    public String getColor2() {
        return color2;
    }
    public void setColor2(String color2) {
        this.color2 = color2;
        //colors.add(color2);
    }
    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }
}
