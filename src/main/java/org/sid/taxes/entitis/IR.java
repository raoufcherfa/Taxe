package org.sid.taxes.entitis;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@DiscriminatorValue("IR")
public class IR extends Taxe {

    public IR() {
        super();
    }

    public IR(String titre, Date dateTaxe, double montant, Entreprise entreprise) {
        super(titre, dateTaxe, montant, entreprise);
    }
}
