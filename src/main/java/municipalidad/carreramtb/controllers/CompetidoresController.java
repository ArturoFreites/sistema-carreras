package municipalidad.carreramtb.controllers;

import municipalidad.carreramtb.entidades.Competidor;
import municipalidad.carreramtb.services.CompetidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompetidoresController {
    @Autowired
    CompetidorService competidorService;

    @GetMapping("/competidores")
    public List<Competidor> getCompetidores(){
        return competidorService.getAll();
    }

}
