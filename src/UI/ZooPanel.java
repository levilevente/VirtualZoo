package UI;

import iterator.Aggregate;
import iterator.Iterator;
import iterator.RenderableAnimalIterator;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ZooPanel extends JPanel implements Aggregate<RenderableAnimal> {
    private List<RenderableAnimal> renderableAnimals;
    private BufferedImage backgroundImage;

    public ZooPanel() {
        this.renderableAnimals = new ArrayList<>();
        try {
            backgroundImage = javax.imageio.ImageIO.read(new java.io.File("assets/grass.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void addRenderableAnimal(RenderableAnimal renderableAnimal) {
        this.renderableAnimals.add(renderableAnimal);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        for (RenderableAnimal renderableAnimal : renderableAnimals) {
            int x = renderableAnimal.getX();
            int y = renderableAnimal.getY();

            // Draw the image
            g.drawImage(renderableAnimal.getImage(), x, y, this);

            // Draw info text to the right of the image
            g.setFont(new Font("SansSerif", Font.PLAIN, 12));
            g.setColor(Color.BLACK);

            animal.Animal animal = renderableAnimal.getAnimal();
            int textX = x + renderableAnimal.getImage().getWidth(this) + 10;
            int textY = y + 15;

            g.drawString("Name: " + animal.getName(), textX, textY);
            g.drawString("Mood: " + animal.getMood(), textX, textY + 15);
            g.drawString("Health: " + animal.getHealth() + "%", textX, textY + 30);
            g.drawString("Hunger: " + animal.getHunger() + "%", textX, textY + 45);

            if (renderableAnimal.isHighlighted()) {
                g.setColor(Color.RED);
                g.drawRect(x - 2, y - 2, renderableAnimal.getImage().getWidth(this) + 4,
                        renderableAnimal.getImage().getHeight(this) + 4);
            }
        }
    }

    @Override
    public Iterator<RenderableAnimal> createIterator() {
        return new RenderableAnimalIterator(renderableAnimals);
    }
}
