package visitor.concretevisitor;

import animal.entities.Bird;
import animal.entities.Mammal;
import animal.entities.Reptile;
import visitor.Visitor;

public class FeedingVisitor implements Visitor {
    @Override
    public void visitReptile(Reptile reptile) {
        if (reptile.getHunger() >= 10) {
            reptile.setHunger(reptile.getHunger() - 10);
        } else {
            reptile.setHunger(0);
        }
        System.out.println("Feeding reptile: " + reptile.getName());
    }

    @Override
    public void visitMammal(Mammal mammal) {
        if (mammal.getHunger() >= 15) {
            mammal.setHunger(mammal.getHunger() - 15);
        } else {
            mammal.setHunger(0);
        }
        System.out.println("Feeding mammal: " + mammal.getName());
    }

    @Override
    public void visitBird(Bird bird) {
        if (bird.getHunger() >= 5) {
            bird.setHunger(bird.getHunger() - 5);
        } else {
            bird.setHunger(0);
        }
        System.out.println("Feeding bird: " + bird.getName());
    }


}
