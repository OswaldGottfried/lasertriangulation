package org.name.lasertriangulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.name.lasertriangulation.Controllers.MenuBarController;

import javax.swing.*;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        stage.setTitle("Лазерная триангуляция");

        final Group rootGroup = new Group();
        final Scene scene = new Scene(rootGroup);
        final Parent mainScene = FXMLLoader.load(getClass().getResource("/view/fxml/Main.fxml"));
        final MenuBar menuBar = new MenuBarController().getMenuBar();

        rootGroup.getChildren().add(menuBar);
        rootGroup.getChildren().add(mainScene);
        stage.setScene(scene);
        stage.show();


//        String appIcon = "/view/images/logo.png";
//        stage.getIcons().add(new Image(appIcon));
//        com.apple.eawt.Application macApp = com.apple.eawt.Application.getApplication();
//        macApp.setDockIconImage(new ImageIcon(getClass().getResource(appIcon)).getImage ());
    }
}