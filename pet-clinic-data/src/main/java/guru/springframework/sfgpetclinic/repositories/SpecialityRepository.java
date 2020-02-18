package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.Speciality;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SpecialityRepository extends PagingAndSortingRepository<Speciality, Long> {
}
