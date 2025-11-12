package UI;


import animal.Animal;
import animal.entities.Bird;
import animal.entities.Mammal;
import animal.entities.Reptile;
import iterator.Iterator;
import visitor.Visitor;
import visitor.concretevisitor.FeedingVisitor;
import zoo.VirtualZoo;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

public class Application extends JFrame {
    private VirtualZoo zoo;
    private ZooPanel zooPanel;
    private JButton feedButton;
    private JButton healButton;
    private JButton iterateButton;
    private Iterator<RenderableAnimal> iterateAnimalsOnButtonPress;
    private AtomicReference<RenderableAnimal> previousIterateAnimalsOnButtonPress;


    public Application() throws HeadlessException {
        this.setLayout(new BorderLayout());
        this.zooPanel = new ZooPanel();
        this.feedButton = new JButton("Feed All Animals");
        this.healButton = new JButton("Heal All Animals");
        this.iterateButton = new JButton("Iterate Animals");

        this.iterateAnimalsOnButtonPress = zooPanel.createIterator();
        this.previousIterateAnimalsOnButtonPress = new AtomicReference<>(null);

        this.feedButton.addActionListener(e -> {
            Iterator<RenderableAnimal> iterator = zooPanel.createIterator();
            Visitor feedingVisitor = new FeedingVisitor();

            AtomicReference<RenderableAnimal> previous = new AtomicReference<>(null);

            Timer timer = new Timer(1000, null);

            timer.addActionListener(ev -> {

                RenderableAnimal previousAnimal = previous.get();
                if (iterator.hasNext()) {
                    // un-highlight previous
                    if (previousAnimal != null) {
                        previousAnimal.setHighlighted(false);
                    }

                    // highlight current
                    RenderableAnimal renderable = iterator.next();
                    renderable.setHighlighted(true);
                    previous.set(renderable);

                    // perform the "visit"
                    Animal animal = renderable.getAnimal();
                    animal.accept(feedingVisitor);

                    // repaint after update
                    zooPanel.repaint();
                } else {
                    // un-highlight the last one and stop
                    if (previousAnimal != null) {
                        previousAnimal.setHighlighted(false);
                    }
                    zooPanel.repaint();
                    timer.stop();
                }
            });

            timer.start();
        });

        this.healButton.addActionListener(e -> {
            Iterator<RenderableAnimal> iterator = zooPanel.createIterator();
            Visitor medicalVisitor = new visitor.concretevisitor.MedicalVisitor();

            AtomicReference<RenderableAnimal> previous = new AtomicReference<>(null);

            Timer timer = new Timer(1000, null);

            timer.addActionListener(ev -> {
                RenderableAnimal previousAnimal = previous.get();
                if (iterator.hasNext()) {
                    // un-highlight previous
                    if (previousAnimal != null) {
                        previousAnimal.setHighlighted(false);
                    }

                    // highlight current
                    RenderableAnimal renderable = iterator.next();
                    renderable.setHighlighted(true);
                    previous.set(renderable);

                    // perform the "visit"
                    Animal animal = renderable.getAnimal();
                    animal.accept(medicalVisitor);

                    // repaint after update
                    zooPanel.repaint();
                } else {
                    // un-highlight the last one and stop
                    if (previousAnimal != null) {
                        previousAnimal.setHighlighted(false);
                    }
                    zooPanel.repaint();
                    timer.stop();
                }
            });

            timer.start();
        });

        this.iterateButton.addActionListener(e -> {

            RenderableAnimal previousAnimal = previousIterateAnimalsOnButtonPress.get();
            if (previousAnimal != null) {
                previousAnimal.setHighlighted(false);
            }

            if (iterateAnimalsOnButtonPress.hasNext()) {
                RenderableAnimal renderable = iterateAnimalsOnButtonPress.next();
                renderable.setHighlighted(!renderable.isHighlighted());
                previousIterateAnimalsOnButtonPress.set(renderable);
                zooPanel.repaint();
            } else {
                // Reset the iterator when done
                iterateAnimalsOnButtonPress = zooPanel.createIterator();
                previousIterateAnimalsOnButtonPress.set(null);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse clicked at: (" + e.getX() + ", " + e.getY() + ")");
                JMenuItem popupItem = new JMenuItem("Add new Animal");
                popupItem.addActionListener(ae -> openAnimalForm(e.getX(), e.getY() - 100));

                JPopupMenu popupMenu = new JPopupMenu();
                popupMenu.add(popupItem);
                popupMenu.show(e.getComponent(), e.getX(), e.getY());

            }
        });

        zoo = new VirtualZoo();
        Animal bird1 = new Bird("Polly", 2, 30, "EXCITED", 90, "Parrot", "macaw");
        Animal tiger = new Mammal("Shir Khan", 1, 20, "ANGRY", 95, "Lion", "Bengal");
        Animal mammal1 = new Mammal("Simba", 4, 50, "HAPPY", 100, "Cat", "lion");
        Animal reptile1 = new Reptile("Rango", 3, 40, "CALM", 80, "Lizard", "iguana");
        Animal cow = new Mammal("Bessie", 5, 60, "CALM", 85, "Cow", "Holstein");
        Animal monkey = new Mammal("George", 2, 30, "PLAYFUL", 90, "Monkey", "Capuchin");
        Animal ram = new Mammal("Dolly", 3, 40, "CURIOUS", 88, "Sheep", "Merino");

        RenderableAnimal renderableBird1 = new RenderableAnimal(bird1, 50, 50, "assets/images/parrot.png");
        RenderableAnimal renderableMammal1 = new RenderableAnimal(tiger, 300, 50, "assets/images/tiger.png");
        RenderableAnimal renderableMammal2 = new RenderableAnimal(mammal1, 550, 50, "assets/images/lion.png");
        RenderableAnimal renderableReptile = new RenderableAnimal(reptile1, 50, 150, "assets/images/iguana.png");
        RenderableAnimal renderableCow = new RenderableAnimal(cow, 300, 150, "assets/images/cow.png");
        RenderableAnimal renderableMonkey = new RenderableAnimal(monkey, 550, 150, "assets/images/monkey.png");
        RenderableAnimal renderableRam = new RenderableAnimal(ram, 50, 250, "assets/images/ram.png");

        zoo.addAnimal(bird1);
        zoo.addAnimal(tiger);
        zoo.addAnimal(mammal1);
        zoo.addAnimal(reptile1);
        zoo.addAnimal(cow);
        zoo.addAnimal(monkey);
        zoo.addAnimal(ram);

        zooPanel.addRenderableAnimal(renderableBird1);
        zooPanel.addRenderableAnimal(renderableMammal1);
        zooPanel.addRenderableAnimal(renderableMammal2);
        zooPanel.addRenderableAnimal(renderableReptile);
        zooPanel.addRenderableAnimal(renderableCow);
        zooPanel.addRenderableAnimal(renderableMonkey);
        zooPanel.addRenderableAnimal(renderableRam);


        this.add(zooPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(feedButton);
        buttonPanel.add(healButton);
        buttonPanel.add(iterateButton);
        this.add(buttonPanel, BorderLayout.NORTH);
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
        JTextField imagePathField = new JTextField();

        String[] categories = {"Bird", "Mammal", "Reptile"};
        JComboBox<String> categoryBox = new JComboBox<>(categories);

        String[] moods = {"HAPPY", "SAD", "ANGRY", "CALM", "EXCITED", "PLAYFUL", "CURIOUS"};
        JComboBox<String> moodBox = new JComboBox<>(moods);
        moodField.setText("HAPPY");

        String[] healthStatuses = {"100", "90", "80", "70", "60", "50", "40", "30", "20", "10", "0"};
        JComboBox<String> healthBox = new JComboBox<>(healthStatuses);
        healthField.setText("100");

        String[] hungerLevels = {"0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};
        JComboBox<String> hungerBox = new JComboBox<>(hungerLevels);
        hungerField.setText("0");

        JButton browseButton = new JButton("Browse...");
        JLabel imagePreview = new JLabel();
        imagePreview.setPreferredSize(new Dimension(60, 60));
        imagePreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        browseButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Select Animal Image");
            chooser.setFileFilter(new FileNameExtensionFilter(
                    "Image files", "jpg", "jpeg", "png", "gif"
            ));
            chooser.setCurrentDirectory(new File("assets/images/"));

            if (chooser.showOpenDialog(formDialog) == JFileChooser.APPROVE_OPTION) {
                String selectedPath = chooser.getSelectedFile().getAbsolutePath();
                imagePathField.setText(selectedPath);
            }
        });

        formDialog.add(new JLabel("Category:"));
        formDialog.add(categoryBox);
        formDialog.add(new JLabel("Name:"));
        formDialog.add(nameField);
        formDialog.add(new JLabel("Age:"));
        formDialog.add(ageField);
        formDialog.add(new JLabel("Hunger:"));
        formDialog.add(hungerBox);
        formDialog.add(new JLabel("Mood:"));
        formDialog.add(moodBox);
        formDialog.add(new JLabel("Health:"));
        formDialog.add(healthBox);
        formDialog.add(new JLabel("Type:"));
        formDialog.add(typeField);
        formDialog.add(new JLabel("Species:"));
        formDialog.add(speciesField);
        formDialog.add(new JLabel("Image:"));
        formDialog.add(browseButton);


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
