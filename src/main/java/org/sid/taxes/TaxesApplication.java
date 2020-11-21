package org.sid.taxes;

import org.sid.taxes.dao.EntrepriseRepository;
import org.sid.taxes.dao.TaxeRepository;
import org.sid.taxes.entitis.Entreprise;
import org.sid.taxes.entitis.IR;
import org.sid.taxes.entitis.TVA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class TaxesApplication implements CommandLineRunner {
    @Autowired
    private EntrepriseRepository entrepriseRepository;
    @Autowired
    private TaxeRepository taxeRepository;

    public static void main(String[] args) {

        SpringApplication.run(TaxesApplication.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     */
        @Override
    public void run(String... args) {
        Entreprise e1 = entrepriseRepository.save(new Entreprise("R1", "r1@gmail.com", "SARL"));
        Entreprise e2 = entrepriseRepository.save(new Entreprise("R2", "r2@gmail.com", "SAFL"));


        taxeRepository.save(new TVA("TVA HABITATION",new Date(),900,e1));
        taxeRepository.save(new TVA("TVA VOITURE",new Date(),100,e1));
        taxeRepository.save(new IR("TVA HABITATION",new Date(),500,e2));
        taxeRepository.save(new IR("TVA VOITURE",new Date(),100,e2));
    }
}
