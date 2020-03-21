package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

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
        int count = this.petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType("Dog");
        PetType cat = new PetType("Cat");
        dog = petTypeService.save(dog);
        cat = petTypeService.save(cat);
        System.out.println("Loaded Pet Types ...");

        Speciality radiology = new Speciality("radiology");
        Speciality surgery = new Speciality("surgery");
        Speciality dentistry = new Speciality("dentistry");
        radiology = this.specialtyService.save(radiology);
        surgery = this.specialtyService.save(surgery);
        dentistry = this.specialtyService.save(dentistry);
        System.out.println("Loaded Specialties ...");

        Owner owner1 = new Owner();
        owner1.setFirstName("Michal");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Bickered");
        owner1.setCity("Miami");
        owner1.setTelephone("123123123");
        Pet mikesPet = new Pet();
        mikesPet.setPetType(dog);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.setPets(Set.of(mikesPet));

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Bickered");
        owner2.setCity("Miami");
        owner2.setTelephone("123123123");
        Pet fionasPet = new Pet();
        fionasPet.setName("Just Cat");
        fionasPet.setOwner(owner2);
        fionasPet.setPetType(cat);
        fionasPet.setBirthDate(LocalDate.now());
        owner2.getPets().add(fionasPet);
        ownerService.save(owner2);

        System.out.println("Loaded Owners ...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(radiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(surgery);
        vetService.save(vet2);

        System.out.println("Loaded Vets ...");
    }
}
