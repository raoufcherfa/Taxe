package org.sid.taxes.web;

import org.sid.taxes.dao.EntrepriseRepository;
import org.sid.taxes.entitis.Entreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxeRestController {
    @Autowired
    private EntrepriseRepository entrepriseRepository;
    @RequestMapping("/listEntreprises")
    public Page<Entreprise> list(@RequestParam (name = "motCle",defaultValue = "") String motCle,
                                 @RequestParam (name = "page",defaultValue = "0") int page,
                                 @RequestParam (name = "size",defaultValue = "4") int size){
        Pageable pageable = PageRequest.of (page, size);
        return entrepriseRepository.chercher("%"+motCle+"%",pageable);
    }
}
