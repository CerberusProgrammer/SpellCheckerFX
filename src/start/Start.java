package start;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Start {

    @FXML
    private Label labelExample;
    @FXML
    private Button hashButton;
    @FXML
    private Button binButton;
    @FXML
    private TextArea textArea;

    public static ArrayList<String> stylizedText = new ArrayList<>();
    public static ArrayList<String> completeText = new ArrayList<>();

    public static boolean selection;
    public static File fileText;

    @FXML
    void importFile(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar archivo de texto plano");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("txt", "*.txt")
        );

        fileText = fileChooser.showOpenDialog(new Stage());

        labelExample.setVisible(true);
        binButton.setDisable(false);
        hashButton.setDisable(false);

        separator(fileText);
        displayPalabras();
    }

    void displayPalabras() {
        textArea.setDisable(false);
        int totalPalabras = 0;

        for (String string: stylizedText) {
            totalPalabras++;
            textArea.appendText(totalPalabras + ": " + string + "\n");
        }

        textArea.insertText(0, "Total de palabras encontradas: " + totalPalabras + "\n\n");
    }

    void separator(File file) throws IOException {
        String s1;
        String s2;

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((s1 = bufferedReader.readLine()) != null) {
            StringTokenizer st = new StringTokenizer (s1);

            while (st.hasMoreTokens()) {
                s2 = st.nextToken().toLowerCase();
                completeText.add(s2);

                s2 = s2.replaceAll("[^a-zA-ZÀ-ÿ\\u00f1\\u00d1]", "");

                if (!s2.isEmpty())
                    stylizedText.add(s2);
            }
        }
    }

    @FXML
    void selectBinary(ActionEvent event) {
        selection = true;
        openApp(event);
    }

    @FXML
    void selectHash(ActionEvent event) {
        selection = false;
        openApp(event);
    }

    void openApp(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../App.fxml"));
            Parent parent = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
