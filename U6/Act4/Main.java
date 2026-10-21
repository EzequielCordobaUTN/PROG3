import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<String, Curso> cursos = new HashMap<>();

        cursos.put("C001", new Curso("Programación III", "Ing. Gómez"));
        cursos.put("C002", new Curso("Estructuras de Datos", "Lic. Pérez"));
        cursos.put("C003", new Curso("Bases de Datos", "Ing. Rodríguez"));

        Curso buscado = cursos.get("C002");
        System.out.println("Curso C002: " + buscado);

        for (Map.Entry<String, Curso> entrada : cursos.entrySet()) {
            System.out.println(entrada.getKey() + " -> " + entrada.getValue());
        }
    }
}
