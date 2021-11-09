import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sources.Reader;
import start.Start;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.geometry.Pos.TOP_LEFT;

public class App implements Initializable {

    @FXML
    private FlowPane flowPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Reader.importWords();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String string : Start.completeText) {
            Button button = new Button(string);

            if (Reader.diccionario.contains(string))
                button.setStyle("-fx-background-color: white;");
            else
                button.getStylesheets().add("style.css");

            button.setOnAction(this::displayMiniMenu);

            flowPane.getChildren().add(button);
        }
    }

    void displayMiniMenu(ActionEvent event) {
        Point point = MouseInfo.getPointerInfo().getLocation();

        Stage stage = new Stage();
        stage.setHeight(111);
        stage.setWidth(197);

        stage.setX(point.getX());
        stage.setY(point.getY());
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: white;");
        anchorPane.setStyle("-fx-background-radius: 10;");
        anchorPane.setPrefHeight(111);
        anchorPane.setPrefWidth(197);

        Button addDictionary = new Button("Agregar al diccionario");
        Button ignoreWord = new Button("Ignorar palabra");
        Button changeAll = new Button("Cambiar todos");

        addDictionary.setAlignment(TOP_LEFT);
        addDictionary.getStylesheets().add("minimenu.css");
        addDictionary.setPrefSize(anchorPane.getPrefWidth(), anchorPane.getPrefHeight() / 3);
        addDictionary.setOnAction(event1 -> {
            Reader.diccionario.add(((Button) event.getSource()).getText());
            ((Button) event.getSource()).setStyle("-fx-background-color: white;");

            try {
                Reader.exportWords();
            } catch (IOException e) {
                e.printStackTrace();
            }

            stage.close();
        });

        ignoreWord.setAlignment(TOP_LEFT);
        ignoreWord.getStylesheets().add("minimenu.css");
        ignoreWord.setPrefSize(anchorPane.getPrefWidth(), anchorPane.getPrefHeight() / 3);
        ignoreWord.setOnAction(event1 -> {
            ((Button)event.getSource()).setStyle("-fx-background-color: white;");

            stage.close();
        });

        changeAll.setAlignment(TOP_LEFT);
        changeAll.getStylesheets().add("minimenu.css");
        changeAll.setPrefSize(anchorPane.getPrefWidth(), anchorPane.getPrefHeight() / 3);
        changeAll.setOnAction(event1 -> {
            for (String string: Start.completeText) {
                if (string.equals(((Button)event.getSource()).getText())) {
                    System.out.println("ok");
                }
            }

            stage.close();
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(addDictionary, ignoreWord, changeAll);

        anchorPane.getChildren().add(vBox);
        Scene scene = new Scene(anchorPane);

        stage.setScene(scene);
        stage.show();
    }
}
