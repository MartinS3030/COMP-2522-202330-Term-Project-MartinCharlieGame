package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RodDisplay {
    Player player = Player.getInstance("Charlie");
    Fishing_Rod rod = player.getRod();
    private final Stage primaryStage;
    public RodDisplay(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public HBox getRodDisplayHBox() {
        StackPane baseBox = createComponentBox("Base");
        StackPane rodBox = createComponentBox("Rod");
        StackPane reelBox = createComponentBox("Reel");
        StackPane lineBox = createComponentBox("Line");
        StackPane hookBox = createComponentBox("Hook");

        baseBox.setOnMouseClicked(event -> {
            ModalPopUp baseModal = new DiceFaceModal(0);
            baseModal.openInGamePopup(primaryStage);
        });
        rodBox.setOnMouseClicked(event -> {
            ModalPopUp rodModal = new DiceFaceModal(1);
            rodModal.openInGamePopup(primaryStage);
        });
        reelBox.setOnMouseClicked(event -> {
            ModalPopUp reelModal = new DiceFaceModal(2);
            reelModal.openInGamePopup(primaryStage);
        });
        lineBox.setOnMouseClicked(event -> {
            ModalPopUp lineModal = new DiceFaceModal(3);
            lineModal.openInGamePopup(primaryStage);
        });
        hookBox.setOnMouseClicked(event -> {
            ModalPopUp hookModal = new DiceFaceModal(4);
            hookModal.openInGamePopup(primaryStage);
        });

        VBox baseVBox = createVBox(baseBox);
        VBox rodVBox = createVBox(rodBox);
        VBox reelVBox = createVBox(reelBox);
        VBox lineVBox = createVBox(lineBox);
        VBox hookVBox = createVBox(hookBox);

        HBox mainBox = new HBox(baseVBox, rodVBox, reelVBox, lineVBox, hookVBox);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setSpacing(10);
        mainBox.setPadding(new Insets(10));

        return mainBox;
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

    private Label createParagraph(String description) {
        Label paragraphLabel = new Label(description);
        paragraphLabel.setWrapText(true);
        paragraphLabel.setStyle("-fx-font-size: 20px");
        paragraphLabel.setMaxWidth(170);
        return paragraphLabel;
    }

    private VBox createVBox(Pane component) {
        return new VBox(component);
    }
}
