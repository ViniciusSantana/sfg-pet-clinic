package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@Slf4j
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
    public void run(String... args) {
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
        log.info("Loaded Pet Types ...");

        Specialty radiology = new Specialty("radiology");
        Specialty surgery = new Specialty("surgery");
        Specialty dentistry = new Specialty("dentistry");
        radiology = this.specialtyService.save(radiology);
        surgery = this.specialtyService.save(surgery);
        dentistry = this.specialtyService.save(dentistry);
        log.info("Loaded Specialties ...");

        Owner owner1 = Owner.builder()
                .firstName("Michal")
                .lastName("Weston")
                .address("123 Bickered")
                .city("Miami")
                .telephone("123123123").build();

        Pet mikesPet = Pet.builder()
                .petType(dog)
                .owner(owner1)
                .birthDate(LocalDate.now())
                .name("Rosco")
                .build();

        owner1.setPets(Set.of(mikesPet));
        ownerService.save(owner1);

        Owner owner2 = Owner.builder()
                .firstName("Fiona")
                .lastName("Glenanne")
                .address("123 Bickered")
                .city("Miami")
                .telephone("123123123").build();


        Pet fionasPet = Pet.builder()
                .name("Just Cat")
                .owner(owner2)
                .petType(cat)
                .birthDate(LocalDate.now())
                .build();

        owner2.setPets(Set.of(fionasPet));
        ownerService.save(owner2);

        log.info("Loaded Owners ...");

        Vet vet1 = Vet.builder()
                .firstName("Sam")
                .lastName("Axe")
                .specialty(radiology)
                .specialty(dentistry)
                .build();
        vetService.save(vet1);

        Vet vet2 = Vet.builder()
                .firstName("Jessie")
                .lastName("Porter")
                .specialty(surgery)
                .build();
        vetService.save(vet2);

        log.info("Loaded Vets ...");
    }
}
