package visitor.concretevisitor;

import animal.entities.Bird;
import animal.entities.Mammal;
import animal.entities.Reptile;
import visitor.Visitor;

public class ReportVisitor implements Visitor {
    @Override
    public void visitReptile(Reptile reptile) {
        System.out.println("Reptile Report - Name: " + reptile.getName() +
                ", Age: " + reptile.getAge() +
                ", Hunger: " + reptile.getHunger() +
                ", Mood: " + reptile.getMood() +
                ", Health: " + reptile.getHealth());
    }

    @Override
    public void visitMammal(Mammal mammal) {
        System.out.println("Mammal Report - Name: " + mammal.getName() +
                ", Age: " + mammal.getAge() +
                ", Hunger: " + mammal.getHunger() +
                ", Mood: " + mammal.getMood() +
                ", Health: " + mammal.getHealth());
    }

    @Override
    public void visitBird(Bird bird) {
        System.out.println("Bird Report - Name: " + bird.getName() +
                ", Age: " + bird.getAge() +
                ", Hunger: " + bird.getHunger() +
                ", Mood: " + bird.getMood() +
                ", Health: " + bird.getHealth());
    }
}
