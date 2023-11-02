module ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame to javafx.fxml;
    exports ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;
}