package visitor.concretevisitor;

import animal.entities.Bird;
import animal.entities.Mammal;
import animal.entities.Reptile;
import visitor.Visitor;

public class MedicalVisitor implements Visitor {
    @Override
    public void visitReptile(Reptile reptile) {
        reptile.setHealth(reptile.getHealth() + 10);
        System.out.println("Providing medical care to reptile: " + reptile.getName());
    }

    @Override
    public void visitMammal(Mammal mammal) {
        mammal.setHealth(mammal.getHealth() + 15);
        System.out.println("Providing medical care to mammal: " + mammal.getName());
    }

    @Override
    public void visitBird(Bird bird) {
        bird.setHealth(bird.getHealth() + 5);
        System.out.println("Providing medical care to bird: " + bird.getName());
    }
}
