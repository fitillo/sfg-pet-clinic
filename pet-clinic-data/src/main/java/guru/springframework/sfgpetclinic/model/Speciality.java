package guru.springframework.sfgpetclinic.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "speciality")
public class Speciality extends NamedEntity {

    @ManyToMany(mappedBy = "specialities")
    private Set<Vet> vets;

    public Set<Vet> getVets() {
        return vets;
    }

    public boolean addVet(Vet vet) {
        vet.addSpeciality(this);
        return this.vets.add(vet);
    }

    public void setVets(Set<Vet> vets) {
        this.vets = vets;
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + this.getId() +
                ", name=" + this.getName() +
                '}';
    }
}
