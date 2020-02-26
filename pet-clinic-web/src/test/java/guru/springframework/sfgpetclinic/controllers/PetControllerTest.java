package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    public static final long ID_1 = 1L;
    public static final long ID_2 = 2L;
    public static final String DOG = "dog";
    public static final String CAT = "cat";
    public static final String JOE = "Joe";
    public static final String INGLES = "Ingles";

    @InjectMocks
    private PetController controller;

    @Mock
    private PetService petService;
    @Mock
    private PetTypeService petTypeService;
    @Mock
    private OwnerService ownerService;

    MockMvc mockMvc;

    Set<PetType> petTypes;
    PetType dogType;
    PetType catType;
    Owner owner;
    Pet dog;
    Pet cat;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        petTypes = new HashSet<>();
        dogType = PetType.builder().id(ID_1).name(DOG).build();
        catType = PetType.builder().id(ID_2).name(CAT).build();
        petTypes.add(dogType);
        petTypes.add(catType);

        owner = Owner.builder().id(ID_1).firstName(JOE).lastName(INGLES).build();
        dog = Pet.builder().id(ID_1).name(DOG).owner(owner).petType(dogType).build();
        cat = Pet.builder().id(ID_2).name(CAT).owner(owner).petType(catType).build();
        owner.addPet(dog);
        owner.addPet(cat);
    }

    @Test
    void testCreatePetForm() throws Exception {
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(ownerService.findById(ID_1)).thenReturn(owner);
        mockMvc.perform(get("/owners/"+ID_1+"/pets/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attribute("owner", hasProperty("id", is(ID_1))))
                .andExpect(model().attribute("types", hasSize(2)));

        verifyNoInteractions(petService);
        verify(petTypeService).findAll();
        verify(ownerService).findById(ID_1);
    }

    @Test
    void testProcessCreatePetForm() throws Exception {
        dog.setOwner(owner);
        owner.addPet(dog);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(ownerService.findById(ID_1)).thenReturn(owner);
        mockMvc.perform(post("/owners/"+ID_1+"/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/"+owner.getId()))
                .andExpect(model().attribute("owner", hasProperty("id", is(ID_1))))
                .andExpect(model().attribute("types", hasSize(2)))
                .andExpect(model().attributeExists("owner"));

        verify(petTypeService).findAll();
        verify(ownerService).findById(ID_1);
        verify(petService).save(any());
    }

    @Test
    void testUpdatePetForm() throws Exception {
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(ownerService.findById(ID_1)).thenReturn(owner);
        mockMvc.perform(get("/owners/"+ID_1+"/pets/"+ID_1+"/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attribute("owner", hasProperty("id", is(ID_1))))
                .andExpect(model().attribute("types", hasSize(2)));

        verify(petService).findById(anyLong());
        verify(petTypeService).findAll();
        verify(ownerService).findById(ID_1);
    }

    @Test
    void testProcessUpdatePetForm() throws Exception {
        dog.setName(DOG+"Updated");
        owner.addPet(dog);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(ownerService.findById(ID_1)).thenReturn(owner);
        mockMvc.perform(post("/owners/"+ID_1+"/pets/"+ID_1+"/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/"+owner.getId()))
                .andExpect(model().attribute("owner", hasProperty("id", is(ID_1))))
                .andExpect(model().attribute("types", hasSize(2)))
                .andExpect(model().attributeExists("owner"));

        verify(petTypeService).findAll();
        verify(ownerService).findById(ID_1);
        verify(petService).save(any());
    }
}