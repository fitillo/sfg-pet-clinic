package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetService petService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, PetService petService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dogs = new PetType();
        dogs.setName("dog");
        PetType cats = new PetType();
        cats.setName("cat");
        PetType fishes = new PetType();
        fishes.setName("fish");

        dogs = petTypeService.save(dogs);
        cats = petTypeService.save(cats);
        fishes = petTypeService.save(fishes);
        System.out.println("Loaded pet types........................");

        Owner owner1 = new Owner();
        owner1.setFirstName("Joe");
        owner1.setLastName("Ingles");
        owner1.setAddress("Joe's Home");
        owner1.setCity("J Salt Lake City");
        owner1.setTelephone("Joe's phone");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy");
        Pet joesDog = new Pet();
        joesDog.setName("Pepito");
        joesDog.setBirthDate(LocalDate.parse("01/02/2020", dtf));
        joesDog.setPetType(dogs);
        joesDog.setOwner(owner1);
        owner1.getPets().add(joesDog);

        Owner owner2 = new Owner();
        owner2.setFirstName("Bojan");
        owner2.setLastName("Bogdanović");
        owner2.setAddress("Bojan's Home");
        owner2.setCity("B Salt Lake City");
        owner2.setTelephone("Bojan's phone");

        Pet bojansCat = new Pet();
        bojansCat.setName("Juanito");
        bojansCat.setBirthDate(LocalDate.now());
        bojansCat.setPetType(cats);
        bojansCat.setOwner(owner2);
        owner2.getPets().add(bojansCat);

        ownerService.save(owner1);
        ownerService.save(owner2);
        System.out.println("Loaded Owners and pets...............................");

        Vet vet1 = new Vet();
        vet1.setFirstName("Mike");
        vet1.setLastName("Conley");

        Vet vet2 = new Vet();
        vet2.setFirstName("Rudy");
        vet2.setLastName("Gobert");

        vetService.save(vet1);
        vetService.save(vet2);
        System.out.println("Loaded Vets...............................");
    }
}
