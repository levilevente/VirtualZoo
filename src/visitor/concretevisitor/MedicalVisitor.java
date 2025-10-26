package visitor.concretevisitor;

import animal.entities.Bird;
import animal.entities.Mammal;
import animal.entities.Reptile;
import visitor.Visitor;

public class MedicalVisitor implements Visitor {
    @Override
    public void visitReptile(Reptile reptile) {
        if (reptile.getHealth() <= 90) {
            reptile.setHealth(reptile.getHealth() + 10);
        } else {
            reptile.setHealth(100);
        }
        System.out.println("Providing medical care to reptile: " + reptile.getName());
    }

    @Override
    public void visitMammal(Mammal mammal) {
        if (mammal.getHealth() <= 85) {
            mammal.setHealth(mammal.getHealth() + 15);
        } else {
            mammal.setHealth(100);
        }
        System.out.println("Providing medical care to mammal: " + mammal.getName());
    }

    @Override
    public void visitBird(Bird bird) {
        if (bird.getHealth() <= 95) {
            bird.setHealth(bird.getHealth() + 5);
        } else {
            bird.setHealth(100);
        }
        System.out.println("Providing medical care to bird: " + bird.getName());
    }
}
