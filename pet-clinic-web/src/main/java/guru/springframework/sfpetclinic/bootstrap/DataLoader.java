package guru.springframework.sfpetclinic.bootstrap;

import guru.springframework.sfpetclinic.model.*;
import guru.springframework.sfpetclinic.services.OwnerService;
import guru.springframework.sfpetclinic.services.PetTypeService;
import guru.springframework.sfpetclinic.services.SpecialtyService;
import guru.springframework.sfpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

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
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Pam");
        vet2.setLastName("Harper");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
