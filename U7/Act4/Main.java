import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("P001", "Laptop", "Dell", 1200.0));
        productos.add(new Producto("P002", "Mouse", "Logitech", 25.5));
        productos.add(new Producto("P003", "Teclado", "Logitech", 45.0));

        List<ProductoRecord> records = new ArrayList<>();

        records.add(new ProductoRecord("R001", "Teclado Mecanico", 80.0));
        records.add(new ProductoRecord("R002", "Monitor", 300.0));

        for (Producto p : productos) {
            records.add(new ProductoRecord(p.getCodigo(), p.getNombre(), p.getPrecio()));
        }

        System.out.println("Productos:");
        for (Producto p : productos) {
            System.out.println("  " + p);
        }

        System.out.println("ProductoRecords:");
        for (ProductoRecord r : records) {
            System.out.println("  " + r);
        }
    }
}
