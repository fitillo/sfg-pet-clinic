package guru.springframework.sfgpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pet")
public class Pet extends NamedEntity {

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @JoinColumn(name = "pet_type_id")
    @ManyToOne
    private PetType petType;

    @JoinColumn(name = "owner_id")
    @ManyToOne
    private Owner owner;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private Set<Visit> visits = new HashSet<>();

    @Builder
    public Pet(Long id, String name, LocalDate birthDate, PetType petType, Owner owner, Set<Visit> visits) {
        super(id, name);
        this.birthDate = birthDate;
        this.petType = petType;
        this.owner = owner;
        if (visits != null) {
            this.visits = visits;
        }
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + this.getId() +
                ", name=" + this.getName() +
                ", birthDate=" + birthDate +
                ((petType != null ) ? ", petType=" + petType.toString() : "") +
                ", owner=" + owner.toString() +
                '}';
    }

    public Visit addVisit(Visit visit) {
        if (visit != null) {
            this.visits.add(visit);
            visit.setPet(this);
        }

        return visit;
    }
}
