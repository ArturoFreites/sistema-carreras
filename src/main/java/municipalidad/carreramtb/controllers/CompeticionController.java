package municipalidad.carreramtb.controllers;

import municipalidad.carreramtb.entidades.Competicion;
import municipalidad.carreramtb.services.CompeticionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping("/api")
public class CompeticionController {

    @Autowired
    CompeticionService competicionService;
    @GetMapping("/competiciones")
    public List<Competicion> allCompeticion(){
        return competicionService.getAll();
    }
}
