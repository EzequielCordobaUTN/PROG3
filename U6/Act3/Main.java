import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set<Producto> productos = new HashSet<>();

        productos.add(new Producto("P001", "Laptop"));
        productos.add(new Producto("P002", "Mouse"));
        productos.add(new Producto("P001", "Laptop"));
        productos.add(new Producto("P003", "Teclado"));
        productos.add(new Producto("P002", "Mouse"));

        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }
}
