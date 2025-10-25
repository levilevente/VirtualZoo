package UI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ZooPanel extends JPanel {
    private List<RenderableAnimal> renderableAnimals;

    public ZooPanel() {
        this.renderableAnimals = new ArrayList<>();
    }

    public void addRenderableAnimal(RenderableAnimal renderableAnimal) {
        this.renderableAnimals.add(renderableAnimal);
        repaint();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        for (RenderableAnimal renderableAnimal : renderableAnimals) {
            g.drawImage(renderableAnimal.getImage(), renderableAnimal.getX(), renderableAnimal.getY(), this);
        }
    }
}
