package zoo;

import animal.Animal;
import iterator.Aggregate;
import iterator.AnimalIterator;
import iterator.Iterator;

import java.util.ArrayList;
import java.util.List;

public class VirtualZoo implements Aggregate<Animal> {
    private List<Animal> animals = new ArrayList<>();

    public VirtualZoo(List<Animal> animals) {
        this.animals.addAll(animals);
    }

    public VirtualZoo() {
        this.animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    @Override
    public Iterator<Animal> createIterator() {
        return new AnimalIterator(animals);
    }

    /*public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Mammal("Simba", 4, 50, "HAPPY", 100, "Cat", "lion"));
        animals.add(new Bird("Polly", 2, 30, "EXCITED", 90, "Parrot", "macaw"));
        animals.add(new Reptile("Rango", 3, 40, "CALM", 80, "Lizard", "iguana"));


        zoo.VirtualZoo zoo = new zoo.VirtualZoo(animals);
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
    }*/
}
