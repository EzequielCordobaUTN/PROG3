import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("Persona");

        System.out.println("Constructores:");
        for (Constructor<?> c : clazz.getDeclaredConstructors()) {
            System.out.println("  " + c);
        }

        Constructor<?> ctor = clazz.getDeclaredConstructor(String.class, int.class);
        Object persona = ctor.newInstance("Juan", 25);
        System.out.println("Instancia creada por reflection: " + persona);

        Field nombreField = clazz.getDeclaredField("nombre");
        nombreField.setAccessible(true);
        nombreField.set(persona, "Carlos");
        System.out.println("Despues de modificar nombre: " + persona);

        Method mostrarInfo = clazz.getDeclaredMethod("mostrarInfo");
        mostrarInfo.setAccessible(true);
        Object info = mostrarInfo.invoke(persona);
        System.out.println("Info via reflection: " + info);

        Method saludar = clazz.getDeclaredMethod("saludar");
        saludar.setAccessible(true);
        Object saludo = saludar.invoke(persona);
        System.out.println("Saludo (metodo privado) invocado: " + saludo);
    }
}
