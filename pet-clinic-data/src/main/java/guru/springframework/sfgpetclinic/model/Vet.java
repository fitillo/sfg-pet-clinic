package guru.springframework.sfgpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vet")
public class Vet extends Person {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vets_specialities",
        joinColumns = @JoinColumn(name = "vet_id"),
        inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private final Set<Speciality> specialities = new HashSet<>();

    @Builder
    public Vet(Long id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    public boolean addSpeciality(Speciality speciality) {
        return this.specialities.add(speciality);
    }
}
