package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * A class that displays the dice.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class DiceDisplay {
    private final Color BORDER_COLOR = Color.BLACK;
    private final Color SELECTED_COLOR = Color.FORESTGREEN;
    private final Color USED_COLOR = Color.GREY;
    private final ImageView[] diceViews;
    private final FishingRod fishingRod;
    private final HBox hBox;
    private final VBox[] vBox;
    private final Player player;
    private final ArrayList<Dice> selectedDice;
    private final ArrayList<Dice> usedDice;

    /**
     * Creates a new DiceDisplay.
     */
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

    /**
     * Gets the border color.
     *
     * @return the border color
     */
    public Color getBORDER_COLOR() {
        return BORDER_COLOR;
    }

    /**
     * Gets the selected color.
     *
     * @return the selected color
     */
    public Color getSELECTED_COLOR() {
        return SELECTED_COLOR;
    }

    /**
     * Gets the dice views.
     *
     * @return the dice views
     */
    public ImageView[] getDiceViews() {
        return diceViews;
    }

    /**
     * Gets the fishing rod.
     *
     * @return the fishing rod
     */
    public FishingRod getFishingRod() {
        return fishingRod;
    }

    /**
     * Gets the HBox.
     *
     * @return the HBox
     */
    public HBox gethBox() {
        return hBox;
    }

    /**
     * Gets the VBox.
     *
     * @return the VBox
     */
    public VBox[] getvBox() {
        return vBox;
    }

    /**
     * Gets the player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the selected dice.
     *
     * @return the selected dice
     */
    public ArrayList<Dice> getUsedDice() {
        return usedDice;
    }

    /**
     * Gets the selected dice.
     *
     * @return the selected dice
     */
    public ArrayList<Dice> getSelectedDice() {
        return selectedDice;
    }

    /**
     * Gets the dice display.
     *
     * @return the dice display
     */
    protected FishingRod generateRod() {
        return new FishingRod();
    }

    /**
     * Creates a rounded bordered ImageView.
     *
     * @param imageView the ImageView to create
     * @param color     the color of the border
     * @return the StackPane
     */
    protected StackPane createRoundedBorderedImageView(final ImageView imageView, final Color color) {
        StackPane stackPane = new StackPane(imageView);

        Border border = getRoundedBorder(color);
        stackPane.setBorder(border);
        stackPane.setMaxSize(imageView.getFitWidth(), imageView.getFitHeight());

        return stackPane;
    }

    /**
     * Gets the rounded border.
     *
     * @param color the color
     * @return the rounded border
     */
    private Border getRoundedBorder(final Color color) {
        CornerRadii cornerRadii = new CornerRadii(10);
        BorderWidths borderWidth = new BorderWidths(15);
        BorderStroke borderStroke = new BorderStroke(color, BorderStrokeStyle.SOLID, cornerRadii, borderWidth);
        return new Border(borderStroke);
    }

    /**
     * Resets the border color.
     *
     * @param imageView the imageView
     */
    protected void resetBorderColor(final ImageView imageView) {
        setBorderColor(imageView, BORDER_COLOR);
    }

    /**
     * Sets the border color.
     *
     * @param imageView the imageView
     * @param color     the color
     */
    protected void setBorderColor(final ImageView imageView, final Color color) {
        Border border = getRoundedBorder(color);
        ((StackPane) imageView.getParent()).setBorder(border);
    }

    /**
     * Gets the image views.
     *
     * @param dice the dice
     * @return the image views
     */
    protected ImageView getImageViews(final Dice dice) {
        return DiceFace.getDiceFaceImage(dice.getFaceUpValue());
    }

    /**
     * Finds the dice view position.
     *
     * @param targetImageView the target image view
     * @return the dice view position
     */
    protected int findDiceViewPosition(final ImageView targetImageView) {
        for (int i = 0; i < diceViews.length; i++) {
            if (diceViews[i] == targetImageView) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Updates the dice view.
     */
    private void updateDiceView() {
        for (int i = 0; i < fishingRod.getComponents().size(); i++) {
            diceViews[i] = getImageViews(fishingRod.getComponents().get(i));
        }

        for (ImageView diceView : diceViews) {
            diceView.setPreserveRatio(true);
            diceView.setFitWidth(100);
        }
    }

    /**
     * Selects the dice.
     *
     * @param diceIndex the dice index
     */
    protected void selectDice(final int diceIndex) {
        System.out.printf("Selecting dice %d\n", diceIndex);
        Dice dice = fishingRod.getComponents().get(diceIndex);
        if (usedDice.contains(dice)) {
            return;
        }
        if (selectedDice.contains(dice)) {
            resetBorderColor(diceViews[diceIndex]);
            selectedDice.remove(dice);
        } else if (!usedDice.contains(dice)) {
            for (Dice diceInSelectedDice : selectedDice) {
                resetBorderColor(diceViews[fishingRod.getComponents().indexOf(diceInSelectedDice)]);
            }
            selectedDice.clear();
            selectedDice.add(dice);
            setBorderColor(diceViews[diceIndex], SELECTED_COLOR);
        }
    }

    /**
     * Gets the dice display.
     *
     * @return the dice display
     */
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

    /**
     * Adds the dice to used dice.
     *
     * @param dice the dice to add
     */
    public void addDiceToUsedDice(final Dice dice) {
        usedDice.add(dice);
        setBorderColor(diceViews[fishingRod.getComponents().indexOf(dice)], USED_COLOR);
    }
}
