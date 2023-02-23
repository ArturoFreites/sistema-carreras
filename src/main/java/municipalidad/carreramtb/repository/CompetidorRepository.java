package municipalidad.carreramtb.repository;

import municipalidad.carreramtb.entidades.Competidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetidorRepository extends JpaRepository<Competidor, Long> {

}
