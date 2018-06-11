package fr.antoineaube.chameleon.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class GUILauncher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL mainViewUrl = getClass().getClassLoader().getResource("fxml/MainView.fxml");

        assert mainViewUrl != null;

        Parent root = FXMLLoader.load(mainViewUrl);
        Scene scene = new Scene(root, 750, 300);

        primaryStage.setTitle("Chameleon");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}