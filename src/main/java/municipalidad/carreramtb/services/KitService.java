package municipalidad.carreramtb.services;

import municipalidad.carreramtb.entidades.Competidor;
import municipalidad.carreramtb.entidades.Kit;
import municipalidad.carreramtb.repository.CompetidorRepository;
import municipalidad.carreramtb.repository.KitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KitService {
    @Autowired
    CompetidorRepository competidorRepository;

    @Autowired
    KitRepository kitRepository;

    public Kit crearKit(String identificadorKit , Long idCompetidor) throws Exception {
        Optional<Competidor> respuesta = competidorRepository.findById(idCompetidor);

        if (respuesta.isPresent()){
            Kit kit = new Kit();
            kit.setNombre(identificadorKit);
            kit.setRetiro(false);
            kit.setCompetidor(respuesta.get());
            kitRepository.save(kit);
            return kit;
        }else{
            throw new Exception("Competidor Invalido");
        }

    }

    public List<Kit> getAll(){
        return kitRepository.findAll();
    }
}
