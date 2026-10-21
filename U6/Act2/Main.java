import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Alumno> alumnos = new ArrayList<>();

        alumnos.add(new Alumno("Juan", 8.5));
        alumnos.add(new Alumno("María", 9.0));
        alumnos.add(new Alumno("Pedro", 6.75));
        alumnos.add(new Alumno("Juan", 7.0));

        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }
}
