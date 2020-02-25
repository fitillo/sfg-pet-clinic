package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    public static final long ID_1 = 1L;
    public static final long ID_2 = 2L;
    @InjectMocks
    OwnerController controller;

    @Mock
    OwnerService service;

    MockMvc mvc;

    @BeforeEach
    void setUp() {
        //Using @InjectMocks
        //controller = new OwnerController(service);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testListOwners() throws Exception {
        List<String> paths = Arrays.asList("/owners","/owners/");
        var owners = new HashSet<Owner>();
        owners.add(Owner.builder().id(ID_1).build());
        owners.add(Owner.builder().id(ID_2).build());

        when(service.findAll()).thenReturn(owners);

        for (String path : paths) {
            mvc.perform(get(path))
                    .andExpect(status().isOk())
                    .andExpect(view().name("owners/index"))
                    .andExpect(model().attributeExists("owners"))
                    .andExpect(model().attribute("owners", hasSize(2)));
        }
        verify(service, times(2)).findAll();
    }

    @Test
    void testShowOwner() throws Exception {
        when(service.findById(anyLong())).thenReturn(Owner.builder().id(ID_1).build());
        mvc.perform(get("/owners/"+ ID_1))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owner", hasProperty("id", is(ID_1))))
                .andExpect(view().name("owners/ownerDetails"));
        verify(service).findById(anyLong());
    }

    @Test
    void testFindOwners() throws Exception {
        List<String> paths = Arrays.asList("/find","/find.html");
        for (String path : paths) {
            mvc.perform(get("/owners" + path))
                    .andExpect(status().isOk())
                    .andExpect(view().name("notimplemented"));
        }

        verifyNoInteractions(service);
    }
}