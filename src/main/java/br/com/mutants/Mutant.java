package br.com.mutants;

public class Mutant {

    Mutant(Integer id, String name, Integer power) {
        this.id = id;
        this.name = name;
        this.power = power;
    }

    private final Integer id;
    private final String name;
    private final Integer power;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPower() {
        return power;
    }
}