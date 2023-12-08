package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Random;

/**
 * Displays the bulletin board.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class BoardDisplay extends Application {
    /**
     * The width of the stage.
     */
    public static final int STAGE_WIDTH = 1200;
    /**
     * The height of the stage.
     */
    public static final int STAGE_HEIGHT = 648;
    /**
     * The size of the back button.
     */
    public static final int BACK_BUTTON_SIZE = 80;
    private Stage primaryStage;
    private final BulletinBoard bulletinBoard = BulletinBoard.getInstance();
    private final ArrayList<Quest> questList = bulletinBoard.getQuests();
    private Player player = Player.getInstance("Charlie");
    private ArrayList<Integer> daysPassed = new ArrayList<>();
    private int startIndex = 0;

    /**
     * Starts the stage.
     *
     * @param stage a Stage
     */
    @Override
    public void start(final Stage stage) {
        this.primaryStage = stage;
        displayUI();
    }

    /**
     * Generates the bulletin board UI.
     */
    public void displayUI() {
        addQuests();
        StackPane questStack;
        if (!questList.isEmpty()) {
            questStack = generateQuestDetails(questList.get(0));
        } else {
            questStack = generateQuestDetails(new Quest("No Quests Available", "No One", 0,
                    new Fish("No Fish", "common", "none", 0, 0),
                    0, 0,
                    "There are no quests available at this time. Please check back later."));
        }

        StackPane bulletinBoardStack = generateBoardUI(questStack);
        VBox left = generateLeft(bulletinBoardStack);

        HBox root = new HBox(left, questStack);

        Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
        this.primaryStage.setTitle("Quest Display");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    /**
     * Generates the left side of the bulletin board UI.
     *
     * @param board a StackPane
     * @return a VBox
     */
    private VBox generateLeft(final StackPane board) {
        Image backButton = new Image("file:../../resources/backButton.png");
        ImageView backButtonView = new ImageView(backButton);
        backButtonView.setFitHeight(BACK_BUTTON_SIZE);
        backButtonView.setFitWidth(BACK_BUTTON_SIZE);
        backButtonView.setOnMouseClicked(this::back);

        Button viewQuests = ButtonMaker.createButton("View Quests", this::displayActiveQuests, 0, 0);
        HBox buttons = new HBox(backButtonView, viewQuests);
        buttons.setSpacing(418);

        return new VBox(board, buttons);
    }

    /**
     * Generates the bulletin board UI.
     *
     * @param questStack a StackPane
     * @return a StackPane
     */
    private StackPane generateBoardUI(final StackPane questStack) {
        int endIndex = Math.min(startIndex + 4, questList.size());

        Image bulletinBoardImage = new Image("file:../../resources/BulletinBoard.png");
        ImageView bulletinBoardView = new ImageView(bulletinBoardImage);

        Label requestTitle = new Label("Requests");
        Font font = Font.loadFont("file:resources/Fonts/CinzelDecorative-Bold.ttf", 30);
        requestTitle.setFont(font);
        requestTitle.setTranslateX(200);

        Button nextButton = ButtonMaker.createButton("Next", this::nextQuests, 0, 0);
        Button previousButton = ButtonMaker.createButton("Previous", this::previousQuests, 0, 0);

        nextButton.setPrefHeight(50);
        nextButton.setPrefWidth(100);
        previousButton.setPrefHeight(50);
        previousButton.setPrefWidth(100);

        HBox nextPreviousButtons = new HBox(previousButton, nextButton);

        VBox allQuestView = new VBox(requestTitle);

        for (int i = startIndex; i < endIndex; i++) {
            HBox questDetailsView = boardHBox(questList.get(i), questStack);
            allQuestView.getChildren().add(questDetailsView);
        }

        allQuestView.getChildren().add(nextPreviousButtons);

        allQuestView.setSpacing(0);

        StackPane bulletinBoardStack = new StackPane(bulletinBoardView, allQuestView);
        StackPane.setAlignment(allQuestView, Pos.TOP_CENTER);
        StackPane.setMargin(allQuestView, new Insets(15, 0, 0, 22));
        StackPane.setAlignment(bulletinBoardView, Pos.TOP_CENTER);
        return bulletinBoardStack;
    }

    /**
     * Generates the HBox for the bulletin board UI.
     *
     * @param quest a Quest
     * @param questStack a StackPane
     * @return a HBox
     */
    private HBox boardHBox(final Quest quest, final StackPane questStack) {
        Image fishImage = new Image("file:../../resources/Fish/" + quest.getObjective().getName() + ".png");
        HBox hbox = getHbox(quest, fishImage);
        hbox.setSpacing(30);
        hbox.setMaxWidth(650);
        hbox.setMaxHeight(80);

        hbox.setOnMouseClicked(event -> {
            StackPane updatedQuestStack = generateQuestDetails(quest);
            questStack.getChildren().set(1, updatedQuestStack);
        });

        hbox.setOnMouseEntered(event -> hbox.getScene().setCursor(Cursor.HAND));
        hbox.setOnMouseExited(event -> hbox.getScene().setCursor(Cursor.DEFAULT));

        return hbox;
    }

    /**
     * Generates the HBox for the bulletin board UI.
     *
     * @param quest a Quest
     * @param fishImage an Image
     * @return a HBox
     */
    private static HBox getHbox(final Quest quest, final Image fishImage) {
        ImageView fishImageView = new ImageView(fishImage);

        Label questTitleView = new Label(quest.getTitle());
        questTitleView.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-alignment: center");
        questTitleView.setMaxHeight(80);
        Label questGiverView = new Label("Requester: " + quest.getGiver());
        questGiverView.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-alignment: center");
        questGiverView.setMaxHeight(80);
        Label questDifficultyView = new Label("Difficulty: " + quest.getDifficulty());
        questDifficultyView.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-alignment: center");
        questDifficultyView.setMaxHeight(80);

        return new HBox(fishImageView, questTitleView, questGiverView, questDifficultyView);
    }

    /**
     * Generates the label for the bulletin board UI.
     *
     * @param text a String
     * @param height an int
     * @return a Label
     */
    public Label generateLabel(final String text, final int height) {
        Font font = Font.loadFont("file:resources/Fonts/CinzelDecorative-Regular.ttf", 20);
        Label label = new Label(text);
        label.setFont(font);
        label.setPrefHeight(height);
        label.setPrefWidth(430);
        label.setWrapText(true);
        return label;
    }

    /**
     * Generates the quest details for the right side of the UI.
     *
     * @param quest a Quest
     * @return a StackPane
     */
    public StackPane generateQuestDetails(final Quest quest) {
        Label questTitle = generateLabel(quest.getTitle(), 80);
        Label questGiver = generateLabel("Requester: " + quest.getGiver(), 50);
        Label questDifficulty = generateLabel("Difficulty: " + quest.getDifficulty(), 50);
        Label questDescription = generateLabel("Description:\n" + quest.getDescription(), 200);
        Label questReward = generateLabel("Reward: " + quest.getReward(), 100);

        Button acceptButton = new Button("Accept");
        acceptButton.setPrefWidth(200);
        acceptButton.setPrefHeight(80);
        acceptButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-background-color: #74b359;"
                + " -fx-border-width: 5px; -fx-border-style: solid; -fx-border-color: black; -fx-border-radius: 10px");

        acceptButton.setOnAction(event -> acceptQuest(quest));

        VBox questDetails = new VBox(questTitle, questGiver, questDifficulty, questDescription, questReward,
                acceptButton);
        questDetails.setSpacing(0);
        questDetails.setMaxWidth(430);
        questDetails.setMaxHeight(520);

        Image questBox = new Image("file:../../resources/QuestBox.png");
        ImageView questBoxView = new ImageView(questBox);
        questBoxView.setFitHeight(648);

        StackPane questStack = new StackPane();
        StackPane.setAlignment(questDetails, Pos.TOP_CENTER);
        questStack.setMaxWidth(501);
        StackPane.setMargin(questDetails, new Insets(12, 0, 0, 0));

        questStack.getChildren().addAll(questBoxView, questDetails);

        return questStack;
    }

    /**
     * Accepts a quest.
     *
     * @param quest a Quest
     */
    public void acceptQuest(final Quest quest) {
        Player player = Player.getInstance("Charlie");
        try {
            player.addQuests(quest);
            bulletinBoard.removeQuest(quest);
            displayUI();
            displayAlert("Quest Accepted", "Quest successfully accepted!");
        } catch (IllegalArgumentException e) {
            displayAlert("Error", e.getMessage());
        }
        for (Quest activeQuest : player.getQuests()) {
            System.out.println(activeQuest.getTitle());
        }
    }

    /**
     * Displays an alert.
     *
     * @param title the title of the alert as a String
     * @param content the content of the alert as a String
     */
    private void displayAlert(final String title, final String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Displays the active quests.
     *
     * @param event an ActionEvent
     */
    public void displayActiveQuests(final ActionEvent event) {
        ModalPopUp modalPopUp = new ActiveQuestModal();
        modalPopUp.openInGamePopup(primaryStage);
    }

    /**
     * Returns to the village.
     *
     * @param event a MouseEvent
     */
    public void back(final MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500));

        fadeTransition.setNode(((Node) event.getSource()).getScene().getRoot());

        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        fadeTransition.setOnFinished(e -> {
            VillageDisplay villageDisplay = new VillageDisplay();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            villageDisplay.start(currentStage);
        });

        fadeTransition.play();
    }

    /**
     * Shows the next quests.
     *
     * @param event an ActionEvent
     */
    private void nextQuests(final ActionEvent event) {
        if (startIndex + 4 < questList.size()) {
            startIndex = Math.min(startIndex + 4, questList.size());
            displayUI();
        }
    }

    /**
     * Shows the previous quests.
     *
     * @param event an ActionEvent
     */
    private void previousQuests(final ActionEvent event) {
        if (startIndex - 4 >= 0) {
            startIndex = Math.max(startIndex - 4, 0);
            displayUI();
        }
    }

    private void addQuests() {
        Random random = new Random();
        if (player.getDate() > 1 && player.getDate() < 8 && !daysPassed.contains(player.getDate())) {
            for (int i = 0; i < 2; i++) {
                bulletinBoard.addQuest(bulletinBoard.generateCommonQuest());
            }
        } else if (player.getDate() > 7 && player.getDate() < 15 && !daysPassed.contains(player.getDate())) {
            for (int i = 0; i < 2; i++) {
                int questRarity = random.nextInt(2);
                if (questRarity == 0) {
                    bulletinBoard.addQuest(bulletinBoard.generateCommonQuest());
                } else {
                    bulletinBoard.addQuest(bulletinBoard.generateRareQuest());
                }
            }
        } else if (player.getDate() > 14 && !daysPassed.contains(player.getDate())) {
            for (int i = 0; i < 2; i++) {
                int questRarity = random.nextInt(3);
                if (questRarity == 0) {
                    bulletinBoard.addQuest(bulletinBoard.generateCommonQuest());
                } else if (questRarity == 1) {
                    bulletinBoard.addQuest(bulletinBoard.generateRareQuest());
                } else {
//                bulletinBoard.addQuest(bulletinBoard.generateLegendaryQuest());
                }
            }
        }
        daysPassed.add(player.getDate());
    }
}
