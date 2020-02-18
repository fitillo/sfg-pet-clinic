package guru.springframework.sfgpetclinic.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + this.getId() +
                ", name=" + this.getName() +
                ", birthDate=" + birthDate +
                ", petType=" + petType.toString() +
                ", owner=" + owner.toString() +
                '}';
    }
}
