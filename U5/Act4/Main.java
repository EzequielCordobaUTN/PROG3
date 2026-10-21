import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Producto> inventario = new ArrayList<>();

        Producto[] productos = {
            new Producto("P001", "Laptop", 1200.00),
            new Producto("P002", "Mouse", 25.50),
            new Producto("P001", "Laptop", 1200.00),
            new Producto("P003", "Teclado", 45.00),
            new Producto("P002", "Mouse", 25.50)
        };

        for (Producto producto : productos) {
            if (!inventario.contains(producto)) {
                inventario.add(producto);
            }
        }

        System.out.println("Cantidad de productos sin duplicados: " + inventario.size());
        for (Producto producto : inventario) {
            System.out.println(producto.getCodigo() + " - " + producto.getNombre());
        }
    }
}
