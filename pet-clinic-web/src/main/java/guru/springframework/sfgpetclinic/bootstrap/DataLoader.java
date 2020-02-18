package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (petTypeService.findAll().isEmpty()) {
            loadData();
        }
    }

    private void loadData() {
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

        Owner joe = new Owner();
        joe.setFirstName("Joe");
        joe.setLastName("Ingles");
        joe.setAddress("Joe's Home");
        joe.setCity("J Salt Lake City");
        joe.setTelephone("Joe's phone");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy");
        Pet joesDog = new Pet();
        joesDog.setName("Pepito");
        joesDog.setBirthDate(LocalDate.parse("01/02/2020", dtf));
        joesDog.setPetType(dogs);
        joesDog.setOwner(joe);
        joe.getPets().add(joesDog);

        Owner bojan = new Owner();
        bojan.setFirstName("Bojan");
        bojan.setLastName("Bogdanović");
        bojan.setAddress("Bojan's Home");
        bojan.setCity("B Salt Lake City");
        bojan.setTelephone("Bojan's phone");

        Pet bojansCat = new Pet();
        bojansCat.setName("Juanito");
        bojansCat.setBirthDate(LocalDate.now());
        bojansCat.setPetType(cats);
        bojansCat.setOwner(bojan);

        Pet bojansFish = new Pet();
        bojansFish.setName("Nemo");
        bojansFish.setBirthDate(LocalDate.parse("01/01/2020", dtf));
        bojansFish.setPetType(fishes);
        bojansFish.setOwner(bojan);

        bojan.getPets().add(bojansCat);
        bojan.getPets().add(bojansFish);

        ownerService.save(joe);
        ownerService.save(bojan);
        System.out.println("Loaded Owners and pets...............................");
        System.out.println("Owners pets are:");
        ownerService.findAll().forEach(owner -> {
            System.out.println(owner.toString());
            owner.getPets().forEach(pet -> System.out.println(pet.toString()));
        });

        Speciality radiology = new Speciality();
        radiology.setName("radiology");
        Speciality surgery = new Speciality();
        surgery.setName("surgery");

        Vet mike = new Vet();
        mike.setFirstName("Mike");
        mike.setLastName("Conley");
        mike.getSpecialities().add(radiology);

        Vet rudy = new Vet();
        rudy.setFirstName("Rudy");
        rudy.setLastName("Gobert");
        rudy.getSpecialities().add(surgery);
        rudy.getSpecialities().add(radiology);

        vetService.save(mike);
        vetService.save(rudy);
        System.out.println("Loaded Vets and specialities...............................");
        System.out.println("Vets specialities are: ");
        vetService.findAll().forEach(vet -> {
            System.out.println(vet.toString());
            vet.getSpecialities().forEach(speciality -> System.out.println(speciality.getName()));
        });

        Visit visit = new Visit();
        visit.setDate(LocalDate.now());
        visit.setDescription("Joe's dog revision");
        visit.setPet(joesDog);
        visit.setVet(mike);

        Visit visit1 = new Visit();
        visit1.setDate(LocalDate.now());
        visit1.setDescription("Bojan's cat vaccines");
        visit1.setPet(bojansCat);
        visit1.setVet(mike);

        Visit visit2 = new Visit();
        visit2.setDate(LocalDate.now());
        visit2.setDescription("Bojan's fish surgery");
        visit2.setPet(bojansFish);
        visit2.setVet(rudy);

        visitService.save(visit);
        visitService.save(visit1);
        System.out.println("Loadded Visits.............................................");
    }
}
