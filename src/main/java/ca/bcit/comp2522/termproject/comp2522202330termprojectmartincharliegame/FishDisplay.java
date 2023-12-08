package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.FadeTransition;

/**
 * Displays the fish catching screen.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class FishDisplay extends Application {
    /**
     * The width of the stage.
     */
    public static final int STAGE_WIDTH = 1200;
    /**
     * The height of the stage.
     */
    public static final int FISH_IMAGE_SIZE = 100;
    public static final int FISH_TO_CATCH = 5;
    public static final int STAGE_HEIGHT = 648;
    private final ArrayList<Fish> fishList = new ArrayList<>();
    private DiceDisplay diceDisplay;
    private final Random random = new Random();

    /**
     * Starts the fish display.
     *
     * @param primaryStage the stage
     */
    @Override
    public void start(final Stage primaryStage) {
        Image oceanImage = new Image("file:../../resources/Ocean.png");
        ImageView oceanImageView = new ImageView(oceanImage);

        generateFish();
//        for (Fish fish : fishList) {
//            System.out.println(fish.getName());
//        }

        HBox fishContainer = new HBox();
        fishContainer.setSpacing(70);
        fishContainer.setAlignment(Pos.CENTER);

        for (Fish fish : fishList) {
            VBox fishVBox = createFishVBox(fish);
            fishContainer.getChildren().add(fishVBox);
        }

        diceDisplay = new FishingDiceDisplay(primaryStage);
        HBox diceDisplayHBox = diceDisplay.getDiceDisplay();
        diceDisplayHBox.setAlignment(Pos.CENTER);

        StackPane root = new StackPane();
        root.getChildren().addAll(oceanImageView, fishContainer, diceDisplayHBox);
        StackPane.setMargin(fishContainer, new Insets(-300, 0, 0, 0));
        StackPane.setMargin(diceDisplayHBox, new Insets(520, 0, 200, 0));

        Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);

        oceanImageView.setPreserveRatio(true);
        oceanImageView.fitHeightProperty().bind(scene.heightProperty());
        primaryStage.setTitle("Fish Display");

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), root);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Creates a Vbox for each fish.
     *
     * @param fish the fish
     * @return the Vbox displaying the fish ad its requirements
     */
    private VBox createFishVBox(final Fish fish) {
        Image fishImage = new Image("file:../../resources/Fish/" + fish.getName() + ".png");
        ImageView fishImageView = new ImageView(fishImage);
        fishImageView.setFitWidth(FISH_IMAGE_SIZE);
        fishImageView.setFitHeight(FISH_IMAGE_SIZE);
        StackPane fishStackPane = new StackPane(fishImageView);

        Label nameLabel = new Label(fish.getName());
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px");

        Label requirementLabel = new Label(generateRequirement(fish.getRequirementType(), fish.getRequirementValue()));

        VBox fishVBox = new VBox(nameLabel, fishStackPane, requirementLabel);
        fishImageView.setOnMouseClicked(event -> selectedFish(fish, fishStackPane));
        fishVBox.setSpacing(5);
        fishVBox.setAlignment(javafx.geometry.Pos.CENTER);

        return fishVBox;
    }

    /**
     * Selects the fish.
     *
     * @param fish the fish
     * @param fishStackPane the stackpane containing the fish
     */
    private void selectedFish(final Fish fish, final StackPane fishStackPane) {
        ArrayList<Dice> diceList = diceDisplay.getSelectedDice();

        if (CheckRequirements.checkAgainstFish(diceList, fish)) {
            System.out.println("You caught a " + fish.getName());
            Image fishImage = new Image("file:../../resources/Fish/" + fish.getName() + ".png");
            ImageView fishImageView = new ImageView(fishImage);
            fishImageView.setFitWidth(FISH_IMAGE_SIZE);
            fishImageView.setFitHeight(FISH_IMAGE_SIZE);

            Text mainText = new Text("CAUGHT!");
            mainText.setFont(Font.font("Oswald", FontWeight.BOLD, 24));
            mainText.setFill(Color.rgb(231, 54, 70));
            mainText.setEffect(new DropShadow(15, Color.BLACK));

            Player player = Player.getInstance("Charlie");
            player.addInventory(fish);

            fishStackPane.getChildren().clear();
            fishStackPane.getChildren().addAll(fishImageView, mainText);

            for (Dice dice : diceList) {
                diceDisplay.addDiceToUsedDice(dice);
            }
            diceList.clear();
        } else {
            System.out.println("You did not catch a " + fish.getName());
        }
    }

    /**
     * Generates a label for the fish requirements.
     *
     * @param requirement the requirement as a string
     * @param value the value of the requirement as an int
     * @return the label for the fish requirements as a string
     */
    public String generateRequirement(final String requirement, final int value) {
        return switch (requirement) {
            case "greater" -> "Greater than " + value;
            case "less" -> "Less than " + value;
            case "equalTo" -> "Equal to " + value;
            case "ofakind" -> value + " of a kind";
            case "straight" -> "Straight of " + value;
            case "fullHouse" -> "Full house";
            case "even" -> value + "Even";
            case "odd" -> value + "Odd";
            default -> "Error";
        };
    }

    /**
     * Generates 5 fish.
     */
    public void generateFish() {
        int fishKey;
        int count = 0;
        while (count < FISH_TO_CATCH) {
            int rarity = random.nextInt(100) + 1;
            if (rarity <= 20) {
                fishKey = random.nextInt(13) + 1;
            } else if (rarity <= 25) {
                fishKey = random.nextInt(8) + 14;
            } else {
                fishKey = random.nextInt(5) + 22;
            }
            count++;
            FishSpecies fishSpecies = new FishSpecies();
            Fish generatedFish = fishSpecies.getFish(fishKey);
            fishList.add(generatedFish);
        }
    }
}
