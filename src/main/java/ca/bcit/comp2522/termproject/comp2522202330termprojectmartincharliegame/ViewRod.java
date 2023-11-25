package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class ViewRod extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane baseBox = createComponentBox("Base");
        StackPane rodBox = createComponentBox("Rod");
        StackPane reelBox = createComponentBox("Reel");
        StackPane lineBox = createComponentBox("Line");
        StackPane hookBox = createComponentBox("Hook");

        Label baseText = createParagraph("This base is used to create a great base experience.");
        Label rodText = createParagraph("This rod is used to create a great rod experience.");
        Label reelText = createParagraph("This reel is used to create a great reel experience.");
        Label lineText = createParagraph("This line is used to create a great line experience.");
        Label hookText = createParagraph("This hook is used to create a great hook experience.");

        VBox baseVBox = createVBox(baseBox, baseText);
        VBox rodVBox = createVBox(rodBox, rodText);
        VBox reelVBox = createVBox(reelBox, reelText);
        VBox lineVBox = createVBox(lineBox, lineText);
        VBox hookVBox = createVBox(hookBox, hookText);

        HBox mainBox = new HBox(baseVBox, rodVBox, reelVBox, lineVBox, hookVBox);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setSpacing(10);
        mainBox.setPadding(new Insets(10));

        Region rectangle = new Region();
        rectangle.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        rectangle.setPrefSize(1200, 648);

        StackPane innerStackPane = new StackPane(mainBox);
        innerStackPane.setTranslateY(100);

        StackPane stackPane = new StackPane(rectangle, innerStackPane);

        Scene scene = new Scene(stackPane, 1200, 648);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Fishing Rod Components");

        primaryStage.show();
    }

}
