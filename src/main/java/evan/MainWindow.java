package evan;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.SnapshotParameters;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Evan evan;

    private Image userImage;
    private Image evanImage;

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);
        
        // Load images or create placeholders
        // Try multiple formats for user image
        userImage = loadImageWithFallback(
            new String[]{"/images/user.png", "/images/user.png.webp"}, 
            Color.web("#667eea")
        );
        evanImage = loadImageOrCreatePlaceholder("/images/evan.png", Color.web("#48bb78"));
    }

    /**
     * Tries to load an image from multiple possible paths.
     *
     * @param paths Array of paths to try
     * @param color The color for the placeholder if all fail
     * @return The loaded image or a placeholder
     */
    private Image loadImageWithFallback(String[] paths, Color color) {
        for (String path : paths) {
            try {
                return new Image(this.getClass().getResourceAsStream(path));
            } catch (Exception e) {
                // Try next path
            }
        }
        return createPlaceholder(color);
    }

    /**
     * Loads an image from resources or creates a colored placeholder if not found.
     *
     * @param path The path to the image resource
     * @param color The color for the placeholder
     * @return The loaded image or a placeholder
     */
    private Image loadImageOrCreatePlaceholder(String path, Color color) {
        try {
            return new Image(this.getClass().getResourceAsStream(path));
        } catch (Exception e) {
            return createPlaceholder(color);
        }
    }

    /**
     * Creates a colored circle placeholder image.
     *
     * @param color The color for the placeholder
     * @return A placeholder image
     */
    private Image createPlaceholder(Color color) {
        Canvas canvas = new Canvas(100, 100);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillOval(0, 0, 100, 100);
        
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        return canvas.snapshot(params, null);
    }

    /**
     * Sets the Evan instance for this controller.
     *
     * @param evan The Evan chatbot instance
     */
    public void setEvan(Evan evan) {
        this.evan = evan;
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void displayWelcome() {
        String welcomeMessage = "Hey! I'm Evan\nWhat's up?";
        dialogContainer.getChildren().add(
            DialogBox.getEvanDialog(welcomeMessage, evanImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Evan's reply 
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return;
        }

        String response = evan.getResponse(input);
        
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getEvanDialog(response, evanImage)
        );
        userInput.clear();

        // Check if it's an exit command
        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
