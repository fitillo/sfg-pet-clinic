package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.PetType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PetTypeRepository extends PagingAndSortingRepository<PetType, Long> {
}
