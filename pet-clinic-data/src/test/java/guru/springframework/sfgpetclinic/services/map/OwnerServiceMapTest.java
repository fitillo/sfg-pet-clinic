package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    private OwnerServiceMap ownerService;
    private Owner joe;
    private final long joeId = 1L;
    private final String joeName = "Joe";
    private Owner bojan;
    private final long bojanId = 2L;
    private final String bojanName = "Bojan";

    @BeforeEach
    void setUp() {
        ownerService = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
    }

    private void loadOwners() {


        this.joe = Owner.builder().id(joeId).firstName(joeName).lastName("Ingles").address("Joe's Home").city("J Salt Lake City").
                telephone("Joe's phone").build();
        this.bojan = Owner.builder().id(bojanId).firstName(bojanName).lastName("Bogdanović").address("Bojan's Home")
                .city("B Salt Lake City").telephone("Bojan's phone").build();
        ownerService.save(this.joe);
        ownerService.save(this.bojan);
    }

    @Test
    void findAllEmpty() {
        assertEquals(0, ownerService.findAll().size());
    }

    @Test
    void findAll() {
        loadOwners();
        assertEquals(2, ownerService.findAll().size());
    }

    @Test
    void deleteById() {
        loadOwners();
        ownerService.deleteById(joeId);
        assertEquals(1, ownerService.findAll().size());
        ownerService.deleteById(bojanId);
        assertEquals(0, ownerService.findAll().size());
    }

    @Test
    void delete() {
        loadOwners();
        ownerService.delete(joe);
        assertEquals(1, ownerService.findAll().size());
        ownerService.delete(bojan);
        assertEquals(0, ownerService.findAll().size());
    }

    @Test
    void save() {
        assertNull(ownerService.save(null));

        assertEquals(1L, ownerService.save(Owner.builder().firstName(joeName).build()).getId());
        assertEquals(1, ownerService.map.size());
    }

    @Test
    void findById() {
        loadOwners();
        assertEquals(joeName, ownerService.findById(joeId).getFirstName());
        assertEquals(bojanName, ownerService.findById(bojanId).getFirstName());
    }

    @Test
    void findByLastName() {
        loadOwners();
        Set<Owner> owners = ownerService.findByLastName("Ingles");
        assertEquals(1, owners.size());
    }

    @Test
    void findByLastNameEmpty() {
        assertTrue(ownerService.findByLastName("Any").isEmpty());
    }
}