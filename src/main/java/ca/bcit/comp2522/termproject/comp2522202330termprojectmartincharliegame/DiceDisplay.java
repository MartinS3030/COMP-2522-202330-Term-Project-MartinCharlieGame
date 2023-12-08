package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DiceDisplay {
    private final Color BORDER_COLOR = Color.BLACK;
    private final Color SELECTED_COLOR = Color.FORESTGREEN;
    private final Color USED_COLOR = Color.GREY;
    private final ImageView[] diceViews;
    private final Fishing_Rod fishingRod;
    private final HBox hBox;
    private final VBox[] vBox;
//    private final Stage primaryStage;
    private final Player player;
    private final ArrayList<Dice> selectedDice;
    private final ArrayList<Dice> usedDice;

    public DiceDisplay() {
        player = Player.getInstance("Charlie");
        fishingRod = generateRod();

        diceViews = new ImageView[fishingRod.getComponents().size()];
        updateDiceView();

        hBox = new HBox();
        vBox = new VBox[]{new VBox(), new VBox(), new VBox(), new VBox(), new VBox(), new VBox()};

        selectedDice = new ArrayList<Dice>();
        usedDice = new ArrayList<Dice>();
    }

    public Color getBORDER_COLOR() {
        return BORDER_COLOR;
    }

    public Color getSELECTED_COLOR() {
        return SELECTED_COLOR;
    }

    public Color getUSED_COLOR() {
        return USED_COLOR;
    }

    public ImageView[] getDiceViews() {
        return diceViews;
    }

    public Fishing_Rod getFishingRod() {
        return fishingRod;
    }

    public HBox gethBox() {
        return hBox;
    }

    public VBox[] getvBox() {
        return vBox;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Dice> getUsedDice() {
        return usedDice;
    }

    public ArrayList<Dice> getSelectedDice() {
        return selectedDice;
    }

    protected Fishing_Rod generateRod() {
        return new Fishing_Rod();
    }

    protected StackPane createRoundedBorderedImageView(ImageView imageView, Color color) {
        // Create a StackPane to hold the ImageView
        StackPane stackPane = new StackPane(imageView);

        Border border = getRoundedBorder(color);

        // Apply the Border to the StackPane
        stackPane.setBorder(border);

        // Set the size of the StackPane to match the size of the image
        stackPane.setMaxSize(imageView.getFitWidth(), imageView.getFitHeight());

        return stackPane;
    }

    private Border getRoundedBorder(Color color) {
        // Define the corner radii for rounded borders
        CornerRadii cornerRadii = new CornerRadii(10); // Adjust the radius as needed
        BorderWidths borderWidth = new BorderWidths(15); // Adjust the width as needed

        // Create a BorderStroke with the desired color, style, width, and rounded corners
        BorderStroke borderStroke = new BorderStroke(color, BorderStrokeStyle.SOLID, cornerRadii, borderWidth);

        // Create a Border with the specified BorderStroke
        return new Border(borderStroke);
    }

    protected void resetBorderColor(ImageView imageView) {
        setBorderColor(imageView, BORDER_COLOR);
    }

    protected void setBorderColor(ImageView imageView, Color color) {
        // Apply the border color to the given ImageView
        Border border = getRoundedBorder(color);
        ((StackPane) imageView.getParent()).setBorder(border);
    }

    protected ImageView getImageViews(Dice dice) {
        return DiceFace.getDiceFaceImage(dice.getFaceUpValue());
    }

    protected int findDiceViewPosition(ImageView targetImageView) {
        for (int i = 0; i < diceViews.length; i++) {
            if (diceViews[i] == targetImageView) {
                return i; // Found the target ImageView, return its index
            }
        }
        return -1; // If the target ImageView is not found in the array
    }

    private void updateDiceView() {
        for (int i = 0; i < fishingRod.getComponents().size(); i++) {
            diceViews[i] = getImageViews(fishingRod.getComponents().get(i));
        }

        for (ImageView diceView : diceViews) {
            diceView.setPreserveRatio(true); // Preserve the aspect ratio of the image
            diceView.setFitWidth(100); // Set the desired width
        }
    }

    protected void selectDice(int diceIndex) {
        System.out.printf("Selecting dice %d\n", diceIndex);
        Dice dice = fishingRod.getComponents().get(diceIndex);
        if (usedDice.contains(dice)) {
            return;
        }
        if (selectedDice.contains(dice)) {
            resetBorderColor(diceViews[diceIndex]);
            selectedDice.remove(dice);
        } else if (!usedDice.contains(dice)){
            for (Dice diceInSelectedDice : selectedDice) {
                resetBorderColor(diceViews[fishingRod.getComponents().indexOf(diceInSelectedDice)]);
            }
            selectedDice.clear();
            selectedDice.add(dice);
            setBorderColor(diceViews[diceIndex], SELECTED_COLOR);
        }
    }

    public HBox getDiceDisplay() {
        if (!hBox.getChildren().isEmpty()) {
            return hBox;
        }
        for (int i = 0; i < diceViews.length; i++) {
            int diceIndex = i;
            StackPane diceBox = createRoundedBorderedImageView(diceViews[i], BORDER_COLOR);
            diceBox.setOnMouseClicked(event -> selectDice(diceIndex));
            vBox[i].getChildren().addAll(diceBox);
            hBox.getChildren().add(vBox[i]);
        }
        hBox.setSpacing(25);
        return hBox;
    }

    public void addDiceToUsedDice(Dice dice) {
        usedDice.add(dice);
        setBorderColor(diceViews[fishingRod.getComponents().indexOf(dice)], USED_COLOR);
    }
}
