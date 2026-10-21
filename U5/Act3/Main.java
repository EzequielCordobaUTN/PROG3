public class Main {

    public static void main(String[] args) {
        Curso estructuras = new Curso("Estructuras de Datos");
        estructuras.agregarEstudiante(new Estudiante("Juan", 20, "Ingeniería en Sistemas"));
        estructuras.agregarEstudiante(new Estudiante("María", 22, "Diseño Gráfico"));
        estructuras.agregarEstudiante(new Estudiante("Pedro", 21, "Medicina"));

        Curso programacion = new Curso("Programación III");
        programacion.agregarEstudiante(new Estudiante("Lucía", 23, "Ingeniería en Sistemas"));
        programacion.agregarEstudiante(new Estudiante("Tomás", 24, "Analista de Sistemas"));

        System.out.println(estructuras);
        System.out.println();
        System.out.println(programacion);
    }
}
