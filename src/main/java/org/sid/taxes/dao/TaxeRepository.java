package org.sid.taxes.dao;

import org.sid.taxes.entitis.Entreprise;
import org.sid.taxes.entitis.Taxe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxeRepository extends JpaRepository<Taxe,Long> {
    public List<Taxe> findByEntreprise(Entreprise e);
}
