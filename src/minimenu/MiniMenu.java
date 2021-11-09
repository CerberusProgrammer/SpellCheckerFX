package minimenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class MiniMenu {

    @FXML
    void agregarAlDiccionario(ActionEvent event) {

        close(event);
    }

    @FXML
    void ignorarPalabra(ActionEvent event) {
        close(event);
    }

    @FXML
    void cambiarTodos(ActionEvent event) {
        close(event);
    }

    void close(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
