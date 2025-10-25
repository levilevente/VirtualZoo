package animal.entities;

import animal.Animal;
import visitor.Visitor;

public class Reptile extends Animal {

    public Reptile(String name, int age, int hunger, String mood, int health) {
        super(name, age, hunger, mood, health);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitReptile(this);
    }
}
