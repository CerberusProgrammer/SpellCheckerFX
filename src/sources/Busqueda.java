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

    public static int busquedaBinaria(String[] A, String s) {

        int izquierda = 0;
        int derecha = A.length - 1;

        while (izquierda <= derecha) {
            int indiceDelElementoDelMedio = (int) Math.floor((izquierda + derecha)/2);
            String elementoDelMedio = A[indiceDelElementoDelMedio];

            int resultadoDeLaComparacion = s.compareTo(elementoDelMedio);

            if (resultadoDeLaComparacion == 0) {
                return indiceDelElementoDelMedio;
            }

            if (resultadoDeLaComparacion < 0) {
                derecha = indiceDelElementoDelMedio - 1;
            } else {
                izquierda = indiceDelElementoDelMedio + 1;
            }
        }
        return -1;
    }

    public static boolean pruebaLineal(int[] A, int x) {
        for (int i = 0; i < A.length; i++) {
            if (x == A[i])
                return true;
        }

        return false;
    }
}
