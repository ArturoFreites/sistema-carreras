package municipalidad.carreramtb.repository;

import municipalidad.carreramtb.entidades.Kit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitRepository extends JpaRepository< Kit,Long> {

}
