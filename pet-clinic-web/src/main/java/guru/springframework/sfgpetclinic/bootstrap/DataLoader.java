package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        addOwners();
        System.out.println("Loaded Owners....");

        addVets();
        System.out.println("Loaded Vets....");

        addPetTypes();
        System.out.println("Loaded pet types....");
        System.out.println("Types of pets are: ");
        petTypeService.findAll().forEach(petType -> System.out.println(petType.getName()));
    }

    private void addPetTypes() {
        PetType dogs = new PetType();
        dogs.setName("dog");
        PetType cats = new PetType();
        cats.setName("cat");
        PetType fishes = new PetType();
        fishes.setName("fish");

        petTypeService.save(dogs);
        petTypeService.save(cats);
        petTypeService.save(fishes);
    }

    private void addVets() {
        Vet vet1 = new Vet();
        vet1.setFirstName("Mike");
        vet1.setLastName("Conley");

        Vet vet2 = new Vet();
        vet2.setFirstName("Rudy");
        vet2.setLastName("Gobert");

        vetService.save(vet1);
        vetService.save(vet2);
    }

    private void addOwners() {
        Owner owner1 = new Owner();
        owner1.setFirstName("Joe");
        owner1.setLastName("Ingles");

        Owner owner2 = new Owner();
        owner2.setFirstName("Bojan");
        owner2.setLastName("Bogdanović");

        ownerService.save(owner1);
        ownerService.save(owner2);
    }
}
