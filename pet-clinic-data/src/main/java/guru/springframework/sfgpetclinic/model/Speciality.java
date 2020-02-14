package guru.springframework.sfgpetclinic.model;

public class Speciality extends NamedEntity {

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + this.getId() +
                ", name=" + this.getName() +
                '}';
    }
}
