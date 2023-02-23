package municipalidad.carreramtb.services;

import jakarta.transaction.Transactional;
import municipalidad.carreramtb.entidades.Competicion;
import municipalidad.carreramtb.entidades.Competidor;
import municipalidad.carreramtb.entidades.Genero;
import municipalidad.carreramtb.entidades.Kit;
import municipalidad.carreramtb.repository.CompeticionRepository;
import municipalidad.carreramtb.repository.CompetidorRepository;
import municipalidad.carreramtb.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.MidiFileFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompetidorService {

    @Autowired
    GeneroRepository generoRepository;
    @Autowired
    CompetidorRepository competidorRepository;
    @Autowired
    CompeticionRepository competicionRepository;

    @Autowired
    KitService kitService;

    @Transactional
    public void crearCompetidor(String nombre, String apellido, Integer dni,Integer anioNac, String localidad, String telefono, Long idGenero, Long idCompeticion, String team) throws Exception {
        validarDatos(nombre,apellido,dni,anioNac,localidad,telefono,team);
        Optional<Genero> respGenero = generoRepository.findById(idGenero);
        Optional<Competicion> respCompeticion = competicionRepository.findById(idCompeticion);

        Competidor competidor = new Competidor();
        competidor.setNombre(nombre);
        competidor.setApellido(apellido);
        competidor.setDni(dni);
        competidor.setAnioNacimiento(anioNac);
        competidor.setLocalidad(localidad);
        competidor.setTelefono(telefono);
        competidor.setTeam(team);
        competidor.setFechaInscripcion(new Date());

        if (respCompeticion.isPresent() && respGenero.isPresent()){
            Competicion competicion = respCompeticion.get();
            competidor.setCompeticion(competicion);

            Genero genero = respGenero.get();
            competidor.setGenero(genero);

            String categoria = calcularCategoria(anioNac,genero.getTipo(),competicion.getTipo());
            competidor.setCategoria(categoria);
        }

        competidorRepository.save(competidor);
    }

    public List<Competidor> getAll(){
        return competidorRepository.findAll();
    }

    private String calcularCategoria(Integer anioNac, String genero , String competicion) {
        String categoria = "";

        if (competicion.equals("promocional")){

            if (anioNac>1998){
                categoria="P1";
            }else if (anioNac<=1998 && anioNac>1986) {
                categoria="P2";
            }else if (anioNac<=1989 && anioNac>1976){
                categoria="P3";
            }else if (anioNac<=1978 && anioNac>1966){
            categoria="P4";
            }else if (anioNac<=1966){
                categoria="P5";
            }

        }else{
            categoria = calcularCompetitiva(anioNac,genero);
        }

        return categoria;
    }

    private String calcularCompetitiva(Integer anioNac, String genero) {
        String categoria= "";

        if (genero.equals("damas")) {

            if (anioNac <= 2007 && anioNac > 1993) {
                categoria = "DamA";
            } else if (anioNac <= 1993 && anioNac > 1983) {
                categoria = "DamB";
            } else if (anioNac <= 1983 && anioNac > 1973) {
                categoria = "DamC";
            } else if (anioNac <= 1973) {
                categoria = "DamD";
            }
        }else{

            if (anioNac<=2004 && anioNac>2000){
                categoria="Sub23";
            }else if(anioNac<=2000 && anioNac>1993){
                categoria="Elite";
            }else if(anioNac<=1993 && anioNac>1988){
                categoria="CabA1";
            }else if(anioNac<=1988 && anioNac>1983){
                categoria="CabA2";
            }else if(anioNac<=1983 && anioNac>1978) {
                categoria = "CabB1";
            }
            else if(anioNac<=1978 && anioNac>1973) {
                categoria = "CabB2";
            }
            else if(anioNac<=1973 && anioNac>1968) {
                categoria = "CabC1";
            }
            else if(anioNac<=1968 && anioNac>1963) {
                categoria = "CabC2";
            }
            else if(anioNac<=1963 && anioNac>1958) {
                categoria = "CabD1";
            }
            else if(anioNac<=1958 && anioNac>1953) {
                categoria = "CabD2";
            }
            else if(anioNac<=1953) {
                categoria = "CabE";
            }
        }
        return categoria;
    }

    private void validarDatos(String nombre, String apellido, Integer dni, Integer anioNac,String localidad, String telefono, String team) throws Exception {
        if (nombre.isEmpty() || nombre==null){
            throw new Exception("nombre no valido");
        }
        if (apellido.isEmpty() || apellido==null){
            throw new Exception("apellido no valido");
        }
        if (dni==null){
            throw new Exception("dni no valido");
        }
        if (anioNac==null){
            throw new Exception("dni no valido");
        }
        if (localidad.isEmpty() || localidad==null){
            throw new Exception("localidad no valido");
        }

        if (telefono.isEmpty() || telefono==null){
            throw new Exception("telefono no valido");
        }
        if (team.isEmpty() || team==null){
           team="Sin Equipo";
        }
    }

    @Transactional
    public void cargarKit(String identificadorKit , Long idCompetidor) throws Exception {

        Optional<Competidor> respuesta = competidorRepository.findById(idCompetidor);

        if (respuesta.isPresent()){
            Competidor competidor = respuesta.get();
            competidor.setKit(kitService.crearKit(identificadorKit,idCompetidor));
            competidorRepository.save(competidor);
        }else{
            throw new Exception("El competidor no existe!");
        }

    }
}
