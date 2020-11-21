package org.sid.taxes.entitis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
public class Entreprise implements Serializable {
    @Id @GeneratedValue
    Long code;
    @NotBlank(message = "Le nom est obligatoire ")
    String nom;
    @NotBlank(message = "Le mail est obligatoire")
    @Email
    String email;
    @NotBlank(message = "La RaisonSociale est obligatoire ")
    String raisonSociale;
    @OneToMany(mappedBy = "entreprise",fetch = FetchType.LAZY)
    Collection<Taxe> taxes;

    public Entreprise() {
        super();

    }

    public Entreprise(String nom, String email, String raisonSociale) {
        super();
        this.nom = nom;
        this.email = email;
        this.raisonSociale = raisonSociale;
        this.taxes = taxes;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }
    @JsonIgnore
    public Collection<Taxe> getTaxes() {
        return taxes;
    }

    public void setTaxes(Collection<Taxe> taxes) {
        this.taxes = taxes;
    }
}
