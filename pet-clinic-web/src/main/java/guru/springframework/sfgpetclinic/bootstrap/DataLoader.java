package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final PetService petService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, PetService petService, VetService vetService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("Joe");
        owner1.setLastName("Ingles");

        Owner owner2 = new Owner();
        owner2.setFirstName("Bojan");
        owner2.setLastName("Bogdanović");

        Vet vet1 = new Vet();
        vet1.setFirstName("Mike");
        vet1.setLastName("Conley");

        Vet vet2 = new Vet();
        vet2.setFirstName("Rudy");
        vet2.setLastName("Gobert");

        ownerService.save(owner1);
        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        vetService.save(vet1);
        vetService.save(vet2);

        System.out.println("Loaded Vets....");

        System.out.println("Registered owners are:");
        ownerService.findAll().forEach(System.out::println);
    }
}
