package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.Visit;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VisitRepository extends PagingAndSortingRepository<Visit, Long> {
}
