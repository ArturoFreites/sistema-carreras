package municipalidad.carreramtb.controllers;

import municipalidad.carreramtb.entidades.Kit;
import municipalidad.carreramtb.services.KitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class KitController {

    @Autowired
    KitService kitService;

    @GetMapping("/allkits")
    public List<Kit> getAll(){
        return kitService.getAll();
    }

}
