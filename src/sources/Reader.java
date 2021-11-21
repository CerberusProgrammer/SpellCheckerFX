package sources;

import start.Start;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Reader {

    public static ArrayList<String> diccionario = new ArrayList<>();
    public static ArrayList<Integer> hashCodes = new ArrayList<>();

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

    public static void exportWords () throws IOException {
        Collections.sort(diccionario);

        File file = new File("src/sources/Diccionario.txt");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (String string: diccionario) {
            bufferedWriter.write(string + "\n");
        }
    }

    public static void toHashCode() {
        for (String string : diccionario) {
            hashCodes.add(string.hashCode());
        }
    }
}
