package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.Vet;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VetRepository extends PagingAndSortingRepository<Vet, Long> {
}
