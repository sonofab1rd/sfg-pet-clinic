package jake.springframework.sfgpetclinic.services.springdatajpa;

import jake.springframework.sfgpetclinic.model.Owner;
import jake.springframework.sfgpetclinic.repositories.OwnerRepository;
import jake.springframework.sfgpetclinic.repositories.PetRepository;
import jake.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    public static final String LAST_NAME = "smith";

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().build();
        returnOwner.setId(1L);
        returnOwner.setLastName(LAST_NAME);
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnerSet = new HashSet<>();
        Owner owner1 = Owner.builder().build();
        owner1.setId(1L);
        owner1.setLastName(LAST_NAME);
        returnOwnerSet.add(owner1);
        Owner owner2 = Owner.builder().build();
        owner2.setId(2L);
        owner2.setCity("Cleveland");
        returnOwnerSet.add(owner2);

        when(ownerRepository.findAll()).thenReturn(returnOwnerSet);
        Set<Owner> owners = service.findAll();
        assertNotNull(owners);
        assertEquals(2, owners.size());

    }

    @Test
    void getById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
        Owner owner = service.getById(1L);
        assertNotNull(owner);
    }

    @Test
    void getByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner owner = service.getById(1L);
        assertNull(owner);
    }

    @Test
    void save() {
        Owner ownerToSave = service.save(Owner.builder().build());

        when(ownerRepository.save(any())).thenReturn(returnOwner);
        Owner savedOwner = service.save(ownerToSave);

        assertNotNull(savedOwner);
    }

    @Test
    void delete() {
        service.delete(returnOwner);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
        Owner owner = service.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, owner.getLastName());
        verify(ownerRepository).findByLastName(any());
    }
}