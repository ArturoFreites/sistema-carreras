package municipalidad.carreramtb.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor

public class Competidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nombre;
    String apellido;
    Integer dni;
    String telefono;
    Integer anioNacimiento;
    String localidad;
    @OneToOne
    Genero genero;
    @OneToOne
    Competicion competicion;
    String categoria;
    String team;
    @OneToOne
    Kit kit;
    @Temporal(TemporalType.DATE)
    Date fechaInscripcion;

    public Competidor() {

    }
}
