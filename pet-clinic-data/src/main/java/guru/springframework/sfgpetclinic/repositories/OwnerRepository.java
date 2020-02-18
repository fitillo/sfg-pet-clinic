package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;

public interface OwnerRepository extends PagingAndSortingRepository<Owner, Long> {

    Set<Owner> findByLastName(String lastName);
}
