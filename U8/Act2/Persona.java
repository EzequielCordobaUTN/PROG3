public class Persona {
    private String nombre;
    private int edad;

    public Persona() {
    }

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    private String saludar() {
        return "Hola";
    }

    public String mostrarInfo() {
        return "Persona{nombre='" + nombre + "', edad=" + edad + "}";
    }

    @Override
    public String toString() {
        return mostrarInfo();
    }
}
