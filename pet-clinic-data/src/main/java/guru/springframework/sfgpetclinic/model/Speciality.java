package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
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
