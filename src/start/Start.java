package start;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Start {

    @FXML
    private Label labelExample;
    @FXML
    private Button hashButton;
    @FXML
    private Button binButton;

    public static ArrayList<String> stringArrayList = new ArrayList<>();

    @FXML
    void importFile(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar archivo de texto plano");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("txt", "*.txt")
        );

        File file = fileChooser.showOpenDialog(new Stage());

        labelExample.setVisible(true);
        binButton.setDisable(false);
        hashButton.setDisable(false);

        separator(file);
    }

    void separator(File file) throws IOException {
        String s1;
        String s2;

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((s1 = bufferedReader.readLine()) != null) {
            StringTokenizer st = new StringTokenizer (s1);

            while (st.hasMoreTokens()) {
                s2 = st.nextToken();
                s2 = s2.replaceAll("[^a-zA-ZÀ-ÿ\\u00f1\\u00d1]", "");
                stringArrayList.add(s2);
            }
        }
    }
}
