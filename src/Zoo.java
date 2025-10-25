import animal.Animal;
import animal.entities.Bird;
import animal.entities.Mammal;
import iterator.Aggregate;
import iterator.AnimalIterator;
import iterator.Iterator;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Zoo implements Aggregate<Animal> {
    private final List<Animal> animals = new ArrayList<>();

    public Zoo(List<Animal> animals) {
        this.animals.addAll(animals);
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    @Override
    public Iterator<Animal> createIterator() {
        return new AnimalIterator(animals);
    }

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Mammal("Lion", 4, 50, "HAPPY", 100));
        animals.add(new Mammal("Tiger", 4, 55, "ANGRY", 120));
        animals.add(new Bird("Eagle", 2, 15, "CALM", 200));
        animals.add(new Bird("Parrot", 2, 1, "HAPPY", 50));

        Zoo zoo = new Zoo(animals);
        Iterator<Animal> iterator = zoo.createIterator();

        Visitor feedingVisitor = new visitor.concretevisitor.FeedingVisitor();
        Visitor medicalVisitor = new visitor.concretevisitor.MedicalVisitor();
        Visitor reportVisitor = new visitor.concretevisitor.ReportVisitor();

        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            System.out.println("--- Visiting " + animal.getName() + " ---");
            System.out.println(animal);
            animal.accept(feedingVisitor);
            animal.accept(medicalVisitor);
            animal.accept(reportVisitor);
            System.out.println(animal);
            System.out.println("------------------------------\n");
        }
    }
}
