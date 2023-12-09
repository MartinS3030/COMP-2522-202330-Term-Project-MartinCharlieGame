package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * A modal for when the player to buy the boat.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class BoatModal implements ModalPopUp{
    private Stage primaryStage;
    private final Player player = Player.getInstance("Charlie");
    private final Popup popup = new Popup();

    /**
     * Creates a new BoatModal.
     */
    public BoatModal() {
    }

    /**
     * Opens the modal.
     *
     * @param stage the stage
     */
    public void openInGamePopup(final Stage stage) {
        this.primaryStage = stage;
        displayUI();
    }

    /**
     * Displays the UI.
     */
    private void displayUI() {

        VBox vbox = getMainVBox(popup);

        StackPane stackPane = createStackPane();
        stackPane.getChildren().add(vbox);

        popup.getContent().clear();
        popup.getContent().add(stackPane);

        popup.setAutoFix(true);

        primaryStage.getScene().widthProperty().addListener((obs, oldVal, newVal) -> centerPopup(primaryStage, popup));
        primaryStage.getScene().heightProperty().addListener((obs, oldVal, newVal) -> centerPopup(primaryStage, popup));

        popup.show(primaryStage);
    }

    /**
     * Centers the popUp.
     *
     * @param stage the stage
     * @param popUp the popUp
     */
    private void centerPopup(final Stage stage, final Popup popUp) {
        double centerX = stage.getX() + stage.getWidth() / 2 - popUp.getWidth() / 2;
        double centerY = stage.getY() + stage.getHeight() / 2 - popUp.getHeight() / 2;

        popUp.setX(centerX);
        popUp.setY(centerY);
    }

    /**
     * Gets the main VBox.
     *
     * @param popUp        the popUp
     * @return the main VBox
     */
    private VBox getMainVBox(final Popup popUp) {
        // Create VBox
        VBox vbox = new VBox(10); // Set spacing between children
        vbox.setStyle("-fx-padding: 8px;");

        HBox labelHBox = getLabelHBox();

        vbox.getChildren().add(labelHBox);

        HBox buyBoat = new HBox();
        if (player.getMoney() < GameDriver.getMoneyGoal()) {
            Label notEnoughMoney = new Label("You don't have enough money!");
            notEnoughMoney.setStyle("-fx-font-family: 'Montserrat';-fx-font-size: 20px;-fx-font-weight: 700;"
                    + "-fx-padding: 5px;-fx-text-fill: rgb(29, 41, 41);");
            buyBoat.getChildren().add(notEnoughMoney);
        } else {
            buyBoat = getButtonHBox(() -> {
                player.buyBoat();
                popup.hide();
                GameDriver gameDriver = new GameDriver();
                gameDriver.endGame(primaryStage);
            }, "Buy Boat");
        }
        buyBoat.setAlignment(Pos.CENTER);

        HBox closeHBox = getButtonHBox(() -> {
            System.out.println("Closing the popup");
            popup.hide();
        }, "Close");
        closeHBox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(buyBoat, closeHBox);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    /**
     * Gets the button HBox.
     *
     * @param action the action to be executed
     * @return the button HBox
     */
    private HBox getButtonHBox(final Runnable action, final String buttonText) {
        Button button = new Button(buttonText);
        button.setOnMouseClicked(e -> {
            action.run();
        });
        button.setStyle("-fx-background-color: rgb(28, 55, 201);"
                + "-fx-font-family: 'Montserrat';-fx-font-size: 20px;-fx-font-weight: 700;"
                + "-fx-padding: 10px;-fx-text-fill: rgb(29, 41, 41);-fx-border-width: 2px"
                + ";-fx-border-color: black;");

        HBox buttonHBox = new HBox(button);
        buttonHBox.setAlignment(Pos.CENTER);

        return buttonHBox;
    }


    /**
     * Gets the label HBox.
     *
     * @return the label HBox
     */
    private HBox getLabelHBox() {
        Label label = new Label("Buy Boat");
        label.setStyle("-fx-font-family: 'Oswald';-fx-font-size: 40px;"
                + "-fx-font-weight: 900;-fx-font-style: italic;"
                + "-fx-text-fill: rgb(29, 41, 41);");
        HBox labelHBox = new HBox(label);
        labelHBox.setMaxWidth(384);
        labelHBox.setPrefHeight(100);
        labelHBox.setStyle(
                "-fx-background-color: rgb(28, 55, 201); -fx-padding: 20px;"
        );
        labelHBox.setAlignment(Pos.CENTER);
        return labelHBox;
    }

    /**
     * Creates a StackPane.
     *
     * @return the StackPane
     */
    private StackPane createStackPane() {
        VBox backgroundCard = new VBox();
        backgroundCard.setStyle(
                "-fx-background-color: rgb(183, 233, 255); -fx-border-color: black; -fx-border-width: 8px;"
                        + "-fx-padding: 20px;"
        );

        StackPane stackPane = new StackPane(backgroundCard);
        stackPane.setPrefWidth(400);

        return stackPane;
    }

    /**
     * Gets an ImageView.
     *
     * @param filepath the filepath as a String
     * @param height   the height as an int
     * @param width    the width as an int
     * @return the ImageView
     */
    private ImageView getImageView(final String filepath, final int height, final int width) {
        Image image = new Image(filepath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }
}
