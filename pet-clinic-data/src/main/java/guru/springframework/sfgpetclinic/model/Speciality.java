package guru.springframework.sfgpetclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "speciality")
public class Speciality extends NamedEntity {

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + this.getId() +
                ", name=" + this.getName() +
                '}';
    }
}
