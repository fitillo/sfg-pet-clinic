package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class NamedEntity extends BaseEntity {

    private String name;

    public NamedEntity(Long id, String name) {
        super(id);
        this.name = name;
    }
}
