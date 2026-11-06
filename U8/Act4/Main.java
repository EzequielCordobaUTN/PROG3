import java.util.List;

public class Main {

    public static <T> void imprimirLista(List<T> lista) {
        for (T elemento : lista) {
            System.out.println(elemento);
        }
    }

    public static void main(String[] args) {
        List<Integer> enteros = List.of(1, 2, 3);
        List<String> cadenas = List.of("uno", "dos", "tres");

        System.out.println("Lista de enteros:");
        imprimirLista(enteros);

        System.out.println("Lista de cadenas:");
        imprimirLista(cadenas);
    }
}
