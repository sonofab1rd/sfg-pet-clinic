package jake.springframework.sfgpetclinic.services;

import java.util.Optional;

public interface CrudService<T, ID> {

    Iterable<T> findAll();

    Optional<T> getById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);

}
