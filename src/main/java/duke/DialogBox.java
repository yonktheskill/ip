package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Custom dialog box component for displaying chat messages.
 * Represents a dialog box consisting of an ImageView to represent the speaker's face 
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Creates a dialog box with the specified text and image.
     *
     * @param text The text to display in the dialog box
     * @param img The image to display as the speaker's avatar
     */
    public DialogBox(String text, Image img) {
        this.text = new Label(text);
        this.displayPicture = new ImageView(img);

        // Style the text label 
        this.text.setWrapText(true);
        this.text.getStyleClass().add("dialog-text");

        // Style the image with circular clipping
        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);
        Circle clip = new Circle(25, 25, 25);
        displayPicture.setClip(clip);

        // Configure the HBox
        this.setAlignment(Pos.TOP_RIGHT);
        this.setSpacing(10);
        this.getStyleClass().add("dialog-box");
        this.getChildren().addAll(this.text, displayPicture);
    }

    /**
     * Creates a user dialog box.
     *
     * @param text The user's message
     * @param img The user's avatar image
     * @return A DialogBox formatted for user messages
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.getStyleClass().add("user-dialog");
        return db;
    }

    /**
     * Creates an Evan dialog box.
     *
     * @param text Evan's response
     * @param img Evan's avatar image
     * @return A DialogBox formatted for Evan's messages
     */
    public static DialogBox getEvanDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.getStyleClass().add("evan-dialog");
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
