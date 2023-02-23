package municipalidad.carreramtb.entidades;

import jakarta.persistence.*;
import lombok.Data;
import municipalidad.carreramtb.services.CompetidorService;

@Entity
@Data
public class Kit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nombre;
    @OneToOne
    Competidor competidor;
    boolean retiro;
}
