package visitor.concretevisitor;

import animal.entities.Bird;
import animal.entities.Mammal;
import animal.entities.Reptile;
import visitor.Visitor;

public class FeedingVisitor implements Visitor {
    @Override
    public void visitReptile(Reptile reptile) {
        reptile.setHunger(reptile.getHunger() - 10);
        System.out.println("Feeding reptile: " + reptile.getName());
    }

    @Override
    public void visitMammal(Mammal mammal) {
        mammal.setHunger(mammal.getHunger() - 15);
        System.out.println("Feeding mammal: " + mammal.getName());
    }

    @Override
    public void visitBird(Bird bird) {
        bird.setHunger(bird.getHunger() - 5);
        System.out.println("Feeding bird: " + bird.getName());
    }


}
