package UI;


import animal.entities.Bird;
import animal.entities.Mammal;
import animal.entities.Reptile;
import zoo.VirtualZoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Application extends JFrame {
    private VirtualZoo zoo;
    private ZooPanel zooPanel;

    public Application() throws HeadlessException {
        this.setLayout(new BorderLayout());
        this.zooPanel = new ZooPanel();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse clicked at: (" + e.getX() + ", " + e.getY() + ")");
                JMenuItem popupItem = new JMenuItem("Add new Animal");
                popupItem.addActionListener(ae -> openAnimalForm(e.getX(), e.getY()));

                JPopupMenu popupMenu = new JPopupMenu();
                popupMenu.add(popupItem);
                popupMenu.show(e.getComponent(), e.getX(), e.getY());

            }
        });

        zoo = new VirtualZoo();
        Bird bird1 = new Bird("Polly", 2, 30, "EXCITED", 90, "Parrot", "macaw");
        Bird bird2 = new Bird("Tweety", 1, 20, "HAPPY", 95, "Canary", "canary");
        Mammal mammal1 = new Mammal("Simba", 4, 50, "HAPPY", 100, "Cat", "lion");
        Reptile reptile1 = new Reptile("Rango", 3, 40, "CALM", 80, "Lizard", "iguana");

        RenderableAnimal renderableBird1 = new RenderableAnimal(bird1, 50, 50, "assets/images/parrot.png");
        RenderableAnimal renderableBird2 = new RenderableAnimal(bird2, 200, 50, "assets/images/parrot.png");
        RenderableAnimal renderableMammal = new RenderableAnimal(mammal1, 50, 200, "assets/images/lion.png");
        RenderableAnimal renderableReptile = new RenderableAnimal(reptile1, 200, 200, "assets/images/turkey.png");

        zoo.addAnimal(bird1);
        zoo.addAnimal(bird2);
        zoo.addAnimal(mammal1);
        zoo.addAnimal(reptile1);

        zooPanel.addRenderableAnimal(renderableBird1);
        zooPanel.addRenderableAnimal(renderableBird2);
        zooPanel.addRenderableAnimal(renderableMammal);
        zooPanel.addRenderableAnimal(renderableReptile);

        this.add(zooPanel, BorderLayout.CENTER);
    }

    private void openAnimalForm(int x, int y) {
        JDialog formDialog = new JDialog(this, "Add New Animal", true);
        formDialog.setLayout(new GridLayout(0, 2));

        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField hungerField = new JTextField();
        JTextField moodField = new JTextField();
        JTextField healthField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField speciesField = new JTextField();

        String[] categories = {"Bird", "Mammal", "Reptile"};
        JComboBox<String> categoryBox = new JComboBox<>(categories);
        JTextField imagePathField = new JTextField();
        formDialog.add(new JLabel("Category:"));
        formDialog.add(categoryBox);
        formDialog.add(new JLabel("Name:"));
        formDialog.add(nameField);
        formDialog.add(new JLabel("Age:"));
        formDialog.add(ageField);
        formDialog.add(new JLabel("Hunger:"));
        formDialog.add(hungerField);
        formDialog.add(new JLabel("Mood:"));
        formDialog.add(moodField);
        formDialog.add(new JLabel("Health:"));
        formDialog.add(healthField);
        formDialog.add(new JLabel("Type:"));
        formDialog.add(typeField);
        formDialog.add(new JLabel("Species:"));
        formDialog.add(speciesField);
        formDialog.add(new JLabel("Image Path:"));
        formDialog.add(imagePathField);
        JButton submitButton = new JButton("Add Animal");
        submitButton.addActionListener(e -> {
            String category = (String) categoryBox.getSelectedItem();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            int hunger = Integer.parseInt(hungerField.getText());
            String mood = moodField.getText();
            int health = Integer.parseInt(healthField.getText());
            String type = typeField.getText();
            String species = speciesField.getText();
            String imagePath = imagePathField.getText();
            addNewAnimal(category, name, age, hunger, mood, health, type, species, imagePath, x, y);
            formDialog.dispose();
        });

        formDialog.add(submitButton);
        formDialog.pack();
        formDialog.setLocationRelativeTo(this);
        formDialog.setVisible(true);

    }

    private void addNewAnimal(
            String category,
            String name,
            int age,
            int hunger,
            String mood,
            int health,
            String type,
            String species,
            String imagePath,
            int x,
            int y
    ) {
       switch (category) {
           case "Bird":
               Bird newBird = new Bird(name, age, hunger, mood, health, type, species);
               zoo.addAnimal(newBird);
               RenderableAnimal renderableBird = new RenderableAnimal(newBird, x, y, imagePath);
               zooPanel.addRenderableAnimal(renderableBird);
               break;
           case "Mammal":
               Mammal newMammal = new Mammal(name, age, hunger, mood, health, type, species);
               zoo.addAnimal(newMammal);
               RenderableAnimal renderableMammal = new RenderableAnimal(newMammal, x, y, imagePath);
               zooPanel.addRenderableAnimal(renderableMammal);
               break;
           case "Reptile":
               Reptile newReptile = new Reptile(name, age, hunger, mood, health, type, species);
               zoo.addAnimal(newReptile);
               RenderableAnimal renderableReptile = new RenderableAnimal(newReptile, x, y, imagePath);
               zooPanel.addRenderableAnimal(renderableReptile);
               break;
           default:
               throw new IllegalArgumentException("Unknown animal category: " + category);
       }
    }



    public static void main(String[] args) {
        Application app = new Application();
        app.setTitle("Virtual Zoo Management System");
        app.setSize(800, 600);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
