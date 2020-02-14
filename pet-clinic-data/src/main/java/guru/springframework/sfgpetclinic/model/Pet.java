package guru.springframework.sfgpetclinic.model;

import java.time.LocalDate;

public class Pet extends NamedEntity {

    private LocalDate birthDate;
    private PetType petType;
    private Owner owner;

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
