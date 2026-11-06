import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno("Ana", 8.5, "Java"));
        alumnos.add(new Alumno("Luis", 6.0, "Java"));
        alumnos.add(new Alumno("Marta", 9.0, "Python"));
        alumnos.add(new Alumno("Carlos", 7.5, "Python"));
        alumnos.add(new Alumno("Sofia", 5.0, "Java"));
        alumnos.add(new Alumno("Diego", 8.0, "Python"));

        System.out.println("1. Nombres aprobados (nota >= 7) en mayusculas y ordenados:");
        List<String> aprobados = alumnos.stream()
                .filter(a -> a.getNota() >= 7)
                .map(a -> a.getNombre().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
        System.out.println(aprobados);

        System.out.println("2. Promedio general de notas:");
        double promedioGeneral = alumnos.stream()
                .mapToDouble(Alumno::getNota)
                .average()
                .orElse(0.0);
        System.out.println(promedioGeneral);

        System.out.println("3. Alumnos agrupados por curso:");
        Map<String, List<Alumno>> porCurso = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));
        porCurso.forEach((curso, lista) -> {
            System.out.println("  " + curso + ": " + lista);
        });

        System.out.println("4. Los 3 mejores promedios (alumnos con mejor nota):");
        List<Alumno> top3 = alumnos.stream()
                .sorted(Comparator.comparingDouble(Alumno::getNota).reversed())
                .limit(3)
                .collect(Collectors.toList());
        top3.forEach(a -> System.out.println("  " + a));
    }
}
