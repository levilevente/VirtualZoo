package iterator.impl;

import animal.Animal;
import iterator.Iterator;

import java.util.List;

public class AnimalIterator implements Iterator<Animal> {
    private final List<Animal> animals;
    private int position = 0;

    public AnimalIterator(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public boolean hasNext() {
        return position < animals.size();
    }

    @Override
    public Animal next() {
        return animals.get(position++);
    }
}
