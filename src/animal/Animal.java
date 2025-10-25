package animal;

import visitor.Visitor;

public abstract class Animal {
    private String name;
    private int age;
    private int hunger;
    private String mood;
    private int health;
    private String type;
    private String species;

    public Animal(
            String name,
            int age,
            int hunger,
            String mood,
            int health,
            String type,
            String species
    ) {
        this.name = name;
        this.age = age;
        this.hunger = hunger;
        this.mood = mood;
        this.health = health;
        this.type = type;
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public abstract void accept(Visitor visitor);

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hunger=" + hunger +
                ", mood='" + mood + '\'' +
                ", health=" + health +
                '}';
    }
}
