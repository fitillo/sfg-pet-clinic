package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    private OwnerServiceMap ownerService;
    private Owner joe;
    private static final long JOE_ID = 1L;
    private static final String JOE_NAME = "Joe";
    private Owner bojan;
    private static final long BOJAN_ID = 2L;
    private static final String BOJAN_NAME = "Bojan";

    @BeforeEach
    void setUp() {
        ownerService = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
    }

    private void loadOwners() {
        this.joe = Owner.builder().id(JOE_ID).firstName(JOE_NAME).lastName("Ingles").address("Joe's Home").city("J Salt Lake City").
                telephone("Joe's phone").build();
        this.bojan = Owner.builder().id(BOJAN_ID).firstName(BOJAN_NAME).lastName("Bogdanović").address("Bojan's Home")
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
        ownerService.deleteById(JOE_ID);
        assertEquals(1, ownerService.findAll().size());
        ownerService.deleteById(BOJAN_ID);
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

        assertEquals(1L, ownerService.save(Owner.builder().firstName(JOE_NAME).build()).getId());
        assertEquals(1, ownerService.map.size());
    }

    @Test
    void findById() {
        loadOwners();
        assertEquals(JOE_NAME, ownerService.findById(JOE_ID).getFirstName());
        assertEquals(BOJAN_NAME, ownerService.findById(BOJAN_ID).getFirstName());
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