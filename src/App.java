import javafx.fxml.Initializable;
import sources.Busqueda;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class App implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < 100; i++)
            arrayList.add(i);

        Collections.shuffle(arrayList);
        int[] ints = new int[100];

        for (int i = 0; i < 100; i++)
            ints[i] = arrayList.get(i);

        int resultado = Busqueda.pruebaLineal(ints, 19);

        if (resultado != -1)
            System.out.println("pos: " + resultado);
        else
            System.out.println("No hay");
    }
}
