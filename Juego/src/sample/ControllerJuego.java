package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerJuego implements Initializable {

    @FXML Pane juegoPane;

    private Node persona;
    private List<Node> coches = new ArrayList<>();
    private AnimationTimer timer;
    private Scene scene;

    private Controller controller;
    private String opcion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    createContent();
    }

    private Parent createContent(){

        persona = initPersona();
        juegoPane.getChildren().add(persona);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();


        return juegoPane;
    }

    private Node initPersona() {
        Rectangle rect = new Rectangle(38, 38, Color.GREEN);
        rect.setTranslateY(600 - 39);

        return rect;
    }

    private Node spawnCoches() {
        Rectangle rect = new Rectangle(40, 40, Color.RED);
        rect.setTranslateY((int)(Math.random() * 14) * 40);

        juegoPane.getChildren().add(rect);
        return rect;
    }

    private void onUpdate() {
       if (opcion.equals("facil")) {
           for (Node car : coches)
               car.setTranslateX(car.getTranslateX() + Math.random() * 10);

           if (Math.random() < 0.075) {
               coches.add(spawnCoches());
           }
       }
        else if (opcion.equals("medio")) {
            for (Node car : coches)
                car.setTranslateX(car.getTranslateX() + Math.random() * 20);

            if (Math.random() < 0.095) {
                coches.add(spawnCoches());
            }
        }
        else if (opcion.equals("dificil")) {
           for (Node car : coches)
               car.setTranslateX(car.getTranslateX() + Math.random() * 30);

           if (Math.random() < 0.15) {
               coches.add(spawnCoches());
           }
       }

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    persona.setTranslateY(persona.getTranslateY() - 40);
                    break;
                case S:
                    persona.setTranslateY(persona.getTranslateY() + 40);
                    break;
                case A:
                    persona.setTranslateX(persona.getTranslateX() - 40);
                    break;
                case D:
                    persona.setTranslateX(persona.getTranslateX() + 40);
                    break;
                default:
                    break;
            }
        });

        checkState();
    }

    private void checkState() {
        for (Node coche : coches) {
            if (coche.getBoundsInParent().intersects(persona.getBoundsInParent())) {
                timer.stop();
                String win = "YOU LOSE";

                HBox hBox = new HBox();
                hBox.setTranslateX(300);
                hBox.setTranslateY(250);
                juegoPane.getChildren().add(hBox);

                for (int i = 0; i < win.toCharArray().length; i++) {
                    char letter = win.charAt(i);

                    Text text = new Text(String.valueOf(letter));
                    text.setFont(Font.font(48));
                    text.setOpacity(0);

                    hBox.getChildren().add(text);

                    FadeTransition ft = new FadeTransition(Duration.seconds(0.66), text);
                    ft.setToValue(1);
                    ft.setDelay(Duration.seconds(i * 0.15));
                    ft.play();
                    scene.setOnKeyPressed(event -> {
                        switch (event.getCode()) {
                            case W:
                                persona.setTranslateY(persona.getTranslateY());
                                break;
                            case S:
                                persona.setTranslateY(persona.getTranslateY());
                                break;
                            case A:
                                persona.setTranslateX(persona.getTranslateX());
                                break;
                            case D:
                                persona.setTranslateX(persona.getTranslateX());
                                break;
                            default:
                                break;
                        }
                    });
                }
                return;
            }
        }

        if (persona.getTranslateY() <= 0) {
            timer.stop();
            String win = "YOU WIN";

            HBox hBox = new HBox();
            hBox.setTranslateX(300);
            hBox.setTranslateY(250);
            juegoPane.getChildren().add(hBox);

            for (int i = 0; i < win.toCharArray().length; i++) {
                char letter = win.charAt(i);

                Text text = new Text(String.valueOf(letter));
                text.setFont(Font.font(48));
                text.setOpacity(0);

                hBox.getChildren().add(text);

                FadeTransition ft = new FadeTransition(Duration.seconds(0.66), text);
                ft.setToValue(1);
                ft.setDelay(Duration.seconds(i * 0.15));
                ft.play();
                scene.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case W:
                            persona.setTranslateY(persona.getTranslateY());
                            break;
                        case S:
                            persona.setTranslateY(persona.getTranslateY());
                            break;
                        case A:
                            persona.setTranslateX(persona.getTranslateX());
                            break;
                        case D:
                            persona.setTranslateX(persona.getTranslateX());
                            break;
                        default:
                            break;
                    }
                });
            }
        }
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }
}
