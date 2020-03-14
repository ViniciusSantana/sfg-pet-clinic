package guru.springframework.sfgpetclinic.model;

import java.time.LocalDate;

public class Pet {
    LocalDate borthDate;
    private PetType petType;
    private Owner owner;

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getBorthDate() {
        return borthDate;
    }

    public void setBorthDate(LocalDate borthDate) {
        this.borthDate = borthDate;
    }
}
