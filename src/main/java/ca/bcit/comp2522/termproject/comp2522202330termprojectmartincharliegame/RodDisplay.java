package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Represents the rod display.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class RodDisplay {
    private final Stage primaryStage;

    /**
     * Constructs a new rod display.
     *
     * @param primaryStage the primary stage
     */
    public RodDisplay(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Gets the primary stage.
     *
     * @return the primary stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Gets the rod display HBox.
     *
     * @return the rod display HBox
     */
    public HBox getRodDisplayHBox() {
        StackPane baseBox = createComponentBox("Base");
        StackPane rodBox = createComponentBox("Rod");
        StackPane reelBox = createComponentBox("Reel");
        StackPane lineBox = createComponentBox("Line");
        StackPane hookBox = createComponentBox("Hook");

        StackPane[] componentBoxes = new StackPane[]{baseBox, rodBox, reelBox, lineBox, hookBox};
        HBox mainBox = new HBox();

        for (int i = 0; i < componentBoxes.length; i++) {
            componentBoxes[i].setOnMouseClicked(openModal(i));
            mainBox.getChildren().add(createVBox(componentBoxes[i]));
        }

        mainBox.setAlignment(Pos.CENTER);
        mainBox.setSpacing(10);
        mainBox.setPadding(new Insets(10));

        return mainBox;
    }

    /**
     * Returns an event handler for handling mouse events to open a modal popup.
     *
     * @param DiceIndex The index of the dice associated with the event.
     * @return The event handler for handling mouse events.
     */
    protected EventHandler<MouseEvent> openModal(int DiceIndex) {
        return event -> {
            ModalPopUp modal = new DiceFaceModal(DiceIndex);
            modal.openInGamePopup(primaryStage);
        };
    }

    private StackPane createComponentBox(String componentName) {
        Image image = new Image("file:../../resources/Box.png");
        ImageView imageView = new ImageView(image);
        Text text = new Text(componentName);
        text.setFont(new Font(20));
        text.wrappingWidthProperty().bind(imageView.fitWidthProperty());
        StackPane stackPane = new StackPane(imageView, text);
        StackPane.setAlignment(text, Pos.CENTER);
        return stackPane;
    }

    private VBox createVBox(Pane component) {
        return new VBox(component);
    }
}
