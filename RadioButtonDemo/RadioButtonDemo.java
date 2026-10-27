
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class RadioButtonDemo extends JFrame {

    private JRadioButton birdButton;
    private JRadioButton catButton;
    private JRadioButton dogButton;
    private JRadioButton rabbitButton;
    private JRadioButton pigButton;

    private JLabel petImageLabel;
    private JLabel selectedPetLabel;

    public RadioButtonDemo() {

        setTitle("RadioButtonDemo");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(20, 10));
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // Heading
        JLabel heading = new JLabel(
            "Choose Your Favourite Pet",
            SwingConstants.CENTER
        );
        heading.setFont(new Font("Arial", Font.BOLD, 22));

        mainPanel.add(heading, BorderLayout.NORTH);

        // Radio button panel
        JPanel radioPanel = new JPanel(new GridLayout(5, 1, 5, 5));

        birdButton = new JRadioButton("Bird");
        catButton = new JRadioButton("Cat");
        dogButton = new JRadioButton("Dog");
        rabbitButton = new JRadioButton("Rabbit");
        pigButton = new JRadioButton("Pig");

        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        birdButton.setFont(buttonFont);
        catButton.setFont(buttonFont);
        dogButton.setFont(buttonFont);
        rabbitButton.setFont(buttonFont);
        pigButton.setFont(buttonFont);

        // Group the radio buttons
        ButtonGroup petGroup = new ButtonGroup();

        petGroup.add(birdButton);
        petGroup.add(catButton);
        petGroup.add(dogButton);
        petGroup.add(rabbitButton);
        petGroup.add(pigButton);

        radioPanel.add(birdButton);
        radioPanel.add(catButton);
        radioPanel.add(dogButton);
        radioPanel.add(rabbitButton);
        radioPanel.add(pigButton);

        mainPanel.add(radioPanel, BorderLayout.WEST);

        // Pet display panel
        JPanel displayPanel = new JPanel(new BorderLayout(5, 10));

        displayPanel.setBorder(
            BorderFactory.createTitledBorder("Pet Picture")
        );

        petImageLabel = new JLabel("", SwingConstants.CENTER);
        petImageLabel.setFont(
            new Font("Segoe UI Emoji", Font.PLAIN, 100)
        );

        selectedPetLabel = new JLabel(
            "Select a pet",
            SwingConstants.CENTER
        );

        selectedPetLabel.setFont(
            new Font("Arial", Font.BOLD, 20)
        );

        displayPanel.add(petImageLabel, BorderLayout.CENTER);
        displayPanel.add(selectedPetLabel, BorderLayout.SOUTH);

        mainPanel.add(displayPanel, BorderLayout.CENTER);

        // Radio button actions
        birdButton.addActionListener(
            e -> showPet("Bird", "\uD83D\uDC26")
        );

        catButton.addActionListener(
            e -> showPet("Cat", "\uD83D\uDC31")
        );

        dogButton.addActionListener(
            e -> showPet("Dog", "\uD83D\uDC36")
        );

        rabbitButton.addActionListener(
            e -> showPet("Rabbit", "\uD83D\uDC30")
        );

        pigButton.addActionListener(
            e -> showPet("Pig", "\uD83D\uDC37")
        );

        add(mainPanel);

        // Default selection
        pigButton.setSelected(true);
        showPet("Pig", "\uD83D\uDC37");
    }

    private void showPet(String petName, String petEmoji) {

        // Find the image in the images folder
        String fileName = petName.toLowerCase() + ".png";

        File imageFile = new File(
            "images" + File.separator + fileName
        );

        if (imageFile.exists()) {

            ImageIcon originalIcon =
                new ImageIcon(imageFile.getAbsolutePath());

            Image scaledImage = originalIcon.getImage()
                .getScaledInstance(
                    280,
                    220,
                    Image.SCALE_SMOOTH
                );

            petImageLabel.setIcon(
                new ImageIcon(scaledImage)
            );

            petImageLabel.setText("");

        } else {

            // Display emoji if the image is missing
            petImageLabel.setIcon(null);
            petImageLabel.setText(petEmoji);

            System.out.println(
                "Image not found: " +
                imageFile.getAbsolutePath()
            );
        }

        // Update selected pet name
        selectedPetLabel.setText(
            "Selected Pet: " + petName
        );

        // Show selection message
        JOptionPane.showMessageDialog(
            this,
            "You selected: " + petName,
            "Pet Selection",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            RadioButtonDemo app = new RadioButtonDemo();
            app.setVisible(true);
        });
    }
}
