package org.name.lasertriangulation.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.name.lasertriangulation.Data.Data;

import java.io.IOException;
import org.name.lasertriangulation.Exceptions.*;

public class СircularСhartController {

    @FXML private Label LSurfaceRadius;                     //радиус фотоприемника LFP
    @FXML private Label LEmitterYOffset;                    //смещение осветителя Y0
    @FXML private Label LInitialEmitterHeightPosition;      //Zmin осветителя
    @FXML private Label LEmitterHeightStep;                 //шаг осветителя
    @FXML private Label LDiscretePoints;                    //кол-во точек на сечение NFI
    @FXML private Label LRadialProfilesNumber;              //кол-во сечений
    @FXML private RadioButton grannost;                     //переключатель режима гранность
    @FXML private RadioButton waviness;                     //переключатель режима волнистость
    @FXML private ComboBox radialProfilesNumber;            //список для выбора номера сечения
    @FXML private TextArea deflection;                      //область для вывода значений отклонений
    @FXML private Slider slider;                            //устанавливает масштаб
    @FXML private Button drawButton;

//    private DrawKruglogramPanel drawKruglogramPanel;  //панель отображает круглограмму
//    private RawData rawData=null;
//    private Data data=null;
    private boolean fWav;
    private int minBorder;
    private int maxBorder;
    private Data data = null;

    public void getData(String filename) throws IOException, FileFormatException {
        grannost.setSelected(true);
//        data = ReaderData.readData(filename);
        LSurfaceRadius.setText("" + data.getSurfaceRadius());
        LEmitterYOffset.setText("" + data.getEmitterYOffset());
    }
}
