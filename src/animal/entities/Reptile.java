package animal.entities;

import animal.Animal;
import visitor.Visitor;

public class Reptile extends Animal {

    public Reptile(String name, int age, int hunger, String mood, int health, String type, String species) {
        super(name, age, hunger, mood, health, type, species);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitReptile(this);
    }
}
