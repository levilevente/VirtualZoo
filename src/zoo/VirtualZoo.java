package zoo;

import animal.Animal;
import iterator.Aggregate;
import iterator.impl.AnimalIterator;
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
}
