package animal.entities;

import animal.Animal;
import visitor.Visitor;

public class Bird extends Animal {

    public Bird(String name, int age, int hunger, String mood, int health, String type, String species) {
        super(name, age, hunger, mood, health, type, species);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitBird(this);
    }
}
