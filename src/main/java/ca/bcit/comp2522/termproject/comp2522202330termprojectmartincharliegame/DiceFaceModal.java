package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Represents the dice face modal.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class DiceFaceModal implements ModalPopUp {
    private static final ArrayList<DiceFaceModal> openedModal = new ArrayList<>();
    private final int diceIndex;

    /**
     * Constructs a DiceFaceModal.
     *
     * @param diceIndex the dice index
     */
    public DiceFaceModal(final int diceIndex) {
        this.diceIndex = diceIndex;
    }

    /**
     * Opens the dice face modal.
     *
     * @param primaryStage the primary stage
     */
    public void openInGamePopup(final Stage primaryStage) {
        if (openedModal.isEmpty()) {
            openedModal.add(this);
        } else {
            return;
        }
        Player player = Player.getInstance("Charlie");
        FishingRod fishingRod = player.getRod();

        Popup popup = new Popup();

        VBox vbox = getMainVBox(fishingRod, popup);

        StackPane stackPane = createStackPane();
        stackPane.getChildren().add(vbox);

        popup.getContent().add(stackPane);

        popup.setAutoFix(true);

        primaryStage.getScene().widthProperty().addListener((obs, oldVal, newVal) -> centerPopup(primaryStage, popup));
        primaryStage.getScene().heightProperty().addListener((obs, oldVal, newVal) -> centerPopup(primaryStage, popup));
        popup.show(primaryStage);
    }

    /**
     * Creates a StackPane.
     *
     * @return a StackPane
     */
    private StackPane createStackPane() {
        VBox backgroundCard = new VBox();
        backgroundCard.setStyle(
                "-fx-background-color: rgb(183, 233, 255);"
                        + "-fx-border-color: black;"
                        + "-fx-border-width: 8px;"
                        + "-fx-padding: 20px;"
        );

        StackPane stackPane = new StackPane(backgroundCard);
        stackPane.setPrefWidth(250 + 8 * 2);

        return stackPane;
    }

    private void centerPopup(final Stage primaryStage, final Popup popup) {
        double centerX = primaryStage.getX() + primaryStage.getWidth() / 2 - popup.getWidth() / 2;
        double centerY = primaryStage.getY() + primaryStage.getHeight() / 2 - popup.getHeight() / 2;

        popup.setX(centerX);
        popup.setY(centerY);
    }

    private VBox getMainVBox(final FishingRod fishingRod, final Popup popup) {
        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 8px;");

        HBox labelHBox = getLabelHBox();

        vbox.getChildren().add(labelHBox);

        ArrayList<ImageView> diceViews = createDiceFaceViews(fishingRod.getComponents().get(diceIndex), popup);
        VBox diceFaceVBox = createTVbox(diceViews);

        diceFaceVBox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(diceFaceVBox);

        HBox closeHBox = getButtonHBox(popup);

        vbox.getChildren().addAll(closeHBox);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    private HBox getButtonHBox(final Popup popup) {
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            popup.hide();
            openedModal.clear();
        });
        closeButton.setStyle("-fx-background-color: rgb(28, 55, 201);"
                + "-fx-font-family: 'Montserrat';-fx-font-size: 20px;-fx-font-weight: 700;"
                + "-fx-padding: 5px;-fx-text-fill: rgb(29, 41, 41);-fx-border-width: 2px"
                + ";-fx-border-color: black;");
        HBox closeHBox = new HBox(closeButton);
        closeHBox.setAlignment(Pos.CENTER);
        closeHBox.setStyle("-fx-padding: 10px;");
        return closeHBox;
    }

    private HBox getLabelHBox() {
        Label label = new Label("Dice Face");
        label.setStyle("-fx-font-family: 'Oswald';-fx-font-size: 40px;"
                + "-fx-font-weight: 900;-fx-font-style: italic;"
                + "-fx-text-fill: rgb(29, 41, 41);");
        HBox labelHBox = new HBox(label);
        labelHBox.setMaxWidth(250);
        labelHBox.setPrefHeight(75);
        labelHBox.setStyle(
                "-fx-background-color: rgb(28, 55, 201);"
                + "-fx-padding: 20px;"
        );
        return labelHBox;
    }

    /**
     * Returns the dice face views.
     *
     * @param dice the dice
     * @param popup the popup
     * @return images of the dice faces as an ArrayList of ImageView
     */
    protected ArrayList<ImageView> createDiceFaceViews(final Dice dice, final Popup popup) {
        ArrayList<ImageView> diceViews = new ArrayList<>();
        for (Integer face : dice.getFaceList()) {
            ImageView diceView = DiceFace.getDiceFaceImage(face);
            diceView.setFitWidth(50);
            diceView.setFitHeight(50);

            Rectangle clip = new Rectangle(diceView.getFitWidth(), diceView.getFitHeight());
            clip.setArcWidth(5);
            clip.setArcHeight(5);

            // Apply the clip to the ImageView
            diceView.setClip(clip);
            diceViews.add(diceView);
        }

        return diceViews;
    }

    /**
     * Creates a VBox of the dice faces in a T configuration.
     *
     * @param diceViews the dice face views
     * @return a VBox of the dice faces
     */
    private VBox createTVbox(final ArrayList<ImageView> diceViews) {
        VBox diceFaceVBox = new VBox();
        for (int i = 0; i < diceViews.size(); i++) {
            if (i == 0) {
                HBox firstRow = new HBox();
                while (i < 3) {
                    firstRow.getChildren().add(diceViews.get(i));
                    i++;
                }
                firstRow.setAlignment(Pos.CENTER);
                diceFaceVBox.getChildren().add(firstRow);
            }
            HBox subsequentRow = new HBox(diceViews.get(i));
            subsequentRow.setAlignment(Pos.CENTER);
            diceFaceVBox.getChildren().add(subsequentRow);
        }
        return diceFaceVBox;
    }

    /**
     * Clears the opened modal.
     */
    public static void clearOpenedModal() {
        openedModal.clear();
    }
}
