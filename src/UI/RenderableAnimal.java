package UI;

import animal.Animal;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class RenderableAnimal {
    private Animal animal;
    private int x;
    private int y;
    private BufferedImage image;
    private Logger logger = Logger.getLogger(RenderableAnimal.class.getName());

    public RenderableAnimal(Animal animal, int x, int y, String imagePath) {
        this.animal = animal;
        this.x = x;
        this.y = y;
        try{
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException e){
            logger.severe("Failed to load image from path: " + imagePath);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Animal getAnimal() {
        return animal;
    }
}
