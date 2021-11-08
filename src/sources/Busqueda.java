package sources;

public class Busqueda {

    public static int buscar(int[] arreglo, int dato) {
        int inicio = 0;
        int fin = arreglo.length - 1;
        int pos;
        while (inicio <= fin) {
            pos = (inicio + fin) / 2;
            if (arreglo[pos] == dato)
                return pos;
            else if (arreglo[pos] < dato) {
                inicio = pos + 1;
            } else {
                fin = pos - 1;
            }
        }
        return -1;
    }

    public static int pruebaLineal(int[] A, int x) {
        int m = A.length;
        int dirHash = x % m;

        if (A[dirHash] == x)
            return dirHash;
        else {
            int dirReh = (dirHash + 1) % m;

            while ((A[dirReh] != x) && (A[dirReh] != 0) && (dirReh != dirHash))
                dirReh = (dirReh + 1) % m;

            if (A[dirReh] == x)
                return dirReh;
            else
                return -1;
        }
    }
}
