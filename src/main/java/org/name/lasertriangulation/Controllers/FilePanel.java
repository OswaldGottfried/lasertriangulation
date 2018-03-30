package org.name.lasertriangulation.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.name.lasertriangulation.Data.Data;
import org.name.lasertriangulation.Exceptions.FileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class FilePanel {

    private final FileChooser fileChooser = new FileChooser();
    private ObservableList fileName;
    Data data = null;

    @FXML
    ListView listView;
    @FXML
    Button load;
    @FXML
    Button delete;

    @FXML
    public void onClickAdd() {
        fileChooser.setTitle("Открыть файл");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Только формат .DAT", "*.DAT");
        fileChooser.getExtensionFilters().add(extFilter);
        List<File> files = fileChooser.showOpenMultipleDialog(listView.getParent().getScene().getWindow());
        if (files != null) {
            fileName = FXCollections.observableArrayList(files);
            listView.setItems(fileName);
        }
    }

    @FXML public void onListClicked() throws IOException, FileFormatException {
        delete.setDisable(false);
        String fileName = listView.getSelectionModel().getSelectedItem().toString();
        System.out.println(fileName);
        Data dataFile = data.readData(fileName);
        System.out.println(dataFile.getSurfaceRadius());
    }

    @FXML
    public void onClickDelete() {
        final int selectedId = listView.getSelectionModel().getSelectedIndex();
        if (selectedId != -1) {
            String itemToRemove = listView.getSelectionModel().getSelectedItem().toString();
            final int newSelectedId = (selectedId == listView.getItems().size() - 1) ? selectedId - 1 : selectedId;
            fileName.remove(selectedId);
            listView.getSelectionModel().select(newSelectedId);
            System.out.println("Удален объект: " + itemToRemove);
            System.out.println("с индексом: " + selectedId);
        }
    }
}
