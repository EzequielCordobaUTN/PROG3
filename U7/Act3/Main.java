import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("P001", "Laptop", 1200.0, "Dell"));
        productos.add(new Producto("P002", "Mouse", 25.5, "Logitech"));
        productos.add(new Producto("P003", "Teclado", 45.0, "Logitech"));

        List<ProductoDTO> dtos = new ArrayList<>();
        for (Producto p : productos) {
            ProductoDTO dto = new ProductoDTO();
            dto.setCodigo(p.getCodigo());
            dto.setNombre(p.getNombre());
            dto.setPrecio(p.getPrecio());
            dtos.add(dto);
        }

        System.out.println("Productos:");
        for (Producto p : productos) {
            System.out.println("  " + p);
        }

        System.out.println("ProductoDTOs:");
        for (ProductoDTO dto : dtos) {
            System.out.println("  " + dto);
        }
    }
}
