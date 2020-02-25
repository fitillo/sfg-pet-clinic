package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Set;

public interface OwnerRepository extends PagingAndSortingRepository<Owner, Long> {

    Set<Owner> findByLastName(String lastName);

    //@Query("SELECT DISTINCT owner FROM Owner owner left join fetch owner.pets where owner.lastName like :lastName%")
    //@Transactional(readOnly = true)
    List<Owner> findAllByLastNameLike(String lastName);
}
