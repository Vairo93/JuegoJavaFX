package sample;

//import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML Pane juegoPane;
    @FXML JFXRadioButton facil, medio, dificil;

    ControllerJuego controller;
    public String opcion;

    public Controller() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML public void onClickBtnEmpezar(ActionEvent actionEvent){
        String btn = ((JFXButton)actionEvent.getSource()).getId();

        if (facil.isSelected()){
            opcion = "facil";
        }
        else if (medio.isSelected()){
            opcion = "medio";
        }
        else if (dificil.isSelected()){
            opcion = "dificil";
        }
        else{
            opcion = "facil";
        }

        System.out.println(btn);
        if(btn.equals("btnEmpezar")){
            //GridPane pane = null;
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("empezarJuego.fxml"));
                Parent juegoEscena = loader.load();
                Scene scene = new Scene(juegoEscena);
                Stage stage = (Stage) ((JFXButton) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                controller = loader.getController();
                controller.setScene(scene);
                controller.setOpcion(opcion);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{
            juegoPane.getChildren().clear();
        }
    }
}
