package guru.springframework.sfpetclinic.bootstrap;

import guru.springframework.sfpetclinic.model.Owner;
import guru.springframework.sfpetclinic.model.Pet;
import guru.springframework.sfpetclinic.model.PetType;
import guru.springframework.sfpetclinic.model.Vet;
import guru.springframework.sfpetclinic.services.OwnerService;
import guru.springframework.sfpetclinic.services.PetTypeService;
import guru.springframework.sfpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Jessie");
        owner1.setLastName("Pinkman");
        owner1.setAddress("123 Wall Street");
        owner1.setCity("New York");
        owner1.setTelephone("2342354365");

        Pet jessiesPet = new Pet();
        jessiesPet.setPetType(savedDogPetType);
        jessiesPet.setOwner(owner1);
        jessiesPet.setBirthDate(LocalDate.now());
        jessiesPet.setName("Óliver");
        owner1.getPets().add(jessiesPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Michael");
        owner2.setLastName("Scott");
        owner2.setAddress("563 Groove Street");
        owner2.setCity("Los Angeles");
        owner2.setTelephone("5433465767");

        Pet mikesCat = new Pet();
        mikesCat.setName("Maia");
        mikesCat.setOwner(owner2);
        mikesCat.setBirthDate(LocalDate.now());
        mikesCat.setPetType(savedCatPetType);
        owner2.getPets().add(mikesCat);

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Kevin");
        vet1.setLastName("Malone");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Pam");
        vet2.setLastName("Harper");

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
