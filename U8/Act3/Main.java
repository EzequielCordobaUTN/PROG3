import java.util.ArrayList;
import java.util.List;

public class Main {

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void main(String[] args) {
        Caja<String> cajaString = new Caja<>();
        cajaString.guardar("Hola");
        System.out.println("Caja<String>: " + cajaString.obtener());

        Caja<Integer> cajaInt = new Caja<>();
        cajaInt.guardar(42);
        System.out.println("Caja<Integer>: " + cajaInt.obtener());

        List lista = new ArrayList();
        lista.add("texto");
        lista.add(123);
        System.out.println("Lista sin genericos: " + lista);
        String s = (String) lista.get(0);
        System.out.println("Elemento 0 (cast): " + s);
    }
}
