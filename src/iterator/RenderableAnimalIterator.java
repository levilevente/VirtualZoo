package iterator;

import UI.RenderableAnimal;

import java.util.List;

public class RenderableAnimalIterator implements Iterator<RenderableAnimal> {
    private final List<RenderableAnimal> renderableAnimals;
    private int position = 0;

    public RenderableAnimalIterator(List<RenderableAnimal> renderableAnimals) {
        this.renderableAnimals = renderableAnimals;
    }

    @Override
    public boolean hasNext() {
        return position < renderableAnimals.size();
    }

    @Override
    public RenderableAnimal next() {
        return renderableAnimals.get(position++);
    }
}
