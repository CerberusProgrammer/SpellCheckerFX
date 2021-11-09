package sources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Reader {

    public static ArrayList<String> diccionario = new ArrayList<>();

    public static void importWords() throws IOException {
        String s1;
        String s2;

        File file = new File("src/sources/Diccionario.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((s1 = bufferedReader.readLine()) != null) {
            StringTokenizer st = new StringTokenizer (s1);

            while (st.hasMoreTokens()) {
                s2 = st.nextToken();
                s2 = s2.trim();
                diccionario.add(s2);
            }
        }
    }
}
