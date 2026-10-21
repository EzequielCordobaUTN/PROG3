import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set<Producto> productos = new HashSet<>();

        Producto[] agregar = {
            new Producto("P001", "Laptop", 1200.00),
            new Producto("P002", "Mouse", 25.50),
            new Producto("P001", "Laptop", 1200.00),
            new Producto("P003", "Teclado", 45.00),
            new Producto("P002", "Mouse", 25.50)
        };

        for (Producto producto : agregar) {
            productos.add(producto);
        }

        System.out.println("Cantidad de productos sin duplicados: " + productos.size());
        for (Producto producto : productos) {
            System.out.println(producto.getCodigo() + " - " + producto.getNombre());
        }
    }
}
