public class Main {

    public static void main(String[] args) {
        Usuario u1 = Usuario.builder()
                .id(1)
                .nombre("Ana Lopez")
                .email("ana.lopez@mail.com")
                .build();

        Usuario u2 = Usuario.builder()
                .id(2)
                .nombre("Luis Garcia")
                .email("luis.garcia@mail.com")
                .build();

        System.out.println("Usuario 1: " + u1);
        System.out.println("Usuario 2: " + u2);
    }
}
