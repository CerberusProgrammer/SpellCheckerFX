package start;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Start {

    @FXML
    private Label labelExample;
    @FXML
    private Button hashButton;
    @FXML
    private Button binButton;
    @FXML
    private Button importButton;

    @FXML
    void importFile(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar archivo de texto plano");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("txt", "*.txt")
        );

        File file = fileChooser.showOpenDialog(new Stage());

        String string;
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((string = bufferedReader.readLine()) != null)
            System.out.println(string);

        bufferedReader.close();

        labelExample.setVisible(true);
        binButton.setDisable(false);
        hashButton.setDisable(false);
    }
}
