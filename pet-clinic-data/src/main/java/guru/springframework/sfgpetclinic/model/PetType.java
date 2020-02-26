package guru.springframework.sfgpetclinic.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "types")
public class PetType extends NamedEntity {

    @Builder
    public PetType(Long id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
