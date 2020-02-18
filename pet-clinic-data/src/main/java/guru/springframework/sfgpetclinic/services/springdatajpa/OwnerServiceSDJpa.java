package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerServiceSDJpa implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceSDJpa(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Set<Owner> findByLastName(String lastName) {
        return this.ownerRepository.findByLastName(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> all = new HashSet<>();
        this.ownerRepository.findAll().forEach(all::add);
        return all;
    }

    @Override
    public Owner findById(Long id) {
        return this.ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner save(Owner owner) {
        return this.ownerRepository.save(owner);
    }

    @Override
    public void delete(Owner owner) {
        this.ownerRepository.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
        this.ownerRepository.deleteById(id);
    }
}
