package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Vet save(Vet object) {
        if (object.getSpecialities().size() > 0) {
            object.getSpecialities().forEach(speciality -> {
                Speciality savedSpecialty = saveSpecialty(speciality);
                speciality.setId(savedSpecialty.getId());
            });
        }
        return super.save(object);
    }

    private Speciality saveSpecialty(Speciality speciality) {
        if (speciality.getId() == null) {
            return this.specialtyService.save(speciality);
        }
        return speciality;
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);

    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
