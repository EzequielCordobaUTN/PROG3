import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Laptop", "Electronica", 1200.0, 5));
        productos.add(new Producto("Mouse", "Electronica", 25.5, 20));
        productos.add(new Producto("Teclado", "Electronica", 45.0, 15));
        productos.add(new Producto("Silla", "Muebles", 150.0, 8));
        productos.add(new Producto("Mesa", "Muebles", 300.0, 3));
        productos.add(new Producto("Lapiz", "Papeleria", 1.5, 100));

        System.out.println("1. Productos con precio > 100, ordenados por precio descendente:");
        productos.stream()
                .filter(p -> p.getPrecio() > 100)
                .sorted((a, b) -> Double.compare(b.getPrecio(), a.getPrecio()))
                .forEach(System.out::println);

        System.out.println("2. Stock total por categoria:");
        Map<String, Integer> stockPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.summingInt(Producto::getStock)));
        stockPorCategoria.forEach((cat, stock) -> System.out.println("  " + cat + ": " + stock));

        System.out.println("3. String con nombre y precio separados por ';':");
        String unidos = productos.stream()
                .map(p -> p.getNombre() + " " + p.getPrecio())
                .collect(Collectors.joining(";"));
        System.out.println(unidos);

        System.out.println("4. Precio promedio general:");
        double promedioGeneral = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0.0);
        System.out.println(promedioGeneral);

        System.out.println("4b. Precio promedio por categoria:");
        Map<String, Double> promedioPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)));
        promedioPorCategoria.forEach((cat, prom) -> System.out.println("  " + cat + ": " + prom));
    }
}
