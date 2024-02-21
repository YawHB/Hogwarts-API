package edu.hogwarts.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class House {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    private String founder;

    @ElementCollection
    private List<String> colors; //ElementCollection laver en join tabel med House og colors, således at tabellerne kan forblive atomar da colors er et array.

    public House() {

    }

    public House(String name, String founder, List<String> colors) {
        this.name = name;
        this.founder = founder;
        this.colors = colors;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }
}
