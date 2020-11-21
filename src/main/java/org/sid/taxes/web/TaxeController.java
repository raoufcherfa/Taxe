package org.sid.taxes.web;

import org.sid.taxes.dao.EntrepriseRepository;
import org.sid.taxes.dao.TaxeRepository;
import org.sid.taxes.entitis.Entreprise;
import org.sid.taxes.entitis.Taxe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;


@Controller
public class TaxeController {
    @Autowired
    private EntrepriseRepository entrepriseRepository;
    @Autowired
    private TaxeRepository taxeRepository;

    @RequestMapping(value = "/entreprises", method = RequestMethod.GET)
    public String index(Model model,
                        @RequestParam(value = "motCle",defaultValue = "") String motCle,
                        @RequestParam(value = "page", defaultValue = "0") int p,
                        @RequestParam(value = "size", defaultValue = "4") int s) {
        Pageable pageable = PageRequest.of (p, s);
        Page<Entreprise> pageEntreprises
                = entrepriseRepository.chercher( "%"+motCle+"%",pageable);
        model.addAttribute("listEntreprises", pageEntreprises.getContent());
        int[] pages = new int[pageEntreprises.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("pageCourante", p);
        model.addAttribute("motCle",motCle);

        return "entreprises";
    }
    @RequestMapping(value = "/formEntreprise")
    public  String form(Model model){
        model.addAttribute("entreprise",new Entreprise());
        return "formEntreprise";
    }
    @RequestMapping(value = "/saveEntreprise")
    public  String form(Model model,
                        @Valid Entreprise e,
                        BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "formEntreprise";
        entrepriseRepository.save(e);
        return "redirect:/entreprises";
    }

    @RequestMapping(value = "/taxes",method = RequestMethod.GET)
    public  String taxes(Model model,
                         @RequestParam(name = "code",defaultValue = "-1") Long code) {
        if (code == -1) model.addAttribute("taxes",new ArrayList<Taxe>());
         else {
            Entreprise e = new Entreprise();
            e.setCode(code);
            model.addAttribute("entreprises", entrepriseRepository.findAll());
            model.addAttribute("taxes", taxeRepository.findByEntreprise(e));

        }
        return "taxes";
    }
}
