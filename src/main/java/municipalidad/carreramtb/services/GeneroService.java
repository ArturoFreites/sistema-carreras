package municipalidad.carreramtb.services;

import jakarta.transaction.Transactional;
import municipalidad.carreramtb.entidades.Genero;
import municipalidad.carreramtb.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {

    @Autowired
    GeneroRepository generoRepository;

    @Transactional
    public void crearGenero(String tipo) throws Exception {
        validarDatos(tipo);
        Genero genero = new Genero();
        genero.setTipo(tipo);
        generoRepository.save(genero);
    }

    public List<Genero> getAll(){
        return generoRepository.findAll();
    }
    private void validarDatos(String tipo) throws Exception {
        if (tipo.isEmpty() || tipo==null){
            throw new Exception("genero invalido");
        }
    }
}
