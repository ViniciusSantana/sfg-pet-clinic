package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerServiceMapTest {

    final long ownerId = 1L;
    final String lastName = "Doe";
    OwnerServiceMap serviceMap;

    @BeforeEach
    void setUp() {
        serviceMap = new OwnerServiceMap(new PetTypeMapService(), new PetServiceMap());
        serviceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        var owners = this.serviceMap.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void deleteById() {
        this.serviceMap.deleteById(ownerId);
        assertEquals(0, this.serviceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        var owner = Owner.builder().id(2L).build();

        var savedOwner = this.serviceMap.save(owner);

        assertEquals(2L, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        var owner = Owner.builder().build();

        var savedOwner = this.serviceMap.save(owner);

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        this.serviceMap.delete(this.serviceMap.findById(ownerId));
        assertEquals(0, this.serviceMap.findAll().size());
    }

    @Test
    void findById() {
        var owner = this.serviceMap.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastName() {
        var owner = this.serviceMap.findByLastName(lastName);
        assertNotNull(owner);
        assertEquals(lastName, owner.getLastName());
        assertEquals(ownerId, owner.getId());
    }
}