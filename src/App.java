import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sources.Busqueda;
import start.Start;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

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

            button.setOnAction(event -> {
                System.out.println(string);

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("minimenu/MiniMenu.fxml"));
                    Parent parent = fxmlLoader.load();

                    Point point = MouseInfo.getPointerInfo().getLocation();

                    Stage stage = new Stage();
                    stage.setX(point.getX());
                    stage.setY(point.getY());
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.UNDECORATED);

                    stage.setScene(new Scene(parent));
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            flowPane.getChildren().add(button);
        }
    }
}
