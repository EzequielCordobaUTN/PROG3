import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Usuario {
    private int id;
    private String nombre;
    private String email;
}
