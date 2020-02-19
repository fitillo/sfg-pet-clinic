package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "speciality")
public class Speciality extends NamedEntity {

    @ManyToMany(mappedBy = "specialities")
    private final Set<Vet> vets = new HashSet<>();

    public boolean addVet(Vet vet) {
        vet.addSpeciality(this);
        return this.vets.add(vet);
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + this.getId() +
                ", name=" + this.getName() +
                '}';
    }
}
