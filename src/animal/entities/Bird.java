package animal.entities;

import animal.Animal;
import visitor.Visitor;

public class Bird extends Animal {

    public Bird(String name, int age, int hunger, String mood, int health) {
        super(name, age, hunger, mood, health);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitBird(this);
    }
}
