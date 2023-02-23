package municipalidad.carreramtb.services;

import jakarta.transaction.Transactional;
import municipalidad.carreramtb.entidades.Competicion;
import municipalidad.carreramtb.repository.CompeticionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompeticionService {

    @Autowired
    CompeticionRepository competicionRepository;

    @Transactional
    public void crearCompeticion(String tipo) throws Exception {
        validarDatos(tipo);
        Competicion competicion = new Competicion();
        competicion.setTipo(tipo);
        competicionRepository.save(competicion);

    }
    public List<Competicion> getAll(){
        return competicionRepository.findAll();
    }
    private void validarDatos(String tipo) throws Exception {
        if (tipo.isEmpty() || tipo==null){
            throw new Exception("genero invalido");
        }
    }
}
