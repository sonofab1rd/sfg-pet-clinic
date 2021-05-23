package jake.springframework.sfgpetclinic.services;

import jake.springframework.sfgpetclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long Id);

    Vet save(Vet owner);

    Set<Vet> findAll();

}
