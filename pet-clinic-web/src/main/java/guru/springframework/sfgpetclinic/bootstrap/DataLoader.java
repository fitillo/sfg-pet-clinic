package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
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
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, VisitService visitService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.visitService = visitService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) {
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

        Owner joe = Owner.builder().firstName("Joe").lastName("Ingles").address("Joe's Home").city("J Salt Lake City").
                telephone("Joe's phone").build();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy");
        Pet joesDog = Pet.builder().name("Poochie").birthDate(LocalDate.parse("01/02/2020", dtf))
                .petType(dogs).owner(joe).build();
        joe.getPets().add(joesDog);

        Owner joey = Owner.builder().firstName("joey").lastName("Ingles").address("Joey's Home").city("J Salt Lake City").
                telephone("Joey's phone").build();
        Pet joeysDog = Pet.builder().name("Lassie").birthDate(LocalDate.parse("01/02/2019", dtf))
                .petType(dogs).owner(joey).build();
        joey.getPets().add(joeysDog);

        Owner bojan = Owner.builder().firstName("Bojan").lastName("Bogdanović").address("Bojan's Home")
                .city("B Salt Lake City").telephone("Bojan's phone").build();

        Pet bojansCat = Pet.builder().name("Snowball").birthDate(LocalDate.now()).petType(cats).owner(bojan).build();

        Pet bojansFish = Pet.builder().name("Nemo").birthDate(LocalDate.parse("01/01/2020", dtf))
                .petType(fishes).owner(bojan).build();

        bojan.getPets().add(bojansCat);
        bojan.getPets().add(bojansFish);

        ownerService.save(joe);
        ownerService.save(joey);
        ownerService.save(bojan);
        System.out.println("Loaded Owners and pets...............................");
        System.out.println("Owners pets are:");
        ownerService.findAll().forEach(owner -> {
            System.out.println(owner.toString());
            //owner.getPets().forEach(pet -> System.out.println(pet.toString()));
        });

        Speciality radiology = new Speciality();
        radiology.setName("radiology");
        Speciality surgery = new Speciality();
        surgery.setName("surgery");

        specialityService.save(radiology);
        specialityService.save(surgery);

        Vet mike = Vet.builder().firstName("Mike").lastName("Conley").build();
        mike.getSpecialities().add(radiology);

        Vet rudy = Vet.builder().firstName("Rudy").lastName("Gobert").build();
        rudy.getSpecialities().add(surgery);
        rudy.getSpecialities().add(radiology);

        vetService.save(mike);
        vetService.save(rudy);
        System.out.println("Loaded Vets and specialities...............................");
        System.out.println("Vets and its specialities are: ");
        vetService.findAll().forEach(vet -> {
            System.out.println(vet.toString());
            vet.getSpecialities().forEach(speciality -> System.out.println(speciality.getName()));
        });

        Visit visit = new Visit();
        visit.setDate(LocalDate.now());
        visit.setDescription("Joe's dog revision");
        visit.setPet(joesDog);
        visit.setVet(mike);

        Visit visit1 = Visit.builder().date(LocalDate.now()).description("Bojan's cat vaccines")
                .pet(bojansCat).vet(mike).build();

        Visit visit2 = Visit.builder().date(LocalDate.now()).description("Bojan's fish surgery")
                .pet(bojansFish).vet(rudy).build();

        visitService.save(visit);
        visitService.save(visit1);
        visitService.save(visit2);
        System.out.println("Loadded Visits.............................................");
    }
}
