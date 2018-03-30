package org.name.lasertriangulation.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MenuBarController {

    Stage stage;
    private final FileChooser fileChooser = new FileChooser();

    @FXML private MenuBar menuBar;
    @FXML private MenuItem openItem;
    @FXML private MenuItem saveItem;
    @FXML private MenuItem exitItem;

    private static String OS = System.getProperty("os.name").toLowerCase();
    private static final KeyCombination.Modifier modifier;

    static {
        if (isMac()) {
            modifier = KeyCombination.META_DOWN;
        } else {
            // Should cover Windows, Linux
            modifier = KeyCombination.CONTROL_DOWN;
        }
    }

//    public MenuBarController(Stage stage) {
//        this.stage = stage;
//    }

    public MenuBar getMenuBar() {

        if (menuBar == null) {
            final URL fxmlURL = MenuBarController.class.getResource("/view/fxml/MenuBar.fxml");
            final FXMLLoader loader = new FXMLLoader();

            loader.setController(this);
            loader.setLocation(fxmlURL);
            try {
                loader.load();
            } catch (RuntimeException | IOException x) {
                System.out.println("loader.getController()=" + loader.getController());
                System.out.println("loader.getLocation()=" + loader.getLocation());
                throw new RuntimeException("Failed to load " + fxmlURL.getFile(), x);
            }
        }

        openItem.setOnAction(event -> {
            fileChooser.setTitle("Открыть файл");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Только формат .sdat", "*.sdat");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(menuBar.getParent().getScene().getWindow());
            if (file != null) {
                // TODO: открытие файла через commutatorPanel
                System.out.println("Процесс открытия файла");
            }
        });

        saveItem.setOnAction(event -> {
            fileChooser.setTitle("Сохранить файл");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Только формат .txt", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(menuBar.getParent().getScene().getWindow());
            if (file != null) {
                // TODO: срхранение txt через commutatorPanel
                System.out.println("Процесс сохранения файла");
            }
        });

        exitItem.setOnAction(event -> stage.close());

        /* HotKeys */
        openItem.setAccelerator(new KeyCodeCombination(KeyCode.O, modifier));
        saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, modifier));
        exitItem.setAccelerator(new KeyCodeCombination(KeyCode.W, modifier));

        menuBar.setUseSystemMenuBar(true);
        return menuBar;
    }

    private static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }
}
