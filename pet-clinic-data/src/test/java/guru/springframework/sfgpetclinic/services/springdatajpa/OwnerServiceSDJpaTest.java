package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceSDJpaTest {

    @Mock
    private OwnerRepository repository;

    @InjectMocks
    private OwnerServiceSDJpa service;

    private Owner joe;
    private static final long JOE_ID = 1L;
    private static final String JOE_NAME = "Joe";
    private static final String JOE_LAST_NAME = "Ingles";
    private Owner bojan;
    private static final long BOJAN_ID = 2L;
    private static final String BOJAN_NAME = "Bojan";

    @BeforeEach
    void setUp() {
        //Using @ExtendWith(MockitoExtension.class) instead
        //MockitoAnnotations.initMocks(this);
        //Using @InjectMocks instead
        //service = new OwnerServiceSDJpa(repository);

        bojan = Owner.builder().id(BOJAN_ID).firstName(BOJAN_NAME).lastName("Bogdanović").address("Bojan's Home")
                .city("B Salt Lake City").telephone("Bojan's phone").build();
        joe = Owner.builder().id(JOE_ID).firstName(JOE_NAME).lastName("Ingles").address("Joe's Home").city("J Salt Lake City").
                telephone("Joe's phone").build();
    }

    private Set<Owner> getOwners() {
        var owners = this.getJoe();
        owners.add(bojan);

        return owners;
    }

    private Set<Owner> getJoe() {
        var owners = new HashSet<Owner>();
        owners.add(joe);
        return owners;
    }

    @Test
    void findByLastName() {
        when(repository.findByLastName(JOE_LAST_NAME)).thenReturn(getJoe());

        Set<Owner> owners = service.findByLastName(JOE_LAST_NAME);
        assertEquals(1, owners.size());
        verify(repository, times(1)).findByLastName(JOE_LAST_NAME);
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(getOwners());

        Set<Owner> owners = service.findAll();
        assertEquals(2, owners.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(repository.findById(JOE_ID)).thenReturn(Optional.of(joe));

        assertEquals(JOE_ID, service.findById(JOE_ID).getId());
        verify(repository, times(1)).findById(JOE_ID);
    }

    @Test
    void findByIdNull() {
        when(repository.findById(JOE_ID)).thenReturn(Optional.ofNullable(null));

        assertNull(service.findById(JOE_ID));
        verify(repository, times(1)).findById(JOE_ID);
    }

    @Test
    void save() {
        when(repository.save(joe)).thenReturn(joe);

        assertEquals(JOE_ID, service.save(joe).getId());
        verify(repository, times(1)).save(joe);
    }

    @Test
    void delete() {
        service.delete(joe);
        service.delete(bojan);
        verify(repository, times(1)).delete(joe);
        verify(repository, times(1)).delete(bojan);
    }

    @Test
    void deleteById() {
        service.deleteById(JOE_ID);
        verify(repository).deleteById(anyLong());
    }
}