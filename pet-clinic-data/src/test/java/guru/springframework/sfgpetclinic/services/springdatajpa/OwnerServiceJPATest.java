package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceJPATest {

    public static final String LAST_NAME = "Doe";

    @InjectMocks
    OwnerServiceJPA ownerServiceJPA;
    @Mock
    OwnerRepository ownerRepository;
    private Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        when(this.ownerRepository.findByLastName(any())).thenReturn(returnOwner);
        var owner = this.ownerServiceJPA.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, owner.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        var ownerSet = new HashSet<Owner>();
        ownerSet.add(Owner.builder().id(1L).build());
        ownerSet.add(Owner.builder().id(2L).build());

        when(this.ownerRepository.findAll()).thenReturn(ownerSet);

        var owners = this.ownerServiceJPA.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
        var owner = this.ownerServiceJPA.findById(1L);
        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        var owner = this.ownerServiceJPA.findById(1L);
        assertNull(owner);
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(returnOwner);
        var owner = this.ownerServiceJPA.save(returnOwner);
        assertNotNull(owner);
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        this.ownerServiceJPA.delete(returnOwner);
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        this.ownerServiceJPA.deleteById(1L);
        verify(ownerRepository).deleteById(anyLong());
    }
}