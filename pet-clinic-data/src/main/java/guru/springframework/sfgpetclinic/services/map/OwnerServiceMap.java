package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }


    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner save(Owner object) {
        if (object != null) {
            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    Pet savedPet = savePet(pet);
                    pet.setId(savedPet.getId());
                });
            }
            return super.save(object);
        } else {
            return null;
        }
    }

    private Pet savePet(Pet pet) {
        if (pet.getPetType() != null) {
            pet.setPetType(savePetType(pet.getPetType()));
        } else {
            throw new RuntimeException("Pet Type is Required");
        }
        if (pet.getId() == null) {
            return petService.save(pet);
        }
        return pet;
    }

    private PetType savePetType(PetType petType) {
        if (petType.getId() == null) {
            return this.petTypeService.save(petType);
        }

        return petType;
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);

    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
