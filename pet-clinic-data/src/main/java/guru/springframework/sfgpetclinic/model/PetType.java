package guru.springframework.sfgpetclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "types")
public class PetType extends NamedEntity {

    @Override
    public String toString() {
        return "PetType{" +
                "id='" + this.getId() + '\'' +
                ", name='" + this.getName() + '\'' +
                '}';
    }
}
