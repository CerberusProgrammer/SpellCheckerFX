package sources;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Reader {

    public static ArrayList<String> dictionary = new ArrayList<>();
    public static ArrayList<Integer> hashCodes = new ArrayList<>();

    private static final String dirFile = "src/sources/text/listado-general.txt";

    public static void importWords() throws IOException {
        String s1;
        String s2;

        File file = new File(dirFile);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((s1 = bufferedReader.readLine()) != null) {
            StringTokenizer st = new StringTokenizer (s1);

            while (st.hasMoreTokens()) {
                s2 = st.nextToken();
                s2 = s2.trim().toLowerCase();
                dictionary.add(s2);
            }
        }

        bufferedReader.close();
    }

    public static void exportWords () throws IOException {
        Collections.sort(dictionary);

        File file = new File(dirFile);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (String string: dictionary)
            if (!string.isEmpty())
                bufferedWriter.write(string + "\n");

        bufferedWriter.close();
    }

    public static void toHashCode() {
        for (String string : dictionary) {
            hashCodes.add(string.hashCode());
        }
    }
}
