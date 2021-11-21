import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sources.Busqueda;
import sources.Reader;
import start.Start;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.geometry.Pos.CENTER_LEFT;

public class App implements Initializable {

    @FXML
    private FlowPane flowPane;
    @FXML
    private Label labelInformation;

    private int agregadoDiccionario = 0;
    private int omision = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Reader.importWords();
            Reader.toHashCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Start.selection) {
            String[] strings = new String[Reader.diccionario.size()];

            for (int i = 0; i < Reader.diccionario.size(); i++) {
                strings[i] = Reader.diccionario.get(i);
            }

            for (String string : Start.completeText) {
                Button button = new Button(string);

                System.out.println(Busqueda.busquedaBinaria(strings, string));

                int pos = Busqueda.busquedaBinaria(strings, string);

                if (pos >= 0) {
                    button.setStyle("-fx-background-color: white;");
                } else {
                    button.getStylesheets().add("style.css");
                    button.setOnAction(this::displayMiniMenu);
                }

                flowPane.getChildren().add(button);
            }
        } else {
            int[] ints = new int[Reader.hashCodes.size() - 1];

            for (int j = 0; j < Reader.hashCodes.size() - 1; j++) {
                ints[j] = Reader.hashCodes.get(j);
            }

            for (String string : Start.completeText) {
                Button button = new Button(string);
                int i = string.hashCode();

                if (Busqueda.pruebaLineal(ints, i))
                    button.setStyle("-fx-background-color: white;");
                else {
                    button.getStylesheets().add("style.css");
                    button.setOnAction(this::displayMiniMenu);
                }

                flowPane.getChildren().add(button);
            }
        }
        displayInformation();
    }

    void displayInformation() {
        labelInformation.setText("Total de palabras: " + Start.completeText.size() + ", " +
                "Palabras erroneas: " + Start.stylizedText.size() + ", " +
                "Agregadas: " + agregadoDiccionario + ", " +
                "Omitidas: " + omision + ".");
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
        anchorPane.setStyle("-fx-background-radius: 20;");
        anchorPane.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0,0,0);");
        anchorPane.setPrefHeight(111);
        anchorPane.setPrefWidth(197);

        Button addDictionary = new Button("Agregar al diccionario");
        Button ignoreWord = new Button("Ignorar palabra");
        Button changeAll = new Button("Cambiar todos");

        addDictionary.setAlignment(CENTER_LEFT);
        addDictionary.getStylesheets().add("minimenu.css");
        addDictionary.setPrefSize(anchorPane.getPrefWidth(), anchorPane.getPrefHeight() / 3);
        addDictionary.setOnAction(event1 -> {
            Reader.diccionario.add(((Button) event.getSource()).getText());
            ((Button) event.getSource()).setStyle("-fx-background-color: white;");
            agregadoDiccionario++;
            displayInformation();
            stage.close();
        });

        ignoreWord.setAlignment(CENTER_LEFT);
        ignoreWord.getStylesheets().add("minimenu.css");
        ignoreWord.setPrefSize(anchorPane.getPrefWidth(), anchorPane.getPrefHeight() / 3);
        ignoreWord.setOnAction(event1 -> {
            ((Button) event.getSource()).setStyle("-fx-background-color: white;");
            omision++;
            displayInformation();
            stage.close();
        });

        changeAll.setAlignment(CENTER_LEFT);
        changeAll.getStylesheets().add("minimenu.css");
        changeAll.setPrefSize(anchorPane.getPrefWidth(), anchorPane.getPrefHeight() / 3);
        changeAll.setOnAction(event1 -> {
            for (String string : Start.completeText) {
                if (string.equals(((Button) event.getSource()).getText())) {
                    System.out.println("ok");
                }
            }

            displayInformation();
            stage.close();
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(addDictionary, ignoreWord, changeAll);

        anchorPane.getChildren().add(vBox);
        Scene scene = new Scene(anchorPane);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void saveFile(ActionEvent event) {
        try {
            Reader.exportWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void saveAsFile(ActionEvent event) {
        try {
            String ruta = "example.txt";
            File file = new File(ruta);

            if (!file.exists())
                file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Node node : flowPane.getChildren()) {
                Button button = ((Button) node);
                bufferedWriter.write(button.getText() + " ");
            }

            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }
}
