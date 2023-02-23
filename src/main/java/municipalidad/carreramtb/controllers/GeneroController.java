package municipalidad.carreramtb.controllers;

import municipalidad.carreramtb.entidades.Competicion;
import municipalidad.carreramtb.entidades.Genero;
import municipalidad.carreramtb.services.CompeticionService;
import municipalidad.carreramtb.services.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GeneroController {
    @Autowired
    CompeticionService competicionService;

    @Autowired
    GeneroService generoService;

    @GetMapping("/generos")
    public List<Genero> allGeneros() {
        return generoService.getAll();
    }
}
