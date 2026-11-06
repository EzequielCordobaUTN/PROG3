public class Main {

    public static void main(String[] args) {
        Persona personaLlena = new Persona("Juan Perez", 25);
        System.out.println("Persona (constructor lleno):");
        System.out.println("  nombre = " + personaLlena.getNombre());
        System.out.println("  edad   = " + personaLlena.getEdad());

        Persona personaVacia = new Persona();
        personaVacia.setNombre("Maria Gomez");
        personaVacia.setEdad(30);
        System.out.println("Persona (constructor vacio + setters):");
        System.out.println("  nombre = " + personaVacia.getNombre());
        System.out.println("  edad   = " + personaVacia.getEdad());
    }
}
